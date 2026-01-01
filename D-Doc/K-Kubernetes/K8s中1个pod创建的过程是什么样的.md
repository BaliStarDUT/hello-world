在 Kubernetes（K8s）中，一个 Pod 的 Pod 的创建过程涉及涉及多个组件协同工作，从用户发起请求到 Pod 成功运行，涉及控制平面的调度决策、配置存储和状态同步，以及工作节点的容器创建等步骤。以下是详细的流程解析：


### 一、前提：Pod 的核心概念
Pod 是 K8s 最小部署单元，包含一个或多个容器，共享网络和存储资源。创建 Pod 的本质是：**用户定义“期望状态”（如容器镜像、资源需求），K8s 组件通过一系列操作将“期望状态”转化为“实际状态”**。


### 二、Pod 创建的完整流程（分 7 步）

#### 1. 用户发起创建请求
- **操作**：用户通过 `kubectl` 命令（如 `kubectl run my-pod --image=nginx`）、API 调用（如 `POST /api/v1/namespaces/default/pods`）或 YAML 文件（`kubectl apply -f pod.yaml`）提交创建 Pod 的请求。
- **目标**：向 K8s API Server 发送包含 Pod 定义的请求（如容器镜像、资源限制、标签等）。


#### 2. API Server 处理请求
- **验证请求**：API Server 首先校验请求的合法性：
  - 用户是否有权限创建 Pod（通过 RBAC 权限控制）。
  - Pod 定义是否符合规范（如必填字段是否存在、资源格式是否正确）。
- **存储配置**：验证通过后，API Server 将 Pod 的配置信息（如 `spec` 字段）写入 **etcd**（集群数据库），此时 Pod 状态为 `Pending`（等待调度）。
- **通知其他组件**：API Server 通过“监听机制”（如 watch API）告知其他组件（如 Scheduler、Controller Manager）有新的 Pod 创建事件。


#### 3. Scheduler 调度 Pod 到节点
- **监听待调度 Pod**：Scheduler 持续监听 API Server，发现状态为 `Pending` 且未指定节点（`nodeName` 为空）的 Pod。
- **调度决策（核心步骤）**：
  1. **过滤（Filter）**：排除不满足 Pod 需求的节点。  
     - 例如：Pod 请求 2GB 内存，节点剩余内存不足则被过滤；节点标签不匹配 Pod 的 `nodeSelector` 也会被过滤。
  2. **打分（Score）**：对过滤后的节点按“优先级”排序。  
     - 例如：优先选择资源利用率低的节点（避免节点过载）、满足 Pod 亲和性规则的节点（如 `nodeAffinity`）。
  3. **绑定（Bind）**：选择打分最高的节点，通过 API Server 将 Pod 与节点绑定（更新 etcd 中 Pod 的 `nodeName` 字段为目标节点名称）。


#### 4. 目标节点的 Kubelet 接管 Pod
- **监听绑定事件**：工作节点上的 Kubelet 组件持续监听 API Server，发现“被绑定到本节点”的 Pod。
- **验证节点状态**：Kubelet 检查本节点是否满足 Pod 的运行条件（如容器运行时是否正常、所需存储卷是否已挂载）。
- **初始化网络**：调用 **CNI 插件**（如 Calico、Flannel）为 Pod 创建网络环境：
  - 创建 Pod 的 Network Namespace（网络隔离域）。
  - 生成 veth 对（一端在 Pod 内作为 `eth0` 网卡，另一端连接节点网桥）。
  - 分配 Pod IP 地址（从节点的 Pod 子网中），配置路由规则。


#### 5. 创建存储卷（若有定义）
- 若 Pod 定义了 `volumes`（如 EmptyDir、ConfigMap、PersistentVolumeClaim），Kubelet 会：
  - 为 EmptyDir 创建临时目录（节点本地存储）。
  - 挂载 ConfigMap/Secret 到 Pod 的指定路径（从 etcd 读取配置数据）。
  - 关联 PersistentVolume（持久化存储），确保容器可访问共享数据。


#### 6. 启动容器（通过容器运行时）
- **调用容器运行时**：Kubelet 通过 **CRI（Container Runtime Interface）** 向容器运行时（如 containerd、CRI-O）发送创建容器的请求，包含以下信息：
  - 容器镜像（如 `nginx:latest`）及拉取策略（如 `IfNotPresent`）。
  - 资源限制（如 CPU 0.5 核、内存 512MiB）。
  - 启动命令（`command`）和参数（`args`）。
  - 环境变量、挂载卷等配置。
- **容器运行时操作**：
  1. 拉取容器镜像（若本地不存在）。
  2. 创建容器的文件系统（结合镜像层和挂载卷）。
  3. 配置容器的 Namespace（网络、PID、用户等隔离）。
  4. 启动容器进程，并设置资源限制（通过 cgroups）。


#### 7. 状态更新与健康检查
- **更新 Pod 状态**：容器启动后，Kubelet 持续监控容器状态（如运行、停止、崩溃），并通过 API Server 更新 etcd 中 Pod 的状态：
  - 若所有容器正常运行，Pod 状态变为 `Running`。
  - 若容器启动失败，状态变为 `Error` 或 `CrashLoopBackOff`（崩溃重启）。
- **执行健康检查**：
  - **存活探针（livenessProbe）**：若检查失败，Kubelet 重启容器（如应用卡死时）。
  - **就绪探针（readinessProbe）**：若检查失败，标记 Pod 为“未就绪”，避免 Service 将流量转发给它。


### 三、核心组件协作关系图
```
用户 → API Server → etcd
       ↑      ↓
Scheduler ←→ Kubelet ←→ 容器运行时 ←→ 容器
       ↓      ↓
      节点选择   网络/存储初始化
```


### 总结
Pod 的创建是 K8s “声明式 API” 思想的典型体现：用户只需定义“期望状态”，控制平面（API Server、Scheduler）和工作节点（Kubelet、容器运行时）通过协同操作，自动完成从“请求”到“运行”的全流程。整个过程涉及配置校验、调度决策、网络/存储初始化、容器生命周期管理等关键步骤，确保 Pod 按预期运行。

# K8s的pod起不来，可能有哪些原因？


Kubernetes（K8s）中 Pod 起不来是常见问题，可能涉及**调度、资源、镜像、配置、网络、存储**等多个环节。排查时需结合 `kubectl describe pod <pod-name>` 和 `kubectl logs <pod-name>`（若容器已启动）的输出，按以下分类逐步定位原因：


### 一、调度阶段失败（Pod 状态为 `Pending`）
Pod 处于 `Pending` 状态表示调度器（kube-scheduler）未将其分配到任何节点，核心原因是**节点不满足 Pod 的调度要求**。

#### 1. 节点资源不足
- **现象**：`kubectl describe pod` 显示 `FailedScheduling` 事件，提示“Insufficient cpu/memory”。  
- **原因**：节点的剩余资源（CPU/内存）小于 Pod 配置的 `resources.requests`（调度器根据 `requests` 选择节点）。  
- **解决**：  
  - 降低 Pod 的 `requests`（若应用实际需求较低）：  
    ```yaml
    resources:
      requests:
        cpu: "100m"  # 原需求可能过高，如 1000m
        memory: "256Mi"
    ```  
  - 扩容节点（增加节点数量）或升级节点配置（提升单节点资源）。  
  - 清理节点上低优先级的 Pod，释放资源（`kubectl delete pod <low-priority-pod>`）。


#### 2. 节点亲和性/污点不匹配
- **现象**：`FailedScheduling` 提示“0/3 nodes are available: 3 node(s) didn't match node selector”。  
- **原因**：  
  - **节点选择器（nodeSelector）**：Pod 指定了 `nodeSelector`（如 `disk: ssd`），但无节点匹配该标签。  
  - **节点亲和性（nodeAffinity）**：复杂亲和性规则（如 `requiredDuringSchedulingIgnoredDuringExecution`）未满足。  
  - **污点与容忍（Taints & Tolerations）**：节点有污点（如 `NoSchedule`），但 Pod 未配置对应的容忍。  
- **解决**：  
  - 检查节点标签：`kubectl get nodes --show-labels`，确保存在 Pod 所需标签（如缺失则添加：`kubectl label nodes <node-name> disk=ssd`）。  
  - 调整亲和性规则：将 `requiredDuringScheduling` 改为 `preferredDuringScheduling`（非强制），或修正规则条件。  
  - 为 Pod 添加容忍：若节点有污点 `key=value:NoSchedule`，Pod 需配置：  
    ```yaml
    tolerations:
    - key: "key"
      operator: "Equal"
      value: "value"
      effect: "NoSchedule"
    ```  


#### 3. 节点端口冲突
- **现象**：`FailedScheduling` 提示“Host port 8080 is already in use”。  
- **原因**：Pod 使用 `hostPort`（如 `hostPort: 8080`），但目标节点的 8080 端口已被其他进程或 Pod 占用。  
- **解决**：  
  - 避免使用 `hostPort`（改用 `Service` 暴露端口）。  
  - 若必须使用，更换端口或手动清理占用端口的进程。  


#### 4. 调度器异常
- **现象**：Pod 长期 `Pending`，无 `FailedScheduling` 事件，且 `kubectl get pods` 显示 `SchedulingGated`。  
- **原因**：  
  - 调度器（kube-scheduler）未运行或崩溃（`kubectl get pods -n kube-system | grep scheduler` 查看状态）。  
  - 调度器与 API Server 通信失败（如网络故障、认证授权问题）。  
- **解决**：  
  - 重启调度器：`kubectl delete pod -n kube-system <scheduler-pod>`（ Deployment 会自动重建）。  
  - 检查调度器日志：`kubectl logs -n kube-system <scheduler-pod>`，排查网络或配置错误。  


### 二、容器启动失败（Pod 状态为 `Error` 或 `CrashLoopBackOff`）
Pod 已调度到节点，但容器启动失败，状态可能为 `Error`（启动后立即退出）或 `CrashLoopBackOff`（反复启动失败）。

#### 1. 镜像拉取失败
- **现象**：`kubectl describe pod` 显示 `Failed to pull image` 或 `ErrImagePull`，事件提示“image not found”或“permission denied”。  
- **原因**：  
  - 镜像名称/标签错误（如 `nginx:latest123` 不存在）。  
  - 私有镜像仓库认证失败（未配置 `imagePullSecrets`）。  
  - 节点无法访问镜像仓库（网络不通，如防火墙阻止、DNS 解析失败）。  
- **解决**：  
  - 验证镜像存在：手动在节点上拉取测试（`docker pull <image>` 或 `crictl pull <image>`）。  
  - 配置镜像拉取密钥：若使用私有仓库，创建 `imagePullSecrets` 并关联到 Pod：  
    ```yaml
    imagePullSecrets:
    - name: myregistrykey  # 提前通过 kubectl create secret docker-registry 创建
    ```  
  - 检查节点网络：`ping registry.example.com` 验证连通性，`nslookup registry.example.com` 检查 DNS。  


#### 2. 容器启动命令错误
- **现象**：容器启动后立即退出，`kubectl logs <pod-name>` 显示“command not found”或业务报错（如配置文件缺失）。  
- **原因**：  
  - `command` 或 `args` 配置错误（如命令拼写错误、参数格式不正确）。  
  - 启动脚本依赖的文件/环境变量缺失（如脚本中引用 `$APP_HOME` 但未定义）。  
- **解决**：  
  - 简化启动命令测试：将 `command` 改为 `["sleep", "3600"]`，确认容器能否启动（排除命令本身问题）。  
  - 检查环境变量：确保 `env` 或 `configMap`/`secret` 正确挂载，如：  
    ```yaml
    env:
    - name: APP_HOME
      value: "/app"
    volumeMounts:
    - name: config
      mountPath: /app/config  # 确保配置文件已挂载
    ```  


#### 3. 资源限制导致 OOM 或权限不足
- **现象**：容器启动后被立即杀死，`kubectl describe pod` 显示 `OOMKilled` 或 `Error: container has runAsNonRoot and image has non-numeric user`。  
- **原因**：  
  - **OOMKilled**：容器内存使用超过 `limits.memory`，被 kubelet 强制终止。  
  - **权限不足**：Pod 配置 `runAsNonRoot: true`，但镜像默认使用 root 用户（UID 0），或挂载目录权限不允许非 root 访问。  
- **解决**：  
  - 调整内存限制：增大 `limits.memory` 或优化应用内存使用（参考前文 Java 应用 OOM 优化）。  
  - 修正权限配置：  
    - 若允许 root 启动：删除 `runAsNonRoot: true`。  
    - 若需非 root：在 Dockerfile 中创建非 root 用户（`USER 1000`），并确保挂载目录权限允许该用户访问。  


#### 4. 健康检查失败
- **现象**：容器启动成功，但 `livenessProbe` 或 `readinessProbe` 失败，导致容器被重启（`CrashLoopBackOff`）。  
- **原因**：  
  - 健康检查配置不合理（如 `initialDelaySeconds` 过短，应用未就绪就开始检查）。  
  - 应用本身启动慢（如加载大量数据），超过健康检查超时时间（`timeoutSeconds`）。  
- **解决**：  
  - 调整健康检查参数：延长 `initialDelaySeconds`（如从 5s 改为 30s），增大 `timeoutSeconds`（如从 1s 改为 5s）。  
  - 临时禁用健康检查（仅测试用）：注释 `livenessProbe` 和 `readinessProbe`，确认应用能否正常运行。  


### 三、存储相关问题（Pod 状态为 `Pending` 或 `Error`）
存储卷挂载失败会导致 Pod 无法启动，常见于使用 `PersistentVolumeClaim`（PVC）或 `emptyDir` 以外的存储。

#### 1. PVC 绑定失败
- **现象**：`kubectl describe pod` 显示 `PersistentVolumeClaim is not bound`，Pod 长期 `Pending`。  
- **原因**：  
  - 无匹配的 `PersistentVolume`（PV）：PV 的 `storageClassName`、容量、访问模式（如 `ReadWriteOnce`）与 PVC 不匹配。  
  - 存储类（StorageClass）配置错误：动态供应的 PV 未成功创建（如存储后端不可用）。  
- **解决**：  
  - 检查 PVC 状态：`kubectl get pvc`，若为 `Pending`，说明无匹配 PV，需创建符合条件的 PV 或调整 PVC 需求。  
  - 检查存储类事件：`kubectl describe storageclass <sc-name>`，排查动态供应失败原因（如存储后端认证错误）。  


#### 2. 存储卷挂载失败
- **现象**：`kubectl describe pod` 显示 `FailedMount`，提示“unable to mount volume”或“permission denied”。  
- **原因**：  
  - 存储后端故障（如 NFS 服务器宕机、Ceph 集群异常）。  
  - 挂载路径权限问题（如容器内挂载点目录被占用，或非 root 用户无权限访问）。  
  - 卷参数错误（如 NFS 路径拼写错误、`hostPath` 指向节点不存在的目录）。  
- **解决**：  
  - 验证存储后端可用性：在节点上手动挂载测试（如 `mount -t nfs 192.168.1.10:/data /tmp/test`）。  
  - 检查挂载路径：确保容器内挂载点未被镜像中已有的文件占用（如挂载到 `/app`，但镜像中 `/app` 已有文件，需改用子目录 `/app/data`）。  


### 四、网络相关问题（Pod 状态为 `Running` 但不可用）
Pod 状态为 `Running`，但应用无法访问（如服务端口不通），可能是网络配置问题。

#### 1. 网络插件（CNI）故障
- **现象**：Pod 无 IP 地址（`kubectl get pods -o wide` 显示 `<none>`），或无法与其他 Pod 通信。  
- **原因**：  
  - CNI 插件（如 Calico、Flannel）未运行（`kubectl get pods -n kube-system` 查看相关 Pod 状态）。  
  - CNI 配置文件损坏（`/etc/cni/net.d/` 下的配置文件缺失或格式错误）。  
- **解决**：  
  - 重启 CNI 插件：`kubectl delete pods -n kube-system -l k8s-app=calico-node`（以 Calico 为例）。  
  - 检查节点网络：确保节点间 Pod CIDR 网段互通（如 Calico BGP 模式需底层路由支持）。  


#### 2. NetworkPolicy 拦截
- **现象**：Pod 启动正常，但无法访问外部服务或被其他 Pod 访问，`kubectl exec` 进入容器后 `ping` 目标 IP 失败。  
- **原因**：命名空间中存在 `NetworkPolicy`，规则限制了该 Pod 的入站/出站流量（如仅允许特定端口或 Pod 访问）。  
- **解决**：  
  - 查看命名空间的 NetworkPolicy：`kubectl get networkpolicy -n <namespace>`。  
  - 临时删除可疑 Policy 测试：`kubectl delete networkpolicy <policy-name> -n <namespace>`，确认是否为 Policy 拦截。  


### 五、其他常见原因
1. **镜像拉取策略导致启动慢**：  
   - 若 `imagePullPolicy: Always`，每次启动都拉取镜像，若镜像过大或网络慢，会导致启动超时。  
   - 解决：改为 `imagePullPolicy: IfNotPresent`（本地有镜像时不重复拉取）。  

2. **节点磁盘满**：  
   - 节点磁盘空间或 inode 耗尽（`df -h` 或 `df -i` 查看），导致无法拉取镜像或创建容器。  
   - 解决：清理节点无用文件（如旧日志、未使用的镜像：`crictl rmi --prune`）。  

3. **API Server 通信失败**：  
   - Pod 需与 API Server 交互（如获取 ConfigMap），但因网络隔离或 RBAC 权限不足导致失败。  
   - 解决：检查 Pod 所在 ServiceAccount 的权限（`kubectl describe sa <sa-name>`），确保有足够权限访问所需资源。  


### 排查流程总结
1. **查看 Pod 状态**：`kubectl get pods <pod-name>` 确定是 `Pending`、`Error` 还是 `CrashLoopBackOff`。  
2. **获取详细事件**：`kubectl describe pod <pod-name>` 重点看 `Events` 部分，定位失败阶段（调度、拉取镜像、挂载卷等）。  
3. **查看容器日志**：若容器曾启动，`kubectl logs <pod-name> -c <container-name>`（若多容器）查看应用报错。  
4. **检查节点状态**：`kubectl describe node <node-name>` 查看节点资源、污点、事件，确认节点是否健康。  

通过以上步骤，可逐步缩小问题范围，定位 90% 以上的 Pod 启动失败原因。