Kubernetes（简称 K8s）是一个开源的**容器编排平台**，核心目标是实现容器化应用的自动化部署、扩展、运维和高可用管理。其架构采用**分布式主从（Master-Worker）模式**，将集群节点分为**控制平面（Control Plane）** 和**工作节点（Worker Node）** 两类，各组件分工明确、协同工作。


## 一、Kubernetes 整体架构
K8s 集群由两部分核心节点组成，所有组件通过 **Kubernetes API Server** 实现通信（API Server 是集群的“统一入口”）：
- **控制平面（Control Plane）**：负责集群的“决策”和“管理”（如调度容器、监控集群状态、处理故障等），通常部署在 1 个或多个专用节点上（生产环境需高可用部署，避免单点故障）。
- **工作节点（Worker Node）**：负责“运行”容器化应用，是实际承载业务负载的节点，可横向扩展（增加节点以提升算力）。

架构示意图（简化）：
```
[控制平面节点]                [工作节点 1]      [工作节点 2]      ...
┌────────────────────────┐    ┌─────────────┐    ┌─────────────┐
│ API Server             │    │ Kubelet     │    │ Kubelet     │
│ Controller Manager     │◄───►│ Kube-Proxy  │◄───►│ Kube-Proxy  │
│ Scheduler              │    │ 容器运行时  │    │ 容器运行时  │
│ etcd                   │    │ (如 containerd)│  (如 containerd)│
└────────────────────────┘    └─────────────┘    └─────────────┘
                                      │                    │
                                      ▼                    ▼
                              ┌─────────────┐    ┌─────────────┐
                              │ 容器化应用  │    │ 容器化应用  │
                              └─────────────┘    └─────────────┘
```


## 二、控制平面组件（Control Plane Components）
控制平面组件通常部署在同一节点（称为“Master 节点”），生产环境需通过多节点部署实现高可用（如 API Server 多实例、etcd 集群）。


### 1. etcd：集群的“数据库”
- **核心功能**：作为 K8s 集群的**分布式键值存储（Key-Value Store）**，存储集群的所有**配置数据和状态信息**（如节点信息、Pod 定义、服务规则、权限配置等）。
- **关键特性**：
  - 强一致性：采用 Raft 共识算法，确保多节点部署时数据一致（适合高可用场景）。
  - 仅 API Server 可直接访问：etcd 不对外暴露接口，所有集群数据的读写必须通过 API Server 中转，保证数据安全性和一致性。
- **类比**：相当于 K8s 集群的“大脑记忆中枢”，所有组件的决策都依赖 etcd 中的数据。


### 2. API Server：集群的“统一入口”
- **核心功能**：提供 **RESTful API 接口**，是所有组件（控制平面内部、控制平面与工作节点）的通信“桥梁”。
- **关键逻辑**：
  - 数据校验：所有对集群的操作（如创建 Pod、删除服务）需先通过 API Server 校验合法性（如权限、资源格式）。
  - 数据转发：校验通过后，API Server 将数据写入 etcd；其他组件（如 Scheduler、Controller Manager）通过“监听 API Server”获取数据变更，触发后续操作。
  - 权限控制：集成 RBAC（基于角色的访问控制），控制用户/组件对集群资源的访问权限。
- **类比**：相当于 K8s 集群的“前台接待员”，所有请求必须经过它，同时负责“登记”（写入 etcd）和“通知”（同步数据给其他组件）。


### 3. Scheduler：容器的“调度器”
- **核心功能**：负责将 **Pod（K8s 最小部署单元，包含一个或多个容器）** 调度到合适的工作节点上运行。
- **调度逻辑（核心流程）**：
  1. **监听请求**：持续监听 API Server，发现“未调度的 Pod”（Pod 状态为 `Pending`）。
  2. **过滤（Filtering）**：排除不满足 Pod 资源需求的节点（如 Pod 需要 2GB 内存，节点剩余内存不足则被过滤）。
     - 常见过滤规则：节点资源（CPU/内存）、节点标签（如 `env=prod`）、容器运行时兼容性、端口冲突等。
  3. **打分（Scoring）**：对过滤后的节点进行“优先级排序”，选择最优节点。
     - 常见打分维度：节点资源利用率（如优先调度到空闲节点）、节点亲和性（如 Pod 倾向于部署在特定节点）、负载均衡（避免单节点过载）。
  4. **绑定（Binding）**：将最优节点信息写入 API Server，完成 Pod 调度。
- **类比**：相当于 K8s 集群的“资源调度员”，根据“任务需求（Pod）”和“节点能力”，分配最合适的“工作工位（节点）”。


### 4. Controller Manager：集群的“控制器中枢”
- **核心功能**：运行一系列 **控制器（Controller）**，负责监控集群状态，确保实际状态与用户定义的“期望状态”一致（即“调谐”过程）。
- **常见控制器及功能**：
  | 控制器（Controller）       | 核心作用                                                                 |
  |---------------------------|--------------------------------------------------------------------------|
  | Node Controller           | 监控节点状态：若节点故障（如断连），标记节点为“不可用”，并驱逐其上的 Pod。 |
  | ReplicaSet Controller     | 确保 Pod 副本数：若用户定义“3个 Pod 副本”，则自动创建/删除 Pod 以维持数量。 |
  | Deployment Controller     | 管理 Deployment（高层抽象）：支持 Pod 滚动更新、回滚（如版本升级失败时回滚）。 |
  | Service Controller        | 管理 Service（服务发现）：为 Pod 分配固定访问地址（ClusterIP），并维护 Pod 与 Service 的关联。 |
  | Endpoints Controller      | 维护 Endpoints：实时同步 Service 对应的 Pod IP 列表（如 Pod 新增/删除时更新）。 |
  | Namespace Controller      | 管理 Namespace（命名空间）：处理 Namespace 的创建、删除，并清理其下的所有资源。 |
- **工作逻辑（通用调谐流程）**：
  1. **监控状态**：通过 API Server 监听某类资源（如 Pod、Node）的实际状态。
  2. **对比期望**：读取 etcd 中用户定义的“期望状态”（如 ReplicaSet 的 `replicas: 3`）。
  3. **执行调谐**：若实际状态 ≠ 期望状态，则触发操作（如创建 Pod、删除节点），直至两者一致。
- **类比**：相当于 K8s 集群的“运维管理员”，持续巡检集群状态，发现“偏差”（如 Pod 少了、节点故障）后自动修复，确保集群按“预期”运行。


## 三、工作节点组件（Worker Node Components）
工作节点是运行容器化应用的节点，每个工作节点需部署以下核心组件，负责接收控制平面的指令并执行。


### 1. Kubelet：节点的“管家”
- **核心功能**：在每个工作节点上运行，负责管理该节点上的 Pod 和容器，确保容器按 Pod 定义正常运行。
- **关键逻辑**：
  1. **接收指令**：监听 API Server，获取分配给当前节点的 Pod 列表（即 Scheduler 绑定的 Pod）。
  2. **创建容器**：根据 Pod 定义（如容器镜像、资源限制），调用“容器运行时”（如 containerd）创建容器。
  3. **监控容器**：实时监控容器状态（如运行、停止、崩溃），并将状态上报给 API Server（如 Pod 状态更新为 `Running` 或 `CrashLoopBackOff`）。
  4. **执行健康检查**：按 Pod 定义的“健康检查规则”（如 `livenessProbe`、`readinessProbe`）检查容器：
     - `livenessProbe`（存活探针）：若容器无响应，自动重启容器（如应用卡死时重启）。
     - `readinessProbe`（就绪探针）：若容器未就绪（如数据库未初始化完成），则标记 Pod 为“不可用”，避免流量接入。
  5. **资源限制**：确保容器不超出 Pod 定义的资源限制（如 CPU 上限、内存上限），若超出则触发“资源节流”或“容器终止”（如内存溢出时 OOM Kill）。
- **类比**：相当于工作节点的“本地管家”，负责“接待任务（Pod）”、“安排容器运行”、“监控任务状态”，并向控制平面“汇报工作”。


### 2. Kube-Proxy：节点的“网络代理”
- **核心功能**：在每个工作节点上运行，负责实现 K8s 的**服务发现（Service）** 和**负载均衡**，确保 Pod 网络可访问。
- **关键逻辑**：
  1. **同步 Service 规则**：监听 API Server，获取集群中所有 Service 的配置（如 ClusterIP、端口映射）。
  2. **配置网络规则**：通过 **iptables（Linux 原生）** 或 **IPVS（高性能模式）** 在节点上配置网络转发规则：
     - 当外部请求访问 Service 的 ClusterIP 时，Kube-Proxy 将请求转发到该 Service 关联的某个 Pod（实现负载均衡）。
     - 处理 Pod 之间的跨节点通信（如 Node1 的 Pod 访问 Node2 的 Pod）。
  3. **维护 Pod 关联**：当 Pod 新增/删除时，自动更新网络规则（确保转发目标始终是“存活的 Pod”）。
- **类比**：相当于工作节点的“网络网关”，负责“路由请求”和“分配流量”，确保 Service 能正确找到并访问背后的 Pod。


### 3. 容器运行时（Container Runtime）
- **核心功能**：提供容器的“运行环境”，负责容器的创建、启动、停止、销毁等底层操作，是 K8s 运行容器的“依赖基础”。
- **常见容器运行时**：
  - **containerd**：Docker 原生命令行工具 `docker` 的底层运行时，目前是 K8s 官方推荐的默认运行时（轻量、稳定）。
  - **CRI-O**：专为 K8s 设计的容器运行时，完全兼容 K8s 的 CRI（容器运行时接口），无 Docker 依赖。
  - **Docker Engine**：早期常用的运行时，但需通过 `cri-dockerd` 适配器兼容 CRI（K8s 1.24+ 已移除对 Docker 的直接支持）。
- **工作逻辑**：Kubelet 通过 **CRI（Container Runtime Interface，容器运行时接口）** 与容器运行时通信，下发“创建容器”等指令，容器运行时则调用底层的 `runc`（容器生命周期管理工具）完成实际操作。


## 四、核心逻辑总结：一个 Pod 的“诞生到运行”流程
以用户创建一个 Deployment（包含 2 个 Pod 副本）为例，串联 K8s 各组件的协同工作：
1. **用户发起请求**：通过 `kubectl create deployment my-app --image=nginx --replicas=2` 向 API Server 发送创建 Deployment 的请求。
2. **API Server 处理**：
   - 校验请求合法性（如用户权限、资源格式）。
   - 将 Deployment 配置（期望 2 个 Pod 副本）写入 etcd。
3. **Controller Manager 调谐**：
   - Deployment Controller 监听 API Server，发现新的 Deployment，自动创建一个 ReplicaSet（负责维护 Pod 副本数）。
   - ReplicaSet Controller 监听 API Server，发现 ReplicaSet 的期望副本数为 2，且当前无 Pod，于是向 API Server 发起“创建 2 个 Pod”的请求。
   - API Server 校验后，将 2 个“未调度的 Pod”写入 etcd。
4. **Scheduler 调度**：
   - 监听 API Server，发现 2 个 Pending 状态的 Pod。
   - 对集群中的工作节点进行“过滤”和“打分”，选择 Node1 和 Node2 作为调度目标。
   - 将 Pod 与 Node 的绑定关系写入 API Server。
5. **Worker Node 执行**：
   - Node1 和 Node2 上的 Kubelet 分别监听 API Server，发现“分配给自己的 Pod”。
   - Kubelet 通过 CRI 调用 containerd 创建 Pod 中的 Nginx 容器。
   - Kubelet 监控容器状态，将 Pod 状态更新为 `Running` 并上报给 API Server。
6. **网络访问准备**：
   - 用户创建 Service（`kubectl expose deployment my-app --port=80`），API Server 将 Service 配置写入 etcd。
   - Service Controller 为 Service 分配 ClusterIP，并创建 Endpoints（关联 2 个 Pod 的 IP）。
   - 所有工作节点的 Kube-Proxy 监听 API Server，更新 iptables/IPVS 规则，确保访问 Service 的 ClusterIP 时，流量能转发到 2 个 Pod。


通过以上组件的协同，K8s 实现了容器化应用的“自动化部署、动态调度、故障自愈、服务发现”，最终达成集群的高可用和可扩展性。