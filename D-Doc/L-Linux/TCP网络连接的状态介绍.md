TCP（Transmission Control Protocol，传输控制协议）是面向连接的、可靠的传输层协议，其连接生命周期通过**11种核心状态**的转换实现，核心逻辑围绕“三次握手建立连接”“数据传输”“四次挥手关闭连接”展开。以下结合RFC标准和实际网络场景，详细介绍TCP状态及转换过程：


### 一、TCP核心状态定义（11种）
TCP状态通过**控制位（SYN、ACK、FIN、RST）** 和**滑动窗口机制**协同切换，每种状态对应连接生命周期的特定阶段，具体含义如下：

| 状态名称          | 英文全称                  | 核心含义                                                                 |
|-------------------|---------------------------|--------------------------------------------------------------------------|
| CLOSED            | Closed                    | 初始/终止状态，连接未建立或已完全关闭，无任何资源占用。                   |
| LISTEN            | Listen                    | 服务器端状态：已监听指定端口，等待客户端发起连接请求（如`bind()`+`listen()`后）。 |
| SYN_SENT          | Syn Sent                  | 客户端状态：已发送SYN报文（连接请求），等待服务器的SYN+ACK报文响应。     |
| SYN_RCVD          | Syn Received              | 服务器端状态：已接收客户端SYN报文，发送SYN+ACK报文后，等待客户端的ACK报文。 |
| ESTABLISHED       | Established               | 连接已建立状态：客户端与服务器可双向传输数据（核心数据传输阶段）。         |
| FIN_WAIT_1        | Fin Wait 1                | 主动关闭方状态：已发送FIN报文（关闭请求），等待对方的ACK报文。           |
| FIN_WAIT_2        | Fin Wait 2                | 主动关闭方状态：已收到对方对FIN的ACK报文，等待对方发送自己的FIN报文。     |
| CLOSE_WAIT        | Close Wait                | 被动关闭方状态：已收到对方的FIN报文，发送ACK报文后，等待应用层确认关闭（准备自己的FIN）。 |
| CLOSING           | Closing                   | 双方同时关闭状态：主动关闭方发送FIN后，未收到ACK却先收到对方的FIN，需等待对方的ACK。 |
| LAST_ACK          | Last Acknowledgment       | 被动关闭方状态：已发送自己的FIN报文，等待对方对FIN的ACK报文（最后一次确认）。 |
| TIME_WAIT         | Time Wait                 | 主动关闭方状态：已收到对方的FIN和ACK，发送最终ACK后，等待2MSL（最大报文段寿命），确保对方收到ACK（避免连接残留）。 |


### 二、TCP状态转换核心流程（三次握手+四次挥手）
TCP状态转换的核心是“建立连接→数据传输→关闭连接”三大阶段，每个阶段对应明确的状态切换路径，以下结合示意图和报文交互说明：


#### 1. 阶段1：三次握手建立连接（CLOSED → ESTABLISHED）
**目的**：客户端与服务器通过三次报文交互，确认双方的“发送/接收能力”，建立可靠连接。  
**状态转换路径**：  
- 服务器端：`CLOSED → LISTEN → SYN_RCVD → ESTABLISHED`  
- 客户端：`CLOSED → SYN_SENT → ESTABLISHED`  

**报文交互与状态切换细节**：  
1. **客户端发起连接**：客户端调用`connect()`，发送`SYN`报文（同步序列号，表明客户端初始序号），自身从`CLOSED`转为`SYN_SENT`，等待服务器响应。  
2. **服务器响应请求**：服务器在`LISTEN`状态下收到`SYN`，立即发送`SYN+ACK`报文（同步自己的序号+确认客户端序号），自身从`LISTEN`转为`SYN_RCVD`，等待客户端确认。  
3. **客户端确认连接**：客户端收到`SYN+ACK`，发送`ACK`报文（确认服务器序号），自身从`SYN_SENT`转为`ESTABLISHED`；服务器收到`ACK`后，从`SYN_RCVD`转为`ESTABLISHED`。  

此时连接建立，双方进入`ESTABLISHED`状态，可双向传输数据。


#### 2. 阶段2：数据传输（ESTABLISHED）
**状态**：双方均处于`ESTABLISHED`状态，通过“滑动窗口”实现流量控制，通过“确认重传”实现可靠传输（如丢包后重发）。  
**说明**：此阶段无状态转换，仅通过`ACK`报文确认数据接收，直到一方发起关闭请求。


#### 3. 阶段3：四次挥手关闭连接（ESTABLISHED → CLOSED）
**目的**：TCP是双向连接，需双方分别关闭“发送通道”，通过四次报文交互确保双方数据都已传输完成。  
**状态转换路径**（假设客户端主动关闭，服务器被动关闭）：  
- 主动关闭方（客户端）：`ESTABLISHED → FIN_WAIT_1 → FIN_WAIT_2 → TIME_WAIT → CLOSED`  
- 被动关闭方（服务器）：`ESTABLISHED → CLOSE_WAIT → LAST_ACK → CLOSED`  

**报文交互与状态切换细节**：  
1. **主动关闭方发FIN**：客户端（如调用`close()`）发送`FIN`报文（关闭发送通道，表明不再发送数据），自身从`ESTABLISHED`转为`FIN_WAIT_1`，等待对方ACK。  
2. **被动关闭方发ACK**：服务器收到`FIN`，发送`ACK`报文（确认关闭客户端发送通道），自身从`ESTABLISHED`转为`CLOSE_WAIT`（此时服务器仍可向客户端发送数据，直到应用层确认关闭）。  
3. **被动关闭方发FIN**：服务器应用层确认无需再发送数据，发送`FIN`报文（关闭自己的发送通道），自身从`CLOSE_WAIT`转为`LAST_ACK`，等待客户端ACK。  
4. **主动关闭方发ACK并等待**：客户端收到`FIN`，发送`ACK`报文（确认关闭服务器发送通道），自身从`FIN_WAIT_2`转为`TIME_WAIT`；服务器收到`ACK`后，从`LAST_ACK`转为`CLOSED`。  
5. **主动关闭方等待2MSL**：`TIME_WAIT`状态需等待**2倍MSL（Maximum Segment Lifetime，最大报文段寿命，通常为1分钟）**，确保：  
   - 服务器能收到最终的ACK（若ACK丢失，服务器会重发FIN，客户端可在TIME_WAIT内再次响应）；  
   - 避免旧连接的残留报文干扰新连接（2MSL足够让网络中所有旧报文失效）。  
   等待结束后，客户端从`TIME_WAIT`转为`CLOSED`，连接完全关闭。


### 三、特殊状态与异常场景
#### 1. CLOSING状态（双方同时关闭）
- **触发场景**：客户端和服务器同时发起关闭请求（均发送FIN）。  
- **状态转换**：双方发送FIN后，未收到ACK却先收到对方的FIN，此时从`FIN_WAIT_1`转为`CLOSING`，等待对方ACK；收到ACK后，直接进入`TIME_WAIT`，无需经过`FIN_WAIT_2`。

#### 2. RST复位（强制关闭，无状态转换）
- **触发场景**：连接异常（如客户端连接不存在的端口、服务器进程崩溃、报文校验错误），一方发送`RST`报文（复位信号）。  
- **效果**：接收方收到`RST`后，直接从当前状态转为`CLOSED`，无需正常四次挥手，属于“强制断开”，可能导致数据丢失。


### 四、TCP状态转换全景图（简化版）
```
# 建立连接
客户端：CLOSED → SYN_SENT → ESTABLISHED
服务器：CLOSED → LISTEN → SYN_RCVD → ESTABLISHED

# 数据传输
双方：ESTABLISHED

# 关闭连接（客户端主动）
客户端：ESTABLISHED → FIN_WAIT_1 → FIN_WAIT_2 → TIME_WAIT → CLOSED
服务器：ESTABLISHED → CLOSE_WAIT → LAST_ACK → CLOSED

# 特殊场景
双方同时关闭：FIN_WAIT_1 → CLOSING → TIME_WAIT → CLOSED
异常复位：任意状态 → RST → CLOSED
```


### 五、关键工具与面试考点
#### 1. 查看TCP状态的工具
- Linux：`netstat -an | grep tcp` 或 `ss -t state <状态名>`（如`ss -t state TIME-WAIT`）；  
- Windows：`netstat -ano | findstr "LISTENING"`。

#### 2. 高频面试考点
- **TIME_WAIT的作用**：2MSL等待的原因（确保ACK送达、避免旧报文干扰）；  
- **CLOSE_WAIT的常见问题**：若服务器长期处于`CLOSE_WAIT`，通常是应用层未调用`close()`（如代码漏洞导致未释放连接）；  
- **三次握手与四次挥手的原因**：三次握手是为了确认双方“发送+接收”能力（避免因超时重传的旧SYN建立无效连接）；四次挥手是因TCP双向通信，需分别关闭两个方向的通道。


理解TCP状态转换是排查网络问题（如连接超时、端口占用、TIME_WAIT堆积）和掌握网络编程的核心，也是后端、网络工程师面试的高频考点。