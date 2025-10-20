K8s的Pod间通信逻辑是怎么样的？
Kubernetes（K8s）中 Pod 间的通信是其网络模型的核心场景，遵循“每个 Pod 拥有独立且唯一的 IP 地址”的设计原则，确保 Pod 间可以直接通过 IP 通信，无需依赖 NAT（网络地址转换）。以下从 **通信基础**、**同节点通信**、**跨节点通信**、**服务发现与负载均衡** 四个层面详细解析其逻辑：


### 一、Pod 通信的基础：网络命名空间与 CNI 插件
1. **Pod 的网络隔离单位**  
   每个 Pod 对应一个独立的 **Network Namespace（网络命名空间）**，包含独立的网络栈（IP 地址、端口、路由表、网卡等）。同一 Pod 内的容器共享这个命名空间，因此可通过 `localhost` 直接通信（如容器 A 访问 `localhost:8080` 即可到达同一 Pod 内的容器 B）。

2. **CNI 插件的作用**  
   K8s 本身不实现网络，而是通过 **CNI（Container Network Interface）插件** 为 Pod 分配 IP 地址、配置网络规则，实现 Pod 间的互联互通。常见的 CNI 插件（如 Calico、Flannel、Cilium）会在节点上创建虚拟网络设备（如 `cni0` 网桥、VXLAN 隧道设备），并负责维护跨节点的路由规则。


### 二、同节点 Pod 间通信：通过网桥直接转发
当两个 Pod 位于同一节点时，通信逻辑简单，无需跨物理网络，主要通过 **节点内的虚拟网桥** 转发：

1. **Pod 网络设备初始化**  
   - CNI 插件在节点上创建一个虚拟网桥（如 Flannel 的 `cni0`、Calico 的 `calico-bridge`），作为节点内 Pod 通信的“交换机”。
   - 每个 Pod 启动时，CNI 插件会为其创建一对 **veth 虚拟网卡**（类似“网线”）：一端（`eth0`）位于 Pod 的 Network Namespace 内（作为 Pod 的网卡），另一端连接到节点的虚拟网桥（如 `cni0`）。
   - CNI 插件为 Pod 分配唯一 IP 地址（来自集群的 Pod 子网，如 `10.244.0.0/16`），并配置 Pod 的路由表（默认网关指向虚拟网桥）。

2. **通信流程（以 Pod A 访问 Pod B 为例）**  
   - Pod A 发送数据包：目标 IP 为 Pod B 的 IP，通过自身 `eth0` 发送到节点的虚拟网桥（`cni0`）。
   - 网桥转发：虚拟网桥查询本地 ARP 表（记录节点内 Pod IP 与 veth 设备的映射关系），直接将数据包转发到 Pod B 的 veth 设备。
   - Pod B 接收：数据包通过自身 `eth0` 进入 Pod B 的网络命名空间，完成通信。

   整个过程在节点内部完成，无需经过物理网络，延迟极低。


### 三、跨节点 Pod 间通信：路由或隧道转发
当两个 Pod 位于不同节点（如 Node1 的 Pod A 访问 Node2 的 Pod B）时，通信需经过物理网络，具体逻辑取决于 CNI 插件采用的网络模式（Overlay 或 Underlay）：

#### 1. Overlay 网络模式（如 Flannel VXLAN、Weave Net）
通过 **隧道技术**（如 VXLAN、GRE）在物理网络之上封装 Pod 数据包，实现跨节点转发：
- **初始化**：
  - CNI 插件为每个节点分配一个子网（如 Node1 对应 `10.244.1.0/24`，Node2 对应 `10.244.2.0/24`）。
  - 节点间通过隧道设备（如 Flannel 的 `flannel.1`）建立虚拟连接，隧道端点为节点的物理 IP。
- **通信流程**：
  1. Pod A（IP：10.244.1.5）发送数据包，目标 IP 为 Pod B（10.244.2.6）。
  2. Node1 的路由表（由 CNI 插件维护）发现目标 IP 属于 Node2 子网，将数据包转发到隧道设备（`flannel.1`）。
  3. 隧道设备将 Pod 数据包 **封装** 在物理网络数据包中（源 IP：Node1 物理 IP，目标 IP：Node2 物理 IP），通过物理网络发送到 Node2。
  4. Node2 的隧道设备 **解封装**，得到原始 Pod 数据包，根据本地路由表转发到 Pod B 的 veth 设备，最终到达 Pod B。

  **特点**：无需修改物理网络，部署灵活，但因封装/解封装存在轻微性能损耗。


#### 2. Underlay 网络模式（如 Calico BGP、Macvlan）
Pod 直接使用物理网络的 IP 地址（与节点在同一网段），数据包无需封装，通过物理网络路由直接转发：
- **初始化**：
  - CNI 插件为 Pod 分配物理网络中的 IP（如 `192.168.1.0/24` 网段），与节点物理 IP 同属一个网络。
  - 通过 BGP 协议（如 Calico）或物理交换机路由，同步 Pod IP 与节点的映射关系（如“192.168.1.105 位于 Node1”）。
- **通信流程**：
  1. Pod A（IP：192.168.1.105）发送数据包，目标 IP 为 Pod B（192.168.1.206）。
  2. Node1 根据 BGP 路由表（或物理交换机 ARP 表），发现 Pod B 位于 Node2，直接通过物理网络将数据包发送到 Node2 的物理网卡。
  3. Node2 接收后，根据本地路由表转发到 Pod B 的 veth 设备，完成通信。

  **特点**：性能接近物理机（无封装损耗），但需物理网络支持（如 BGP 路由、允许多 IP 绑定）。


### 四、Pod 间通信的服务发现与负载均衡
直接通过 Pod IP 通信存在两个问题：**Pod 重建后 IP 会变化**、**多副本 Pod 需负载均衡**。K8s 通过 **Service 资源** 解决这些问题：

1. **Service 的核心作用**  
   - **固定访问入口**：为一组 Pod（通过标签 `selector` 关联，如 `app=nginx`）分配一个固定的虚拟 IP（ClusterIP），即使 Pod 重建，Service IP 不变。
   - **自动负载均衡**：访问 Service IP 时，请求会自动转发到后端健康的 Pod（默认轮询策略）。

2. **Service 转发逻辑（基于 Kube-Proxy）**  
   - 每个节点的 `kube-proxy` 组件监听 API Server，同步 Service 和 Pod 的关联关系（Endpoints）。
   - `kube-proxy` 通过 **iptables** 或 **IPVS** 在节点上配置转发规则：
     - 当 Pod 访问 Service 的 ClusterIP 时，规则会将请求转发到后端某个 Pod 的 IP:Port。
     - 例如：Service（ClusterIP：10.96.0.10，端口 80）关联 3 个 Nginx Pod，访问 `10.96.0.10:80` 会被转发到其中一个 Pod 的 `10.244.1.5:80`。


### 总结：Pod 间通信的完整逻辑链
1. **基础层**：CNI 插件为 Pod 分配唯一 IP，通过 veth 设备和节点网桥实现同节点通信。
2. **跨节点层**：通过 Overlay 隧道（如 VXLAN）或 Underlay 路由（如 BGP）实现不同节点 Pod 的互联互通。
3. **服务发现层**：Service 提供固定访问入口，Kube-Proxy 实现负载均衡，解决 Pod IP 动态变化和多副本问题。

整个过程无需人工配置 IP 或路由，完全由 K8s 网络组件自动化管理，确保 Pod 间通信的可靠性和灵活性。