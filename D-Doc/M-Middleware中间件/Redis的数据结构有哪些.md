Redis 提供了 8 种核心数据结构，每种结构都针对特定场景设计，覆盖了缓存、计数、队列、分布式锁等绝大多数业务需求。以下是各数据结构的详细介绍，包括核心特性、典型场景和基础操作：


### 1. String（字符串）
**核心特性**：Redis 最基础的数据结构，存储二进制安全的字符串（可存文本、图片二进制等），单个键最大存储 512MB；支持「字符串、整数、浮点数」三种存储形式，可直接对整数/浮点数进行增减操作。  
**典型场景**：  
- 缓存用户信息、商品详情（序列化后的 JSON 字符串）；  
- 计数（如文章阅读量、接口调用次数）；  
- 分布式锁的基础（用 `SET NX` 命令实现）。  
**基础操作**：  
- 设值：`SET key value`（如 `SET user:100 "{name:'Alice'}"`）；  
- 取值：`GET key`；  
- 整数增减：`INCR key`（自增 1）、`DECRBY key 5`（自减 5）；  
- 浮点数增减：`INCRBYFLOAT key 2.5`。


### 2. Hash（哈希）
**核心特性**：存储「键值对集合」，类似 Java 的 `HashMap` 或 Python 的 `dict`；键（field）和值（value）均为字符串类型，适合存储结构化数据（如用户信息、商品属性），可单独操作某个字段，无需修改整个结构。  
**典型场景**：  
- 存储用户信息（如 `user:100` 的 `name`、`age`、`email` 字段）；  
- 商品属性缓存（如 `goods:200` 的 `price`、`stock`、`category`）。  
**基础操作**：  
- 设字段：`HSET key field value`（如 `HSET user:100 name Alice age 25`）；  
- 取字段：`HGET key field`（如 `HGET user:100 name`）；  
- 取所有字段：`HGETALL key`；  
- 删除字段：`HDEL key field`（如 `HDEL user:100 email`）。


### 3. List（列表）
**核心特性**：有序的字符串集合，底层基于「双向链表」实现（Redis 3.2+ 对短列表优化为压缩列表），支持「两端插入/删除」，按插入顺序排序，可作为队列或栈使用。  
**典型场景**：  
- 消息队列（用 `LPUSH` 从左侧入队，`RPOP` 从右侧出队）；  
- 最新消息排行（如用户消息列表，`LPUSH` 新增消息，`LRANGE` 取前 10 条）；  
- 栈（用 `LPUSH` 入栈，`LPOP` 出栈，先进后出）。  
**基础操作**：  
- 左侧插入：`LPUSH key value`（如 `LPUSH msg:100 "hello"`）；  
- 右侧删除：`RPOP key`；  
- 取范围元素：`LRANGE key 0 9`（取索引 0 到 9 的元素，即前 10 条）；  
- 获取长度：`LLEN key`。


### 4. Set（集合）
**核心特性**：无序的字符串集合，底层基于「哈希表」实现，元素唯一（自动去重）；支持交集、并集、差集等数学集合操作，适合需去重或关系计算的场景。  
**典型场景**：  
- 去重（如用户标签去重，`SADD tag:user:100 "sports"`，重复添加无效）；  
- 关系计算（如共同好友：`SINTER user:100:friends user:200:friends`）；  
- 抽奖（`SRANDMEMBER key` 随机取 1 个元素，`SPOP key` 随机删除并返回）。  
**基础操作**：  
- 添加元素：`SADD key value1 value2`（如 `SADD user:100:tags sports music`）；  
- 取所有元素：`SMEMBERS key`；  
- 交集：`SINTER key1 key2`（如 `SINTER u1:friends u2:friends`）；  
- 并集：`SUNION key1 key2`；  
- 判断元素是否存在：`SISMEMBER key value`。


### 5. Sorted Set（有序集合，简称 ZSet）
**核心特性**：在 Set 基础上为每个元素关联一个「分数（score，浮点数）」，按分数从小到大排序（分数相同则按元素字典序排序）；底层用「跳表 + 哈希表」实现，支持快速按分数范围查询，兼顾排序和查找效率。  
**典型场景**：  
- 排行榜（如游戏积分排行：`ZADD rank:game 1000 user:100`，`ZRANGE rank:game 0 9 WITHSCORES` 取前 10 名）；  
- 带权重的消息队列（按分数优先级处理，`ZRANGEBYSCORE key 0 500` 取低优先级消息）；  
- 范围统计（如查询积分 800-1000 的用户：`ZRANGEBYSCORE rank:game 800 1000`）。  
**基础操作**：  
- 添加元素：`ZADD key score1 value1 score2 value2`（如 `ZADD rank 95 Alice 88 Bob`）；  
- 按排名取元素：`ZRANGE key 0 4 WITHSCORES`（取前 5 名，带分数）；  
- 按分数取元素：`ZRANGEBYSCORE key 80 90`（取分数 80-90 的元素）；  
- 元素分数自增：`ZINCRBY key 10 value`（如 `ZINCRBY rank 5 Alice`，Alice 分数+5）。


### 6. Bitmap（位图）
**核心特性**：本质是 String 类型的「位操作扩展」，将字符串的每个字节拆分为 8 个二进制位（bit），每个 bit 对应一个布尔值（0 或 1）；极大节省存储空间（如存储 1000 万个用户的签到状态，仅需约 1.2MB）。  
**典型场景**：  
- 状态标记（如用户签到：`SETBIT sign:20240501 100 1`，表示用户 100 在 2024-05-01 签到）；  
- 基数统计（如统计某 day 签到人数：`BITCOUNT sign:20240501`）；  
- 权限控制（如用 bit 表示用户是否有某功能权限）。  
**基础操作**：  
- 设置位：`SETBIT key offset value`（offset 是位索引，从 0 开始，value 为 0/1）；  
- 获取位：`GETBIT key offset`；  
- 统计 1 的个数：`BITCOUNT key`；  
- 位与/或运算：`BITOP AND destkey key1 key2`（计算两个位图的交集，结果存到 destkey）。


### 7. HyperLogLog（基数统计）
**核心特性**：用于统计「集合的基数（不重复元素个数）」，底层基于概率算法（HyperLogLog 算法），牺牲极小精度（误差率约 0.81%）换取极大的存储空间优化（统计 10 亿个元素仅需约 12KB）；不存储具体元素，仅记录基数。  
**典型场景**：  
- UV 统计（如网站每日独立访客数：`PFADD uv:20240501 user:100 user:200`，`PFCOUNT uv:20240501` 得 UV 数）；  
- 关键词去重计数（如统计某接口调用的不同 IP 数）。  
**基础操作**：  
- 添加元素：`PFADD key value1 value2`；  
- 统计基数：`PFCOUNT key`；  
- 合并多个 HyperLogLog：`PFMERGE destkey key1 key2`（如合并两天的 UV：`PFMERGE uv:20240501-02 uv:20240501 uv:20240502`）。


### 8. Geo（地理空间）
**核心特性**：专门用于存储和操作「地理坐标数据」，支持根据经纬度计算距离、查找指定范围内的元素；底层基于 ZSet 实现（将经纬度编码为分数，按分数排序实现范围查询）。  
**典型场景**：  
- 附近的人（如 `GEOADD user:location 116.40 39.90 user:100` 添加用户 100 的坐标，`GEORADIUS user:location 116.40 39.90 5000 m` 查 5 公里内的用户）；  
- 商家定位（存储商家经纬度，查询用户周边的商家）。  
**基础操作**：  
- 添加坐标：`GEOADD key longitude latitude member`（经度、纬度、元素名）；  
- 计算距离：`GEODIST key member1 member2 m`（单位 m/km/mi/ft）；  
- 范围查询：`GEORADIUS key longitude latitude radius m`（按坐标查范围内元素）；  
- 根据元素查坐标：`GEOPOS key member`。


### 总结：数据结构选择指南
| 数据结构   | 核心优势                  | 典型场景                          |
|------------|---------------------------|-----------------------------------|
| String     | 简单、灵活，支持数值操作  | 缓存、计数、分布式锁              |
| Hash       | 结构化存储，字段独立操作  | 用户信息、商品属性                |
| List       | 有序、双向操作            | 消息队列、最新列表                |
| Set        | 去重、支持集合运算        | 标签去重、共同好友                |
| Sorted Set | 按分数排序，支持范围查询  | 排行榜、带权重队列                |
| Bitmap     | 节省空间，位级操作        | 签到、状态标记                    |
| HyperLogLog| 极小空间统计基数          | UV 统计、去重计数                  |
| Geo        | 地理坐标存储与范围查询    | 附近的人、商家定位                |

选择的核心原则：**根据业务需求匹配数据结构的优势**（如需要排序用 ZSet，需要省空间统计基数用 HyperLogLog），避免过度复杂的实现（如能用 String 解决的场景，不强行用 Hash）。

要不要我帮你整理一份**Redis 数据结构操作速查表**？表格会包含每种结构的核心命令、使用场景和注意事项，方便你快速查阅。