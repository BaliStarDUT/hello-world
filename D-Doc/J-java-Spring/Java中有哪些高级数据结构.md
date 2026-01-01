Java 标准库（`java.util` 包及其子包）提供了丰富的高级数据结构，覆盖了集合、映射、队列、并发等多种场景。以下是常用的高级数据结构及其特点：


### 一、核心集合框架（`java.util`）
#### 1. 列表（List）扩展
- **`ArrayList`**：动态数组，支持随机访问，适合读多写少场景（扩容策略为 1.5 倍）。  
- **`LinkedList`**：双向链表，实现 `List` 和 `Deque` 接口，适合频繁插入/删除（首尾操作 O(1)，中间 O(n)）。  
- **`CopyOnWriteArrayList`**：读多写少场景的线程安全列表，写操作通过复制底层数组实现，读无需锁。  

#### 2. 集合（Set）扩展
- **`HashSet`**：基于哈希表，无序，不允许重复元素（底层用 `HashMap` 实现）。  
- **`LinkedHashSet`**：继承 `HashSet`，维护插入顺序（通过双向链表记录元素顺序）。  
- **`TreeSet`**：基于红黑树（自平衡二叉搜索树），元素自动排序（需实现 `Comparable` 或提供 `Comparator`）。  
- **`CopyOnWriteArraySet`**：线程安全的 Set，底层依赖 `CopyOnWriteArrayList`。  

#### 3. 映射（Map）扩展
- **`HashMap`**：哈希表实现，key 无序，允许 key 为 `null`（JDK 8+ 引入红黑树优化哈希冲突）。  
- **`LinkedHashMap`**：继承 `HashMap`，维护 key 的插入顺序或访问顺序（可用于 LRU 缓存）。  
- **`TreeMap`**：红黑树实现，key 自动排序，支持范围查询（如 `subMap()`、`tailMap()`）。  
- **`WeakHashMap`**：key 为弱引用，当 key 不再被引用时会自动被 GC 回收，适合缓存场景。  
- **`IdentityHashMap`**：用 `==` 而非 `equals()` 判断 key 相等，适用于引用比较场景。  


### 二、队列与栈（`java.util.Queue`、`java.util.Deque`）
- **`ArrayDeque`**：基于动态数组的双端队列（`Deque`），效率高于 `LinkedList`，可作为栈（`push()`/`pop()`）或队列（`add()`/`poll()`）。  
- **`PriorityQueue`**：优先队列，基于小根堆实现，元素按优先级排序（默认自然顺序，可自定义 `Comparator`）。  
- **`LinkedList`**：同时实现 `Deque`，可作为双端队列使用（但性能不如 `ArrayDeque`）。  


### 三、并发安全集合（`java.util.concurrent`）
专为多线程场景设计，提供高效的线程安全实现：  
- **`ConcurrentHashMap`**：并发哈希表，分段锁（JDK 8+ 改为 CAS + synchronized），支持高并发读写。  
- **`ConcurrentSkipListMap`**：并发有序映射，基于跳表实现，支持 O(log n) 时间的增删改查，适合高并发排序场景。  
- **`ConcurrentSkipListSet`**：基于 `ConcurrentSkipListMap` 的并发有序集合。  
- **`LinkedBlockingQueue`**：基于链表的阻塞队列，支持固定容量或无界，适合生产者-消费者模型。  
- **`ArrayBlockingQueue`**：基于数组的有界阻塞队列，初始化时需指定容量。  
- **`SynchronousQueue`**：无缓冲阻塞队列，发送者与接收者必须同步交互（如线程池工作队列）。  
- **`PriorityBlockingQueue`**：支持优先级的无界阻塞队列。  
- **`CopyOnWriteArrayList`/`CopyOnWriteArraySet`**：前文提及的线程安全集合，适合读多写少场景。  


### 四、特殊用途数据结构
- **`BitSet`**：位图结构，用比特位存储布尔值，高效节省内存（如存储大量开关状态、去重）。  
  ```java
  BitSet bits = new BitSet(100);
  bits.set(5);  // 第5位设为true
  bits.get(5);  // 返回true
  ```
- **`Vector`**：线程安全的动态数组（已被 `ArrayList` 替代，仅兼容旧代码）。  
- **`Stack`**：基于 `Vector` 的栈实现（已被 `Deque` 的 `push()`/`pop()` 替代）。  


### 五、工具类与视图
- **`Collections`**：提供集合工具方法，如创建不可变集合（`unmodifiableList()`）、同步集合（`synchronizedList()`）、空集合（`emptyList()`）等。  
- **`Arrays`**：数组工具类，提供数组排序（`sort()`）、二分查找（`binarySearch()`）、转为列表（`asList()`）等功能。  


### 总结：适用场景速查表
| 需求场景                | 推荐数据结构                  |
|-------------------------|-------------------------------|
| 动态数组（读多写少）    | `ArrayList`                   |
| 双端操作/队列/栈        | `ArrayDeque`                  |
| 有序集合/范围查询       | `TreeSet`/`TreeMap`           |
| 哈希存储（无序）        | `HashSet`/`HashMap`           |
| 保持插入顺序            | `LinkedHashSet`/`LinkedHashMap`|
| 并发安全哈希表          | `ConcurrentHashMap`           |
| 阻塞队列（生产消费）    | `LinkedBlockingQueue`         |
| 优先队列                | `PriorityQueue`               |
| 位图存储（节省内存）    | `BitSet`                      |

这些数据结构覆盖了单线程、多线程、有序、无序等多种场景，是 Java 开发中处理复杂数据的基础工具。