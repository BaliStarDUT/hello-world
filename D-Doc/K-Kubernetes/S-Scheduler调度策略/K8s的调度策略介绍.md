# k8s的调度策略有哪些？
- 是否能实现用尽1台主机后，再用另1台，而不是均匀分布
是的，Kubernetes **默认调度策略是“尽量均匀分布”**（通过 `SelectorSpreadPriority` / `PodTopologySpread` 实现高可用），但 **可以通过配置实现“用尽一台主机再用下一台”的调度行为**，这种策略通常称为 **“Bin Packing”（装箱调度）**。

---

## ✅ 一、K8s 默认调度策略（均匀分布）

Kubernetes 调度器（kube-scheduler）默认使用以下策略：

| 策略 | 作用 |
|------|------|
| **LeastRequestedPriority** | 优先调度到 **资源剩余最多** 的节点（倾向于均匀） |
| **BalancedResourceAllocation** | 平衡 CPU 和内存使用率 |
| **SelectorSpreadPriority**（旧）**PodTopologySpread**（新） | 避免 Pod 集中在少数节点（提高容错性） |

> 📌 **结果**：Pod 会被尽量分散到不同节点，避免单点故障。

---

## ✅ 二、如何实现“用尽一台再用下一台”？（Bin Packing）

要实现 **“先填满一个节点，再用下一个”**，核心是 **修改调度器的打分策略（Score Plugin）**，让 **资源使用率高的节点得分更高**。

### 方法 1：使用 `MostAllocated` 打分插件（推荐 ✅）

从 Kubernetes **v1.18+** 开始，官方提供了 `MostAllocated` 插件，**直接支持 Bin Packing 行为**。

#### 步骤：
1. **创建自定义调度器配置文件**（如 `scheduler-config.yaml`）：
   ```yaml
   apiVersion: kubescheduler.config.k8s.io/v1beta3
   kind: KubeSchedulerConfiguration
   profiles:
     - schedulerName: default-scheduler
       plugins:
         score:
           disabled:
             - name: "NodeResourcesLeastAllocated"  # 禁用默认的“最少分配”策略
           enabled:
             - name: "NodeResourcesMostAllocated"    # 启用“最多分配”策略
               weight: 1
   ```

2. **挂载配置并重启 kube-scheduler**
   ```yaml
   # 在 kube-scheduler Pod 中
   spec:
     containers:
       - command:
           - kube-scheduler
           - --config=/etc/kubernetes/scheduler-config.yaml
         volumeMounts:
           - name: scheduler-config
             mountPath: /etc/kubernetes/scheduler-config.yaml
             subPath: scheduler-config.yaml
     volumes:
       - name: scheduler-config
         configMap:
           name: scheduler-config
   ```

> ✅ **效果**：  
> 调度器会优先选择 **CPU/内存已使用比例最高** 的节点，从而实现“填满一台再用下一台”。

---

### 方法 2：使用 `Descheduler` + 自定义策略（间接实现）

如果无法修改调度器（如托管集群 EKS/AKS/GKE），可用 **Descheduler** 主动驱逐 Pod，强制合并。

1. 安装 [Descheduler](https://github.com/kubernetes-sigs/descheduler)
2. 配置 `PodDensity` 或 `LowNodeUtilization` 策略：
   ```yaml
   strategies:
     LowNodeUtilization:
       enabled: true
       params:
         nodeResourceUtilizationThresholds:
           thresholds:
             cpu: 30
             memory: 30
           targetThresholds:
             cpu: 50
             memory: 50
   ```
3. Descheduler 会将低负载节点上的 Pod 驱逐，让它们被重新调度到高负载节点。

> ⚠️ 缺点：Pod 会短暂中断，不适合有状态服务。

---

### 方法 3：使用 Taints/Tolerations + Node Selector（手动控制）

适用于 **少量关键应用**，不适用于通用场景：

1. 给 Node-1 打标签：
   ```bash
   kubectl label node node-1 usage=primary
   ```
2. 应用只调度到 `usage=primary` 的节点：
   ```yaml
   spec:
     template:
       spec:
         nodeSelector:
           usage: primary
   ```
3. 当 Node-1 资源不足时，手动给 Node-2 打相同标签。

> ❌ 缺点：需人工干预，无法自动扩展。

---

## ✅ 三、验证 Bin Packing 效果

部署多个 Pod 后，观察节点资源使用情况：

```bash
# 查看各节点分配的 CPU/内存
kubectl top nodes

# 查看 Pod 分布
kubectl get pods -o wide
```

**Bin Packing 结果示例**：
```
NODE      CPU(cores)   MEMORY(bytes)
node-1    7.8/8        15Gi/16Gi   ← 几乎用满
node-2    0.2/8        0.5Gi/16Gi  ← 几乎空闲
```

而默认策略会是：
```
node-1    4.0/8
node-2    4.0/8
```

---

## ⚠️ 四、注意事项

| 问题 | 建议 |
|------|------|
| **单点故障风险** | Bin Packing 降低高可用性，仅适用于可容忍节点故障的场景（如批处理任务、无状态 Web 服务） |
| **资源碎片** | 大 Pod 可能因单个节点剩余资源不足而无法调度 → 建议配合 `PodTopologySpread` 限制最大集中度 |
| **托管集群限制** | AWS EKS / Azure AKS / GKE 可能不允许自定义调度器 → 改用 Descheduler 或自定义调度器（如 Karpenter） |

---

## ✅ 五、高级方案：使用 Karpenter（推荐用于弹性场景）

[Karpenter](https://karpenter.sh/) 是 AWS 开源的 **高性能调度器**，原生支持 Bin Packing：

```yaml
# Provisioner 配置
spec:
  consolidation:
    enabled: true  # 自动合并低利用率节点
  ttlSecondsAfterEmpty: 30  # 空节点 30 秒后销毁
```

> ✅ 优势：自动扩缩容 + Bin Packing + 快速调度（毫秒级）

---

## ✅ 总结

| 目标 | 解决方案 |
|------|--------|
| **用尽一台再用下一台** | ✅ 启用 `NodeResourcesMostAllocated` 插件 |
| **托管集群无法改调度器** | ✅ 使用 Descheduler 驱逐低负载 Pod |
| **需要自动扩缩容 + Bin Packing** | ✅ 使用 Karpenter |
| **临时手动控制** | ⚠️ 使用 nodeSelector + taints（不推荐长期使用） |

> 💡 **最佳实践**：  
> - 对 **无状态、可中断任务** 使用 Bin Packing 提高资源利用率  
> - 对 **关键业务** 保留默认均匀调度以保证高可用  
> - 生产环境建议结合 **HPA + Cluster Autoscaler** 实现弹性

通过以上方法，你可以灵活控制 Kubernetes 的调度行为，满足不同业务场景的需求。

# 调度策略切换是否影响稳定性？
调度策略的切换**可能影响集群稳定性**，但**在合理操作和充分评估的前提下，风险是可控的**。是否造成影响，取决于 **切换方式、策略类型、集群负载状态及业务容忍度**。

---

## ✅ 一、什么情况下会影响稳定性？

### 1. **直接修改默认调度器配置（高风险）**
- 如果你**重启 kube-scheduler 并加载新配置**（如从 `LeastAllocated` 切换到 `MostAllocated`）：
  - ✅ **对已运行的 Pod 无影响**（调度器只参与新建/未调度 Pod 的决策）
  - ⚠️ **但新 Pod 调度行为突变**：可能导致：
    - 资源集中 → 单节点过载（CPU/内存打满、OOM）
    - 网络或 I/O 瓶颈（如所有写密集型 Pod 跑在同一台磁盘慢的机器上）
    - 高可用性下降（多个副本集中在同一节点，节点宕机导致服务中断）

### 2. **使用 Descheduler 主动驱逐 Pod（中高风险）**
- Descheduler 会 **强制删除正在运行的 Pod**，触发重建：
  - ❌ 导致**服务短暂中断**（即使有 readiness probe）
  - ❌ 有状态应用（如数据库主从）可能因 Pod 重建顺序错乱而脑裂
  - ❌ 频繁驱逐增加 API Server 压力

### 3. **多调度器共存配置错误（低风险但隐蔽）**
- 若同时运行 default-scheduler 和 custom-scheduler，但 Pod 未指定 `schedulerName`：
  - 可能出现 **部分 Pod 被错误调度**，引发资源争抢或亲和性冲突

---

## ✅ 二、如何安全切换调度策略？（最佳实践）

### ✅ 原则：**渐进式、可回滚、可观测**

#### 步骤 1：**在测试环境验证**
- 模拟生产负载，验证新策略是否导致：
  - 节点资源超限
  - Pod 启动失败（Pending）
  - 服务 SLA 下降

#### 步骤 2：**使用自定义调度器（而非修改默认）**
```yaml
# 创建专用调度器（如 binpack-scheduler）
apiVersion: v1
kind: Pod
spec:
  schedulerName: binpack-scheduler  # 仅特定应用使用新策略
```
> ✅ 优势：隔离风险，不影响现有业务。

#### 步骤 3：**灰度切换 + 监控告警**
- 先对**非核心业务**启用新策略
- 监控关键指标：
  - 节点 CPU/Memory 使用率（`kubectl top nodes`）
  - Pod Pending 率（`kube_pod_status_phase{phase="Pending"}`）
  - 节点 NotReady 事件
  - 应用错误率 / 延迟（APM 工具）

#### 步骤 4：**避免在业务高峰期操作**
- 选择维护窗口期执行

#### 步骤 5：**准备回滚方案**
- 保留旧调度器配置
- 如遇问题，快速切回并排查

---

## ✅ 三、不同切换方式的风险对比

| 切换方式 | 影响范围 | 中断风险 | 推荐场景 |
|--------|--------|--------|--------|
| **修改默认调度器配置** | 全集群新 Pod | 低（无 Pod 重建） | 测试集群 / 全新业务集群 |
| **使用自定义调度器** | 仅指定 `schedulerName` 的 Pod | 极低 | 生产环境（推荐✅） |
| **Descheduler 驱逐** | 已运行的 Pod | 高（Pod 重建） | 资源优化（需容忍中断） |
| **Karpenter 等替代调度器** | 新扩缩容节点 | 中（自动扩缩） | 弹性计算场景（如批处理） |

---

## ✅ 四、特别注意：Bin Packing（装箱调度）的稳定性隐患

若切换为 **“用尽一台再用下一台”** 策略，需警惕：

| 风险 | 缓解措施 |
|------|--------|
| **单节点故障导致多服务中断** | 对关键应用仍使用 `PodTopologySpread` 强制分散 |
| **大 Pod 无法调度（资源碎片）** | 设置合理的 `requests/limits`，避免超大容器 |
| **节点资源耗尽后新 Pod 全部 Pending** | 配合 Cluster Autoscaler 自动扩容节点 |

> 💡 建议：**不要全局启用 Bin Packing**，而是通过 `schedulerName` 仅对**可容忍集中部署的工作负载**（如离线计算任务）使用。

---

## ✅ 总结

| 问题 | 回答 |
|------|------|
| **调度策略切换会影响稳定性吗？** | **可能影响，但可控制** |
| **已运行的 Pod 会被重新调度吗？** | **不会！调度器只影响未调度的 Pod** |
| **最安全的切换方式是什么？** | **使用自定义调度器 + 灰度发布 + 监控** |
| **能否在生产环境切换？** | **可以，但必须遵循变更管理流程** |

> 🌟 **核心建议**：  
> **“调度策略是集群的‘交通规则’，改规则前要先清空路口（测试），再逐步放行（灰度），全程盯紧摄像头（监控）。”**

只要做好评估、隔离和回滚准备，调度策略切换完全可以安全实施。