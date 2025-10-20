在Kubernetes（k8s）中，Pod的状态反映了其生命周期的不同阶段，由`status.phase`和更详细的`status.conditions`共同描述。了解这些状态及转换逻辑，是排查Pod部署问题的基础。


### 一、Pod的核心状态（`status.phase`）
`status.phase`是Pod最顶层的状态，描述了Pod在生命周期中的宏观阶段，主要有以下5种：

| 状态名称         | 含义说明                                                                 |
|------------------|--------------------------------------------------------------------------|
| **Pending（悬决）** | Pod已被API Server创建，但尚未调度到节点，或容器镜像正在拉取，或初始化容器未完成。 |
| **Running（运行中）** | Pod已调度到节点，所有容器已创建，至少一个容器处于运行状态（或正在启动/重启）。 |
| **Succeeded（成功）** | Pod中所有容器均已成功终止，且不会重启（如一次性任务Job的Pod）。             |
| **Failed（失败）**   | Pod中所有容器均已终止，且至少一个容器因失败终止（退出码非0）。               |
| **Unknown（未知）**  | 由于某种原因（如节点通信故障），API Server无法获取Pod的状态信息。           |


### 二、更详细的容器状态（`status.containerStatuses`）
`phase`仅反映宏观状态，每个容器的具体状态需通过`status.containerStatuses`查看，核心状态包括：

| 容器状态         | 含义说明                                                                 |
|------------------|--------------------------------------------------------------------------|
| **Waiting（等待中）** | 容器未运行，处于等待状态（如镜像拉取中、依赖的Secret/ConfigMap未就绪），通常包含`reason`（原因）和`message`（详情）。 |
| **Running（运行中）** | 容器正在运行，包含启动时间（`startedAt`）。                               |
| **Terminated（已终止）** | 容器已停止，包含终止原因（`reason`）、退出码（`exitCode`）、终止时间（`finishedAt`）。 |


### 三、Pod状态转换逻辑（生命周期流程）
Pod的状态转换遵循固定的生命周期流程，核心路径如下：

#### 1. 从创建到运行：`Pending → Running`
- **触发条件**：用户提交Pod清单（`kubectl apply -f pod.yaml`）。  
- **转换过程**：  
  1. API Server接收Pod请求，存储到etcd，此时Pod状态为`Pending`。  
  2. Scheduler（调度器）通过过滤（如节点资源是否满足）和打分（如负载均衡），为Pod选择合适的节点。  
  3. 节点上的Kubelet接收到调度结果，开始创建Pod：  
     - 拉取容器镜像（`Pulling`状态，若镜像拉取慢，`Pending`状态会持续）；  
     - 运行初始化容器（`initContainers`，若有多个则按顺序执行，全部完成后才启动业务容器）；  
     - 创建网络接口、挂载存储卷（如PersistentVolume）。  
  4. 业务容器启动成功后，Pod状态转为`Running`。  


#### 2. 运行中状态的可能转换
- **`Running → Succeeded`**：  
  - 触发条件：Pod是Job类型（一次性任务），所有容器正常完成（退出码0），且`restartPolicy`为`Never`或`OnFailure`（但Job默认会确保容器成功终止）。  
  - 例：执行`kubectl run job-test --image=busybox --restart=Never -- echo "done"`，Pod执行完命令后状态变为`Succeeded`。

- **`Running → Failed`**：  
  - 触发条件：至少一个容器异常终止（退出码非0），且`restartPolicy`不允许重启（如`restartPolicy=Never`）；或重启次数超过`terminationGracePeriodSeconds`限制。  
  - 例：容器内程序崩溃（退出码1），且未配置自动重启，Pod状态转为`Failed`。

- **`Running → Pending`**（特殊情况）：  
  - 罕见场景：如节点突然宕机，Kubelet与API Server通信中断，Pod状态可能短暂回退到`Pending`（实际已不在节点运行），最终可能转为`Unknown`。


#### 3. 异常状态转换
- **`Pending → Failed`**：  
  - 触发条件：初始化容器执行失败（如启动命令报错）、镜像拉取失败（如镜像地址错误、权限不足）、挂载存储失败（如PVC绑定失败）等，导致Pod无法进入`Running`状态。  

- **`Pending/Running → Unknown`**：  
  - 触发条件：节点故障（如断电、kubelet崩溃），API Server超过`node-monitor-grace-period`（默认40秒）未收到节点心跳，无法确定Pod状态，标记为`Unknown`。  


#### 4. 重启策略对状态的影响
Pod的`restartPolicy`（重启策略）决定容器终止后是否重启，直接影响状态转换：
- **`Always`**（默认，适用于Deployment等长期运行的Pod）：  
  容器失败后会自动重启，Pod状态保持`Running`（即使容器频繁重启，`phase`仍为`Running`，但容器状态会显示`Terminated`和`Waiting`的循环）。  
- **`OnFailure`**：  
  容器退出码非0时重启，退出码0时不重启（如Job完成后不重启）。  
- **`Never`**：  
  容器无论成功或失败都不重启（如一次性任务，失败后Pod直接转为`Failed`）。  


### 四、常见状态问题排查思路
- **Pending**：检查事件（`kubectl describe pod <pod-name>`），重点看`Events`部分，可能原因：  
  - 调度失败（如节点资源不足、节点亲和性不满足）；  
  - 镜像拉取失败（`ErrImagePull`，检查镜像地址、仓库权限）；  
  - 初始化容器失败（`Init:Error`，查看初始化容器日志）。  

- **Running但容器未就绪**：检查`readinessProbe`（就绪探针），若探针失败，Pod会被从Service端点中移除（但`phase`仍为`Running`）。  

- **CrashLoopBackOff**：容器反复崩溃重启，查看容器日志（`kubectl logs <pod-name> -f`）和退出码，通常是程序错误（如配置文件缺失、内存溢出）。  


### 总结：Pod状态转换核心路径
```
创建 → Pending（调度、拉镜像、初始化） → Running（容器运行中） → 
  ├→ Succeeded（所有容器正常终止）
  └→ Failed（至少一个容器异常终止）
  （中间可能因节点故障转为Unknown）
```

理解这些状态及转换逻辑，能快速定位Pod部署中的问题（如调度失败、镜像错误、程序崩溃等），是Kubernetes日常运维的基础技能。