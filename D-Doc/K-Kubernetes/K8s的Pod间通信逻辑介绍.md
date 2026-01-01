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

# 5、请详述kube-proxy原理?
- https://blog.51cto.com/u_14224/9285358
![alt text](image.png)
service其实只是一个概念，真正起作用的是kube-proxy服务进程，每个node节点上都运行一个kube-proxy服务进程，创建service的时候会通过apiserver向etcd中写入service的信息，kube-proxy进行会监听service的变动，然后将最新的service信息转换成对应的访问规则。
![alt text](image-1.png)

## kube-proxy现在支持的三种模式：

### 1、userspace模式 

userspace模式下，kube-proxy会为每个service创建一个监听端口，发向cluster IP的请求被iptables规则重定向到kube-proxy监听的端口上，然后kube-proxy根据LB算法选择一个可以提供服务的pod并建立连接，将请求转发到这个pod上。

- 这种模式，进行请求转发处理时，会增加内核和用户之间的数据拷贝，效率低，但是稳定
-----------------------------------
K8S做虚拟化 面试题 k8s常见面试题
https://blog.51cto.com/u_14224/9285358

![alt text](image-2.png)
### 2、iptables模式

iptables模式下，kube-proxy为service后端的每个pod创建对应的iptables规则，将直接发向cluster IP的请求重定向到一个pod 的ip上。

该模式下kube-proxy只负责创建iptables规则，优点是效率高，但是不能提供灵活的负载均衡策略，而后端pod不可用时，也无法重试。
![alt text](image-3.png)

### 3、ipvs模式

 ipvs模式和iptables类似，kube-proxy监控Pod的变化并创建相应的ipvs规则。ipvs相对iptables转发效率更高。除此以外，ipvs支持更多的LB算法

![alt text](image-4.png)
答：集群中每个Node上都会运行一个kube-proxy服务进程，他是Service的透明代理兼均衡负载器，其核心功能是将某个Service的访问转发到后端的多个Pod上。kube-proxy通过监听集群状态变更，并对本机iptables做修改，从而实现网络路由。 而其中的负载均衡，也是通过iptables的特性实现的。从V1.8版本开始，用IPVS（IP Virtual Server）模式，用于路由规则的配置，主要优势是：1）为大型集群提供了更好的扩展性和性能。采用哈希表的数据结构，更高效；2）支持更复杂的负载均衡算法；3）支持服务器健康检查和连接重试；4）可以动态修改ipset的集合；


-----------------------------------
K8S做虚拟化 面试题 k8s常见面试题
https://blog.51cto.com/u_14224/9285358


# k8s发布(暴露)服务，servcie的类型有那些

- ClusterIP类型： kubernetes系统自动分配的虚拟ip（或者自己配置clusterIP），只能在集群内部访问
headless类型：无头服务，某些场景开发人员不想使用service提供的负载均衡功能，而是希望自己控制。（见后面）这类service配置时clusterIP要设置为None，如果要访问service，只能通过service的域名访问。
NodePort类型：这类service可以将service暴露给集群外部使用，原理就是将service的端口映射到Node的一个端口上，最常用
LoadBalancer类型：LoadBalancer又会在集群外部做一个负载均衡的设备，相当于把NodePort类型的service在向外映射。这种用法仅用于在公有云服务提供商的云平台上设置Service的场景；
ExternalName类型：用于引入集群外部的服务，通过externalName属性指定外部服务的地址，然后在集群内访问这个service的时候，就可以访问到外部服务了。
-----------------------------------
K8S做虚拟化 面试题 k8s常见面试题
https://blog.51cto.com/u_14224/9285358
![alt text](image-5.png)

- 答：kubernetes原生的，一个Service的ServiceType决定了其发布服务的方式。1） ClusterIP：这是k8s默认的ServiceType。通过集群内的ClusterIP在内部发布服务。2）NodePort：这种方式是常用的，用来对集群外暴露Service，你可以通过访问集群内的每个NodeIP:NodePort的方式，访问到对应Service后端的Endpoint。3）LoadBalancer: 这也是用来对集群外暴露服务的，不同的是这需要Cloud Provider的支持，比如AWS等。4）ExternalName：这个也是在集群内发布服务用的，需要借助KubeDNS(version >= 1.7)的支持，就是用KubeDNS将该service和ExternalName做一个Map，KubeDNS返回一个CNAME记录；
-----------------------------------
K8S做虚拟化 面试题 k8s常见面试题
https://blog.51cto.com/u_14224/9285358

# 43、简述Kubernetes ingress?
前面了解了：Service对集群之外暴露服务的主要方式有两种：NotePort和LoadBalancer，但是这两种方式，都有一定的缺点：

NodePort方式的缺点是占用了很多的Node端口

LB缺点是每个service需要一个LB，资源浪费，并且需要k8s之外的设备支持。

为了解决上述问题，k8s提供了ingress资源对象，ingress只需要一个NodePort或者一个LB就可以满足暴露多个service的需求 。如下图：

![alt text](image-6.png)

实际上，ingress相当于一个7层负载均衡器，是kubernetes对反向代理的一个抽象，工作原理类似于Nginx，可以简单的理解成：

ingress建立了诸多的映射规则，ingress controller 通过监听这些配置规则bin转化为Nginx的反向代理配置，然后对外提供服务
。

- ingress：k8s中一种资源对象，作用是定义请求如何转发到service的规则
ingress controller： 具体实现反向代理和负载均衡的程序。负责对ingress定义的规则进行解析，然后根据配置规则来实现请求转发，实现方式很多如Nginx，HAProxy等
-----------------------------------
K8S做虚拟化 面试题 k8s常见面试题
https://blog.51cto.com/u_14224/9285358

- ingress的工作原理（以Nginx实现方式为例）

- 用户编写ingress规则，说明那个域名对应k8s集群中的哪个service
ingress controller动态感知ingress服务规则的变化，然后生成对应的Nginx反向代理配置
ingress controller将生成的Nginx配置写入到一个运行的Nginx服务中（这个是动态更新的 ）
然后就是Nginx来工作了，Nginx将按照配置中的规则将请求转发给pod（实现HTTP层的业务路由机制），而不再经过kube-proxy
-----------------------------------
K8S做虚拟化 面试题 k8s常见面试题
https://blog.51cto.com/u_14224/9285358
![alt text](image-7.png)

- K8s的Ingress资源对象，用于将不同URL的访问请求转发到后端不同的Service，以实现HTTP层的业务路由机制。K8s使用了Ingress策略和Ingress Controller，两者结合并实现了一个完整的Ingress负载均衡器。使用Ingress进行负载分发时，Ingress Controller基于Ingress规则将客户端请求直接转发到Service对应的后端Endpoint（Pod）上，从而跳过kube-proxy的转发功能，kube-proxy不再起作用，全过程为：ingress controller + ingress 规则 ----> services；

### 简述Kubernetes Scheduler使用哪两种算法将Pod绑定到worker节点?

- 1）预选（Predicates）：输入是所有节点，输出是满足预选条件的节点。kube-scheduler根据预选策略过滤掉不满足策略的Nodes。如果某节点的资源不足或者不满足预选策略的条件则无法通过预选；

- 2）优选（Priorities）：输入是预选阶段筛选出的节点，优选会根据优先策略为通过预选的Nodes进行打分排名，选择得分最高的Node。例如，资源越富裕、负载越小的Node可能具有越高的排名；
-----------------------------------
K8S做虚拟化 面试题 k8s常见面试题
https://blog.51cto.com/u_14224/9285358