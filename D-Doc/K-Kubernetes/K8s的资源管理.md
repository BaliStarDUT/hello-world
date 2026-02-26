# K8s的内存资源不可压缩性
在 Kubernetes 中，像 CPU 这样的资源被称作“可压缩资源”（compressible resources）。它的典型特点是，当可压缩资源不足时，Pod 只会“饥饿”，但不会退出。而像内存这样的资源，则被称作“不可压缩资源（incompressible resources）。当不可压缩资源不足时，Pod 就会因为 OOM（Out-Of-Memory）被内核杀掉。

#  K8s 中的 Request 与 Limit
在 Kubernetes 中，requests 和 limits 是容器资源管理的核心参数，用于控制 CPU 和 内存 的分配与使用。它们不仅影响 调度策略，还直接关系到 运行时资源限制 和 Pod 稳定性。

requests 表示容器运行所需的最小资源量，调度器会基于该值选择节点； limits 表示容器可使用的最大资源量，运行时由 Linux cgroup 限制，防止资源过度占用。

示例配置：
```
resources:
    requests:
        cpu: "500m"
        memory: "256Mi"
    limits:
        cpu: "1"
        memory: "512Mi"
```

CPU 超过 limits → 不会被杀死，但会被限流（throttling）。

内存超过 limits → 触发 OOM Kill，容器被终止并重启。

底层实现机制：

cgroup 控制组： CPU：通过 cpu.cfs_quota_us / cpu.cfs_period_us 控制配额。 内存：通过 memory.limit_in_bytes 限制最大可用内存。

容器运行时（Docker、containerd）在启动容器时将 requests/limits 转换为 cgroup 配置。

调度与 QoS 影响：

调度器仅参考 requests，确保节点有足够资源。

QoS 类别： Guaranteed：requests = limits 且全部容器都设置。 Burstable：部分容器设置 requests。 BestEffort：未设置 requests/limits，最容易被驱逐。

最佳实践：

同时设置 requests 与 limits，避免调度失衡与 OOM 风险。

requests ≈ 平均负载，limits > 峰值负载，留有余量。

对关键业务 Pod 设为 Guaranteed，提高稳定性。

使用历史监控数据（如 Prometheus）优化参数。

在项目级别配置 ResourceQuota 与 LimitRange，强制规范。

总结： 合理配置 requests 与 limits 能在 资源利用率 与 系统稳定性 之间取得平衡。requests 决定“能否调度”，limits 决定“能用多少”，两者配合才能避免资源争抢、性能下降或服务中断。

了解详细信息:
1 - [kubesphere.io](https://www.kubesphere.io/zh/blogs/deep-dive-into-the-k8s-request-and-limit/)
2 - juejin.cn
3 - xie.infoq.cn