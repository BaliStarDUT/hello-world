Python 标准库提供了丰富的高级数据结构，这些结构在基础数据类型（列表、字典、元组等）之上扩展了更多功能，适用于复杂场景。以下是常用的高级数据结构及其特点：


### 一、`collections` 模块（核心高级数据结构）
#### 1. `defaultdict`  
- **功能**：字典的子类，为不存在的键提供默认值，避免 `KeyError`。  
- **场景**：统计、分组、构建嵌套结构（如列表/集合字典）。  
```python
from collections import defaultdict
d = defaultdict(list)  # 默认为空列表
d["a"].append(1)  # 无需先初始化列表
```

#### 2. `OrderedDict`（Python 3.7+ 后字典默认有序，仍有特殊用途）  
- **功能**：保持键值对插入顺序，提供 `move_to_end()`、`popitem(last=False)` 等操作。  
- **场景**：需要明确顺序的字典（如 LRU 缓存淘汰策略）。  

#### 3. `deque`  
- **功能**：双端队列，支持 O(1) 时间复杂度的首尾元素添加/删除，比列表更高效。  
- **场景**：队列、栈、滑动窗口、生产者-消费者模型。  
```python
from collections import deque
q = deque(maxlen=3)  # 固定长度，超出自动丢弃旧元素
q.append(1)
q.appendleft(2)  # 左侧添加
```

#### 4. `Counter`  
- **功能**：字典的子类，用于计数可哈希对象，提供统计相关方法。  
- **场景**：词频统计、元素计数、TopN 分析。  
```python
from collections import Counter
cnt = Counter(["a", "a", "b"])
cnt.most_common(1)  # 取出现次数最多的1个元素：[("a", 2)]
```

#### 5. `namedtuple`  
- **功能**：创建带字段名的元组子类，兼具元组的不可变性和类的可读性。  
- **场景**：替代元组（增强代码可读性）、轻量级数据对象（如坐标、记录）。  
```python
from collections import namedtuple
Point = namedtuple("Point", ["x", "y"])
p = Point(1, 2)
print(p.x, p.y)  # 1 2
```

#### 6. `ChainMap`  
- **功能**：将多个字典/映射合并为一个视图，无需创建新字典，查找时按顺序搜索。  
- **场景**：多配置合并（如默认配置 + 用户配置）、参数优先级处理。  


### 二、`heapq` 模块（堆结构）  
- **功能**：实现小根堆（最小堆），提供堆的插入、弹出、合并等操作。  
- **场景**：优先队列、TopK 问题、堆排序。  
```python
import heapq
heap = [3, 1, 2]
heapq.heapify(heap)  # 构建小根堆
heapq.heappop(heap)  # 弹出最小元素 1
```


### 三、`bisect` 模块（有序序列工具）  
- **功能**：提供二分查找算法，用于维护有序列表（插入、查找均为 O(log n)）。  
- **场景**：有序集合、范围查询、时间序列数据。  
```python
import bisect
lst = [1, 3, 5]
bisect.insort(lst, 4)  # 插入后保持有序：[1, 3, 4, 5]
bisect.bisect_left(lst, 3)  # 查找元素3的位置：1
```


### 四、`array` 模块（高效数组）  
- **功能**：存储同类型数据的数组，比列表更节省内存，支持快速数值操作。  
- **场景**：大量同类型数据（如整数、浮点数）的存储和计算。  
```python
import array
arr = array.array("i", [1, 2, 3])  # "i" 表示int类型
```


### 五、`queue` 模块（线程安全队列）  
- **功能**：提供线程安全的队列结构，支持多线程间的数据传递。  
- **主要结构**：  
  - `Queue`：FIFO 队列，支持阻塞/超时操作。  
  - `LifoQueue`：栈结构（后进先出）。  
  - `PriorityQueue`：优先队列（按元素优先级排序）。  
- **场景**：多线程并发编程、任务调度。  


### 六、`sched` 模块（事件调度器）  
- **功能**：实现基于时间的事件调度，支持延迟执行和周期性任务。  
- **场景**：定时任务、事件驱动编程。  


### 七、`types` 模块（特殊类型）  
- **功能**：提供特殊数据类型，如 `SimpleNamespace`（简化的属性访问对象）。  
```python
from types import SimpleNamespace
obj = SimpleNamespace(name="test", value=10)
print(obj.name)  # "test"
```


### 总结：适用场景速查表  
| 需求场景                | 推荐数据结构               |
|-------------------------|----------------------------|
| 高效双端操作            | `collections.deque`        |
| 计数/统计               | `collections.Counter`      |
| 带字段名的元组          | `collections.namedtuple`   |
| 多字典合并              | `collections.ChainMap`     |
| 优先队列/TopK           | `heapq` 堆                 |
| 有序列表维护            | `bisect` + 列表            |
| 线程安全通信            | `queue.Queue`              |
| 同类型数据高效存储      | `array.array`              |

这些结构覆盖了大部分复杂场景，避免了重复造轮子，是 Python 高效编程的重要工具。