在 Java 中，`synchronized` 和 `ReentrantLock` 都是实现线程同步的核心机制，用于解决多线程并发访问共享资源的问题。但它们的底层实现有显著差异，涉及 JVM 指令、操作系统内核态操作等不同层面。


### 一、`synchronized` 的底层实现
`synchronized` 是 Java 原生的关键字，依赖 JVM 实现同步，其底层通过 **对象头（Mark Word）**、**监视器锁（Monitor）** 和 **字节码指令** 协同工作。

#### 1. 核心原理：对象头与 Monitor
- **对象头（Mark Word）**：Java 中每个对象都有一个对象头，其中 `Mark Word` 字段存储了对象的锁状态信息（如无锁、偏向锁、轻量级锁、重量级锁）。  
  - 64位JVM中，Mark Word结构如该文所属：https://cloud.tencent.com/developer/article/1824223
  - 32位 JVM 中，`Mark Word` 结构（简化）：  
    ```
    无锁状态：| hashcode (25位) | 分代年龄 (4位) | 是否偏向锁 (1位：0) | 锁标志位 (2位：01) |
    偏向锁：  | 线程ID (23位)   | Epoch (2位)    | 是否偏向锁 (1位：1) | 锁标志位 (2位：01) |
    轻量级锁：| 指向栈中锁记录的指针 (30位)        | 锁标志位 (2位：00) |
    重量级锁：| 指向 Monitor 的指针 (30位)         | 锁标志位 (2位：10) |
    ```

- **监视器锁（Monitor）**：也称为“内部锁”，是 `synchronized` 实现同步的核心。每个对象都关联一个 Monitor（C++ 实现的 `ObjectMonitor` 结构体），其核心字段包括：  
  - `_owner`：持有锁的线程 ID；  
  - `_WaitSet`：等待锁的线程队列（调用 `wait()` 后进入）；  
  - `_EntryList`：竞争锁失败的线程队列（阻塞状态）；  
  - `_recursions`：重入次数（支持可重入锁）。  


#### 2. 锁的升级过程（优化机制）
JVM 对 `synchronized` 做了三级优化，避免直接使用重量级锁（性能差），锁的升级过程不可逆：  
1. **偏向锁**：  
   - 适用场景：单线程重复获取锁（无竞争）。  
   - 实现：第一次获取锁时，将 `Mark Word` 中的线程 ID 设置为当前线程 ID，后续该线程再次获取锁时，只需判断线程 ID 是否匹配（无需 CAS 操作），几乎无开销。  
   - 撤销：当其他线程尝试竞争锁时，偏向锁会升级为轻量级锁。  

2. **轻量级锁**：  
   - 适用场景：多线程交替获取锁（竞争不激烈）。  
   - 实现：线程获取锁时，在栈中创建“锁记录（Lock Record）”，并通过 CAS 将 `Mark Word` 指向该锁记录。若 CAS 成功，当前线程持有锁；若失败（存在竞争），升级为重量级锁。  

3. **重量级锁**：  
   - 适用场景：多线程激烈竞争锁。  
   - 实现：依赖操作系统的 **互斥量（Mutex）** 实现，线程竞争失败会进入内核态阻塞（`_EntryList` 队列），上下文切换成本高（微秒级）。  


#### 3. 字节码层面的体现
`synchronized` 代码块在编译后会生成 `monitorenter` 和 `monitorexit` 指令：  
```java
public void syncMethod() {
    synchronized (this) { // 同步代码块
        System.out.println("sync");
    }
}
```
编译后的字节码（简化）：  
```
monitorenter  // 进入同步块，尝试获取 Monitor
...           // 执行同步代码
monitorexit   // 退出同步块，释放 Monitor
monitorexit   // 异常情况下的退出（确保锁释放）
```

同步方法则通过方法常量池的 `ACC_SYNCHRONIZED` 标志实现，调用方法时 JVM 会自动获取/释放 Monitor。


### 二、`ReentrantLock` 的底层实现
`ReentrantLock` 是 JDK 提供的工具类（位于 `java.util.concurrent.locks`），基于 **AQS（AbstractQueuedSynchronizer，抽象队列同步器）** 实现，完全由 Java 代码控制，灵活性更高。

#### 1. 核心原理：AQS 框架
AQS 是 JUC 中同步工具的基础（如 `ReentrantLock`、`CountDownLatch`），其核心是通过 **volatile 状态变量** 和 **CLH 双向队列** 实现同步：  
- **状态变量（state）**：`volatile int state` 表示锁的持有状态（0 表示无锁，>0 表示重入次数）。  
- **CLH 队列**：竞争锁失败的线程会被封装为 `Node` 节点，加入双向队列（FIFO），通过自旋 + CAS 等待锁释放（避免直接阻塞）。  


#### 2. `ReentrantLock` 的实现细节
- **锁的获取**：  
  调用 `lock()` 时，通过 AQS 的 `acquire(1)` 方法尝试获取锁：  
  1. 若 `state == 0`（无锁），当前线程通过 CAS 将 `state` 设为 1，成功则持有锁（设置 `exclusiveOwnerThread` 为当前线程）。  
  2. 若当前线程已持有锁（可重入），则 `state += 1`。  
  3. 若获取失败，当前线程进入 CLH 队列，通过自旋 + CAS 等待（或阻塞）。  

- **锁的释放**：  
  调用 `unlock()` 时，通过 `release(1)` 方法释放锁：  
  1. `state -= 1`，若 `state == 0`，则释放锁（设置 `exclusiveOwnerThread` 为 null）。  
  2. 唤醒 CLH 队列中的下一个线程竞争锁。  


#### 3. 公平锁与非公平锁
`ReentrantLock` 支持两种锁模式（构造函数指定 `fair` 参数）：  
- **非公平锁**（默认）：线程获取锁时，先尝试直接 CAS 抢占锁（不按队列顺序），失败后再入队。性能更高（减少上下文切换），但可能导致线程饥饿。  
- **公平锁**：线程必须按 CLH 队列的顺序获取锁，严格遵守 FIFO，避免饥饿，但性能略低（每次获取锁需检查队列）。  


### 三、`synchronized` 与 `ReentrantLock` 的底层对比
| 特性                | `synchronized`                          | `ReentrantLock`                          |
|---------------------|-----------------------------------------|------------------------------------------|
| **实现层面**        | 依赖 JVM 底层（C++ 实现 Monitor）       | 基于 Java 代码（AQS 框架）               |
| **锁类型**          | 可重入锁、非公平锁（早期），后期支持偏向锁优化 | 可重入锁，支持公平/非公平模式（手动指定） |
| **锁升级**          | 偏向锁 → 轻量级锁 → 重量级锁（自动升级） | 无锁升级，直接基于 AQS 队列竞争          |
| **释放机制**        | 自动释放（代码块/方法结束或异常）        | 手动释放（必须在 `finally` 中调用 `unlock()`） |
| **灵活性**          | 低（仅支持基本同步）                    | 高（支持超时、中断、条件变量 `Condition`） |
| **性能**            | JDK 1.6+ 优化后与 `ReentrantLock` 接近  | 非公平模式下略高，公平模式略低           |
| **适用场景**        | 简单同步场景（如方法/代码块同步）        | 复杂同步场景（如超时获取、中断、多条件） |


### 四、总结
- `synchronized` 是 JVM 原生支持的关键字，通过对象头和 Monitor 实现，自动管理锁的获取与释放，适用于简单同步场景，得益于 JVM 优化（偏向锁、轻量级锁），性能已足够优秀。  
- `ReentrantLock` 基于 AQS 框架，完全由 Java 代码实现，支持公平锁、超时获取、中断等高级特性，灵活性更高，适合复杂并发场景，但需手动释放锁（否则可能导致死锁）。  

两者的底层核心都是通过“状态标记”和“等待队列”实现线程同步，区别在于 `synchronized` 更依赖 JVM 底层优化，而 `ReentrantLock` 提供了更精细的控制能力。