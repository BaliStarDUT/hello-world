在Kubernetes（K8s）中使用GPU资源部署大模型推理实例，核心是通过**设备插件（Device Plugin）** 暴露GPU资源，再**资源限制（Resource Limits）** 指定GPU使用量，并结合**容器化推理服务**实现实例创建。以下是详细的实现流程和最佳实践：


### 一、K8s管理GPU的核心前提
1. **GPU硬件与驱动**：  
   - 节点需配备NVIDIA GPU（如A100、V100），并安装对应版本的**NVIDIA驱动**（需与CUDA版本兼容）。  
   - 验证：节点上执行`nvidia-smi`能正常显示GPU信息（如型号、显存、驱动版本）。  

2. **NVIDIA设备插件（Device Plugin）**：  
   K8s通过设备插件框架识别节点上的GPU资源，需部署**NVIDIA Device Plugin**（官方推荐），其作用是：  
   - 向K8s API Server上报节点的GPU数量、型号等信息。  
   - 为容器分配GPU设备，并挂载驱动和CUDA库。  

   **部署方式**（通过DaemonSet确保每个GPU节点都运行）：  
   ```yaml
   # nvidia-device-plugin-daemonset.yaml
   apiVersion: apps/v1
   kind: DaemonSet
   metadata:
     name: nvidia-device-plugin-daemonset
     namespace: kube-system
   spec:
     selector:
       matchLabels:
         name: nvidia-device-plugin-ds
     template:
       metadata:
         labels:
           name: nvidia-device-plugin-ds
       spec:
         tolerations:
         - key: nvidia.com/gpu
           operator: Exists
           effect: NoSchedule
         containers:
         - image: nvidia/k8s-device-plugin:v0.14.1  # 版本需匹配K8s版本
           name: nvidia-device-plugin-ctr
           securityContext:
             allowPrivilegeEscalation: false
             capabilities:
               drop: ["ALL"]
           volumeMounts:
           - name: device-plugin
             mountPath: /var/lib/kubelet/device-plugins
         volumes:
         - name: device-plugin
           hostPath:
             path: /var/lib/kubelet/device-plugins
   ```  
   部署后，节点的`status.allocatable`会显示GPU资源（如`nvidia.com/gpu: 4`表示4张GPU）。  


### 二、大模型推理实例的容器化配置
大模型推理服务需打包为容器镜像（如基于PyTorch、vLLM等框架），并在K8s资源清单中指定GPU使用规则。


#### 1. 基础镜像构建
以vLLM（高效大模型推理框架）为例，构建支持GPU的镜像：  
```dockerfile
# Dockerfile
FROM nvidia/cuda:11.8.0-cudnn8-runtime-ubuntu22.04
RUN apt-get update && apt-get install -y python3 python3-pip
RUN pip install vllm==0.2.6 transformers==4.34.0  # 安装vLLM和依赖
# 拷贝模型启动脚本
COPY start.sh /start.sh
CMD ["/start.sh"]
```  
`start.sh`内容（启动vLLM服务，指定模型和GPU参数）：  
```bash
#!/bin/bash
python -m vllm.entrypoints.api_server \
  --model /models/llama-2-7b \  # 模型路径（通过PVC挂载）
  --port 8000 \
  --tensor-parallel-size $GPU_NUM \  # 张量并行数（与分配的GPU数一致）
  --max-num-seqs 20  # 单实例最大并发数
```  


#### 2. K8s资源清单配置（Deployment/StatefulSet）
通过`resources.limits`指定GPU数量，结合`nodeSelector`或`affinity`调度到GPU节点，示例如下：  

```yaml
# llama-inference-deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: llama-2-7b-inference
  namespace: model-inference
spec:
  replicas: 2  # 启动2个推理实例
  selector:
    matchLabels:
      app: llama-inference
  template:
    metadata:
      labels:
        app: llama-inference
    spec:
      # 调度到有GPU的节点
      nodeSelector:
        nvidia.com/gpu.present: "true"  # 匹配带GPU的节点
      # 资源限制：每个实例使用1张GPU
      containers:
      - name: vllm-server
        image: my-registry/vllm-llama:v1  # 自定义镜像
        ports:
        - containerPort: 8000
        # GPU资源配置
        resources:
          limits:
            nvidia.com/gpu: 1  # 申请1张GPU（必填，精确到整数）
          requests:
            cpu: "4"  # 申请4核CPU（根据模型需求调整）
            memory: "16Gi"  # 申请16GB内存
        # 环境变量：传递GPU数量给启动脚本
        env:
        - name: GPU_NUM
          value: "1"
        # 挂载模型（通过PVC从存储系统加载，如NAS/S3）
        volumeMounts:
        - name: model-storage
          mountPath: /models
      volumes:
      - name: model-storage
        persistentVolumeClaim:
          claimName: model-pvc  # 提前创建的PVC，指向模型存储
```  


#### 3. 关键参数说明
- **`nvidia.com/gpu`限制**：  
  - 必须在`resources.limits`中指定（`requests`可省略，默认与`limits`一致），值为整数（如`1`表示1张GPU，`2`表示2张）。  
  - 若模型支持张量并行（如70B模型需4张GPU），则设置为对应数量（如`4`），同时启动脚本中`--tensor-parallel-size`需匹配。  

- **节点选择与亲和性**：  
  - 用`nodeSelector: nvidia.com/gpu.present: "true"`确保调度到有GPU的节点。  
  - 若需指定GPU型号（如仅用A100），可通过标签选择：  
    ```yaml
    nodeSelector:
      nvidia.com/gpu.product: "NVIDIA-A100-80GB-PCIe"
    ```  

- **模型存储**：  
  大模型权重（如Llama-2-70B约130GB）需通过**PVC（PersistentVolumeClaim）** 挂载，存储后端可选：  
  - 共享存储（如NAS、NFS）：适合多实例共享模型权重（节省存储空间）。  
  - 对象存储（如S3兼容存储）：通过`s5cmd`或`rclone`在容器启动时下载模型到本地缓存（适合分布式场景）。  


### 三、服务暴露与访问
推理实例部署后，通过**Service**暴露端口，供外部或内部服务调用：  

```yaml
# llama-inference-service.yaml
apiVersion: v1
kind: Service
metadata:
  name: llama-inference-service
  namespace: model-inference
spec:
  selector:
    app: llama-inference
  ports:
  - port: 80
    targetPort: 8000
  type: ClusterIP  # 内部访问用ClusterIP，外部访问用NodePort/Ingress
```  

- 内部访问：通过`llama-inference-service.model-inference.svc.cluster.local:80`调用推理API。  
- 外部访问：结合Ingress（如NGINX Ingress）配置域名和路径转发。  


### 四、高级配置：优化GPU利用率
1. **多实例共享GPU（MIG模式）**：  
   对支持MIG（Multi-Instance GPU）的GPU（如A100、H100），可将1张物理GPU分割为多个独立的MIG实例（如A100-80GB可分为7个10GB MIG实例），实现资源隔离和细粒度分配。  
   - 配置：在节点上启用MIG模式，Device Plugin会自动识别MIG实例，使用时指定`nvidia.com/mig-1g.10gb: 1`（申请1个10GB MIG实例）。  

2. **自动扩缩容（HPA）**：  
   根据GPU利用率或请求队列长度自动调整实例数量，示例：  
   ```yaml
   # 基于GPU利用率扩缩容
   apiVersion: autoscaling/v2
   kind: HorizontalPodAutoscaler
   metadata:
     name: llama-inference-hpa
   spec:
     scaleTargetRef:
       apiVersion: apps/v1
       kind: Deployment
       name: llama-2-7b-inference
     minReplicas: 2
     maxReplicas: 10
     metrics:
     - type: Pods
       pods:
         metric:
           name: gpu_utilization  # 需通过Prometheus采集GPU指标
         target:
           type: AverageValue
           averageValue: 70%  # 平均GPU利用率超过70%则扩容
   ```  

3. **资源超限保护**：  
   - 用`limits`严格限制GPU使用（避免单实例占用过多资源）。  
   - 通过**ResourceQuota**限制命名空间的总GPU使用量（如`nvidia.com/gpu: 10`表示命名空间最多使用10张GPU）。  


### 五、常见问题与排查
1. **Pod一直Pending**：  
   - 检查节点是否有可用GPU：`kubectl describe nodes <node-name> | grep nvidia.com/gpu`，确认`Allocatable`大于0且未被完全分配。  
   - 检查`nodeSelector`是否匹配：确保节点有对应的标签（如`nvidia.com/gpu.present: "true"`）。  

2. **容器启动失败，提示“CUDA error: no CUDA-capable device is detected”**：  
   - 检查NVIDIA Device Plugin是否正常运行：`kubectl get pods -n kube-system | grep nvidia-device-plugin`。  
   - 确认容器内是否能访问GPU：`kubectl exec -it <pod-name> -- nvidia-smi`，若命令不存在，可能是镜像未基于NVIDIA CUDA镜像构建。  

3. **GPU利用率低**：  
   - 增加单实例并发数（如vLLM的`--max-num-seqs`），或通过HPA减少实例数量（避免资源闲置）。  


### 总结
K8s使用GPU部署大模型推理实例的核心流程是：  
1. 部署NVIDIA Device Plugin，让K8s识别GPU资源；  
2. 构建包含推理框架（如vLLM）和模型依赖的容器镜像；  
3. 通过Deployment配置GPU资源限制、节点调度和模型存储；  
4. 暴露Service供访问，并结合HPA实现弹性扩缩容。  

这种方式能高效利用GPU资源，支持大规模部署和动态调度，是生产环境中大模型推理服务的标准方案。