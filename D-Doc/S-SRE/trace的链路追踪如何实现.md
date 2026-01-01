# trace的链路监控如何实现
链路追踪（Distributed Tracing）是监控分布式系统的核心技术，通过追踪请求在多个服务间的传播路径，还原调用链路、分析性能瓶颈和定位故障。其实现核心是**在请求流经的每个服务节点生成并传递唯一标识（Trace ID）和节点标识（Span ID）**，最终聚合所有节点的日志数据，形成完整的调用链路视图。


### 一、链路追踪的核心概念
在深入实现前，需先明确三个核心概念（以 OpenTelemetry 标准为例）：
- **Trace（追踪）**：一个完整的请求链路，包含从用户请求开始到最终响应的所有服务调用，用唯一的 `Trace ID` 标识。
- **Span（跨度）**：链路中的单个服务/节点的处理过程，是 Trace 的基本单元，包含：
  - `Span ID`：当前节点的唯一标识。
  - `Parent Span ID`：父节点的 Span ID（根节点的 Parent Span ID 为空）。
  - 时间信息：`Start Time`（开始时间）、`End Time`（结束时间），用于计算耗时。
  - 元数据：如服务名、接口名、请求参数、错误信息等。
- **上下文（Context）**：传递 Trace ID、Span ID 等信息的载体，确保跨服务调用时标识不丢失（如通过 HTTP 头、RPC 元数据传递）。


### 二、链路追踪的实现流程（核心四步）
链路追踪的实现可拆解为“**埋点生成 -> 上下文传递 -> 数据收集 -> 存储与可视化**”四个步骤，每个步骤需解决特定问题：


#### 1. 埋点生成：在服务中插入追踪逻辑，生成 Span
埋点是链路追踪的起点，需在服务的关键节点（如接口入口、外部调用出口）自动或手动生成 Span，记录处理过程。

##### （1）埋点方式
- **自动埋点**：通过框架集成（如 Spring Cloud Sleuth、OpenTelemetry Instrumentation），无需修改业务代码，自动拦截 HTTP/RPC 调用（如 Java 的 `javaagent` 字节码增强）。  
  例：Spring Boot 应用通过 `spring-cloud-starter-sleuth` 依赖，自动为 `@RestController` 接口生成 Span。
- **手动埋点**：通过 SDK 在关键业务逻辑中显式创建 Span（如复杂流程中的子任务）。  
  例：OpenTelemetry Java SDK 手动创建 Span：
  ```java
  // 手动创建 Span
  Span span = tracer.spanBuilder("process-order").startSpan();
  try (Scope scope = span.makeCurrent()) {
      // 业务逻辑：处理订单
      processOrder();
  } catch (Exception e) {
      span.recordException(e);  // 记录错误信息
      span.setStatus(StatusCode.ERROR);  // 标记 Span 为错误状态
  } finally {
      span.end();  // 结束 Span，计算耗时
  }
  ```

##### （2）Span 核心信息
每个 Span 需包含：
- 基础标识：`Trace ID`（全链路唯一）、`Span ID`（当前节点唯一）、`Parent Span ID`（父节点关联）。
- 时间戳：开始/结束时间（精确到微秒/纳秒），用于计算耗时（`Duration = End Time - Start Time`）。
- 标签（Tags）：键值对信息，如 `http.method=GET`、`http.url=/api/v1/order`、`db.instance=order-db`（便于筛选和分析）。
- 事件（Events）：关键时间点的日志，如 `{"name": "cache-miss", "timestamp": 1620000000}`（记录缓存未命中事件）。
- 状态（Status）：成功/失败标识（如 `OK` 或 `ERROR`），用于快速定位异常链路。


#### 2. 上下文传递：跨服务传递 Trace/Span ID，确保链路连续
分布式系统中，请求会跨多个服务（如 A -> B -> C），需通过**上下文传递机制**将 Trace ID 和 Span ID 从上游服务传递到下游服务，确保所有 Span 归属同一 Trace。

##### （1）传递方式
- **HTTP 协议**：通过 HTTP 头（Headers）传递，常用头字段：
  - `traceparent`（W3C 标准）：格式为 `00-{trace-id}-{parent-span-id}-01`（如 `00-4bf92f3577b34da6a3ce929d0e0e4736-00f067aa0ba902b7-01`）。
  - 非标准头：如 Jaeger 的 `uber-trace-id`、Zipkin 的 `X-B3-TraceId`/`X-B3-ParentSpanId`。
- **RPC 协议**：通过 RPC 框架的元数据（Metadata）传递，如 gRPC 的 `Metadata`、Dubbo 的 `Attachment`。
- **消息队列**：通过消息的属性（Properties）传递，如 Kafka 的 `headers`、RabbitMQ 的 `messageProperties`。

##### （2）实现示例（HTTP 调用）
- **上游服务（A 调用 B）**：  
  发起 HTTP 请求时，从当前上下文提取 `traceparent`，添加到请求头：
  ```java
  // 上游服务：创建 HTTP 请求时传递 traceparent
  HttpURLConnection connection = (HttpURLConnection) new URL("http://service-b/api").openConnection();
  // 从当前上下文获取 traceparent
  String traceParent = Span.current().getSpanContext().getTraceParent();
  connection.setRequestProperty("traceparent", traceParent);
  ```

- **下游服务（B 接收请求）**：  
  接收请求时，从 HTTP 头解析 `traceparent`，提取 Trace ID 和 Parent Span ID，作为当前 Span 的父标识：
  ```java
  // 下游服务：解析请求头中的 traceparent
  String traceParent = request.getHeader("traceparent");
  SpanContext parentContext = TraceContext.parseTraceParent(traceParent);
  // 基于父上下文创建当前 Span
  Span span = tracer.spanBuilder("service-b-handler")
      .setParent(Context.current().with(Span.wrap(parentContext)))
      .startSpan();
  ```


#### 3. 数据收集：将 Span 数据发送到追踪系统
每个服务生成的 Span 需被收集到统一的追踪系统（如 Jaeger、Zipkin、SkyWalking），常见收集方式有：

##### （1）直接上报
服务通过 SDK 直接将 Span 数据（通常为 JSON 格式）发送到追踪系统的后端（如 Jaeger Collector），支持同步（HTTP）或异步（gRPC/Kafka）发送。  
- 优势：架构简单，适合小规模系统。  
- 劣势：服务与追踪系统强耦合，若追踪系统故障可能影响业务。

##### （2）通过代理（Agent）上报
服务将 Span 数据发送到本地代理（如 Jaeger Agent、OpenTelemetry Collector），由代理批量、异步转发到后端。  
- 优势：解耦业务与追踪系统，代理可处理重试、压缩、格式转换（如将 Zipkin 格式转为 Jaeger 格式），减轻服务负担。  
- 架构：`服务 -> Agent（本地进程） -> Collector（集中服务） -> 存储`。

##### （3）数据格式
不同系统的 Span 数据格式略有差异，但核心字段一致，以 OpenTelemetry 的 JSON 格式为例：
```json
{
  "name": "service-a-handler",
  "trace_id": "4bf92f3577b34da6a3ce929d0e0e4736",
  "span_id": "00f067aa0ba902b7",
  "parent_span_id": "",
  "start_time_unix_nano": 1620000000000000000,
  "end_time_unix_nano": 1620000001000000000,
  "attributes": [
    {"key": "http.method", "value": {"string_value": "GET"}},
    {"key": "http.url", "value": {"string_value": "/api/v1/order"}}
  ],
  "status": {"code": "OK"}
}
```


#### 4. 存储与可视化：聚合链路数据，生成可观测视图
收集到的 Span 数据需存储并可视化，以直观展示链路拓扑、耗时分布和异常点。

##### （1）存储方案
- **时序数据库**：如 Cassandra、Elasticsearch（适合存储大量 Span 数据，支持按 Trace ID 快速查询）。  
- **关系型数据库**：如 MySQL（适合小规模场景，查询性能有限）。  
- **专用存储**：如 Jaeger 支持的 Badger（嵌入式 KV 存储，适合单机部署）。

##### （2）可视化功能（以 Jaeger 为例）
- **链路拓扑图**：展示服务间的调用关系（如 A -> B -> C），标注调用次数和平均耗时。  
- **Span 详情列表**：按时间顺序展示链路中的所有 Span，包含每个 Span 的耗时、标签、错误信息。  
- **耗时分布**：用火焰图（Flame Graph）或热力图展示各 Span 的耗时占比，快速定位性能瓶颈（如某个数据库查询耗时过长）。  
- **筛选与搜索**：支持按 Trace ID、服务名、接口名、错误状态等筛选链路，快速定位异常请求。


### 三、主流链路追踪工具与集成
实际应用中，无需重复造轮子，可基于成熟工具实现：

| **工具**         | **特点**                                                                 | **集成方式**                                  |
|------------------|--------------------------------------------------------------------------|-----------------------------------------------|
| **Jaeger**       | CNCF 毕业项目，支持分布式上下文传递、采样策略、根因分析，适合云原生场景。 | 提供多语言 SDK、Agent、Collector，支持 Kubernetes 部署。 |
| **Zipkin**       | 开源早期工具，轻量易用，支持 Zipkin 格式和 W3C 标准。                     | 集成 Spring Cloud Sleuth（自动埋点），支持 HTTP/Kafka 上报。 |
| **SkyWalking**   | 国产工具，支持链路追踪+ metrics+日志，对 Java 生态（如 Dubbo、Spring）支持好。 | 基于探针（Agent）自动埋点，无需修改代码，适合微服务架构。 |
| **OpenTelemetry**| 厂商中立的标准化框架，统一埋点、数据格式和收集，可对接 Jaeger/Zipkin 等后端。 | 提供多语言 SDK 和自动埋点工具（如 Java Agent），支持与主流框架集成。 |


### 四、关键优化：采样策略（避免数据爆炸）
高并发系统中，全量采集 Span 会导致数据量过大（如每秒 10 万请求，每天产生数十亿 Span），需通过**采样策略**控制数据量：
- **固定采样**：按比例采样（如 1%），适合流量稳定的场景。
- **概率采样**：基于随机概率决定是否采样（如 0.1% 概率）。
- **速率限制采样**：限制每秒最大采样数（如每秒 100 条），避免流量峰值冲击。
- **智能采样**：优先采样异常链路（如返回码 5xx）、慢链路（耗时超过阈值），确保关键数据不丢失。


### 五、总结：链路追踪实现要点
1. **埋点**：通过自动或手动方式在服务中生成 Span，记录关键信息。  
2. **传递**：跨服务传递 Trace/Span ID，确保链路连续性（依赖上下文机制）。  
3. **收集**：通过 Agent 或直接上报 Span 数据到追踪系统，平衡性能与可靠性。  
4. **可视化**：用拓扑图、火焰图等展示链路，聚焦性能瓶颈和异常点。  
5. **优化**：通过采样策略控制数据量，避免资源浪费。  

链路追踪是分布式系统可观测性（Observability）的三大支柱之一（另外两个是 Metrics、Logs），与监控、日志结合可全面提升系统的故障排查和性能优化能力。

# golang如何实现trace的链路追踪
在 Go 语言中实现链路追踪，核心是基于 **OpenTelemetry（业界主流标准化框架）** 或 Jaeger/Zipkin 等工具的 SDK，通过“埋点生成 Span、上下文传递、数据上报”三步实现。以下是基于 OpenTelemetry 的完整实现方案，涵盖 HTTP 服务、gRPC 调用、数据库操作等常见场景，兼顾自动埋点与手动埋点。


### 一、核心依赖与环境准备
首先引入 OpenTelemetry 相关依赖（Go Modules 管理），OpenTelemetry 提供了统一的 API、SDK 和 exporter（用于数据上报到追踪系统，如 Jaeger）：
```bash
# 安装核心依赖
go get go.opentelemetry.io/otel@v1.21.0
go get go.opentelemetry.io/otel/trace@v1.21.0
go get go.opentelemetry.io/otel/exporters/jaeger@v1.21.0  # Jaeger  exporter
go get go.opentelemetry.io/contrib/instrumentation/net/http/otelhttp@v0.46.0  # HTTP 自动埋点
go get go.opentelemetry.io/contrib/instrumentation/database/sql/otelsql@v0.46.0  # 数据库自动埋点
```

核心概念与 Go 对应关系：
- **`trace.Tracer`**：生成 Span 的核心对象，每个服务/模块建议创建独立 Tracer。
- **`trace.Span`**：链路中的单个节点，记录耗时、标签、事件等信息。
- **`context.Context`**：Go 语言中传递上下文的载体，用于携带 Trace ID、Span ID 等信息，实现跨函数/跨服务传递。


### 二、Step 1：初始化追踪器（Tracer Provider）
首先需要初始化 OpenTelemetry 的 `TracerProvider`，配置数据上报的目标（如 Jaeger），并将其设为全局 Tracer 提供者（方便后续代码使用）。

#### 示例：初始化 Jaeger  exporter 与 TracerProvider
```go
package main

import (
	"context"
	"log"

	"go.opentelemetry.io/otel"
	"go.opentelemetry.io/otel/exporters/jaeger"
	"go.opentelemetry.io/otel/sdk/resource"
	sdktrace "go.opentelemetry.io/otel/sdk/trace"
	semconv "go.opentelemetry.io/otel/semconv/v1.21.0"
	"go.opentelemetry.io/otel/trace"
)

// 初始化 TracerProvider，连接 Jaeger
func initTracer(jaegerAddr string) (trace.Tracer, func(context.Context) error, error) {
	// 1. 创建 Jaeger exporter（指定 Jaeger 地址，默认 14250 端口）
	exp, err := jaeger.New(jaeger.WithCollectorEndpoint(jaeger.WithEndpoint(jaegerAddr)))
	if err != nil {
		return nil, nil, err
	}

	// 2. 配置 TracerProvider：指定采样策略（这里用全量采样，生产可改概率采样）
	tp := sdktrace.NewTracerProvider(
		sdktrace.WithBatcher(exp),  // 批量上报 Span（提升性能）
		sdktrace.WithResource(resource.NewWithAttributes(
			semconv.SchemaURL,
			// 服务元数据（必选：标识当前服务名称，用于链路拓扑展示）
			semconv.ServiceNameKey.String("go-trace-demo"),
			semconv.ServiceVersionKey.String("v1.0.0"),
		)),
		// 采样策略：生产环境建议用 sdktrace.WithSampler(sdktrace.ParentBased(sdktrace.TraceIDRatioBased(0.1)))（10% 采样）
		sdktrace.WithSampler(sdktrace.AlwaysSample()),
	)

	// 3. 设置全局 TracerProvider（后续通过 otel.Tracer() 获取 Tracer）
	otel.SetTracerProvider(tp)

	// 4. 返回 Tracer、关闭函数（程序退出时调用，确保数据刷盘）
	tracer := otel.Tracer("go-trace-demo")
	shutdown := func(ctx context.Context) error {
		return tp.Shutdown(ctx)
	}
	return tracer, shutdown, nil
}

func main() {
	// 初始化 Tracer（Jaeger 地址：本地部署默认 http://localhost:14268/api/traces）
	tracer, shutdown, err := initTracer("http://localhost:14268/api/traces")
	if err != nil {
		log.Fatalf("init tracer failed: %v", err)
	}
	defer func() {
		if err := shutdown(context.Background()); err != nil {
			log.Fatalf("shutdown tracer failed: %v", err)
		}
	}()

	// 后续业务逻辑（如启动 HTTP 服务）
	// ...
}
```


### 三、Step 2：链路追踪实现场景
Go 中链路追踪主要覆盖 **HTTP 服务、gRPC 调用、数据库操作、自定义业务逻辑** 四类场景，结合“自动埋点”（框架拦截）和“手动埋点”（显式创建 Span）。


#### 场景 1：HTTP 服务自动埋点（最常见）
通过 `otelhttp` 中间件，自动为 HTTP 服务的每个请求生成 Span，无需修改业务 Handler 代码，支持：
- 自动记录 HTTP 方法、URL、状态码、耗时。
- 自动传递上下文（通过 HTTP 头 `traceparent` 携带 Trace ID/Span ID）。

##### 示例：创建带追踪的 HTTP 服务
```go
package main

import (
	"context"
	"fmt"
	"log"
	"net/http"
	"time"

	"go.opentelemetry.io/contrib/instrumentation/net/http/otelhttp"
	"go.opentelemetry.io/otel"
	"go.opentelemetry.io/otel/attribute"
	"go.opentelemetry.io/otel/trace"
)

// 业务 Handler：处理 /api/order 请求
func orderHandler(w http.ResponseWriter, r *http.Request) {
	// 1. 从请求的 Context 中获取当前 Span（由 otelhttp 中间件自动创建）
	ctx := r.Context()
	span := trace.SpanFromContext(ctx)
	defer span.End()  // 虽中间件会自动结束，但手动显式结束更安全

	// 2. （可选）手动添加标签（如订单 ID）
	orderID := r.URL.Query().Get("order_id")
	if orderID != "" {
		span.SetAttributes(attribute.String("order.id", orderID))
	}

	// 3. 业务逻辑：模拟处理订单（调用下游函数，传递 Context）
	if err := processOrder(ctx, orderID); err != nil {
		http.Error(w, fmt.Sprintf("process order failed: %v", err), http.StatusInternalServerError)
		// 记录错误信息到 Span
		span.RecordError(err)
		span.SetStatus(trace.Status{Code: trace.StatusCodeError, Description: err.Error()})
		return
	}

	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "order processed successfully: %s", orderID)
}

// 下游函数：处理订单详情（手动埋点，创建子 Span）
func processOrder(ctx context.Context, orderID string) error {
	// 1. 从全局 Tracer 创建子 Span，父 Span 从 Context 中获取
	tracer := otel.Tracer("go-trace-demo")
	_, span := tracer.Start(ctx, "processOrder")  // 子 Span 名称：processOrder
	defer span.End()

	// 2. 模拟业务耗时（如查询数据库、调用 RPC）
	time.Sleep(100 * time.Millisecond)

	// 3. （可选）记录事件（如“开始查询库存”）
	span.AddEvent("query inventory start", trace.WithAttributes(attribute.String("order.id", orderID)))
	time.Sleep(50 * time.Millisecond)
	span.AddEvent("query inventory end", trace.WithAttributes(attribute.Bool("inventory.enough", true)))

	return nil
}

func main() {
	// 初始化 Tracer（同 Step 1）
	tracer, shutdown, err := initTracer("http://localhost:14268/api/traces")
	if err != nil {
		log.Fatalf("init tracer failed: %v", err)
	}
	defer func() {
		if err := shutdown(context.Background()); err != nil {
			log.Fatalf("shutdown tracer failed: %v", err)
		}
	}()

	// 2. 创建 HTTP 路由，使用 otelhttp 中间件自动埋点
	mux := http.NewServeMux()
	mux.HandleFunc("/api/order", orderHandler)

	// 3. 用 otelhttp.NewHandler 包装 mux，自动为所有请求生成 Span
	// 参数 1：原始 Handler；参数 2：Span 名称前缀（如 "HTTP Server"）；参数 3：是否记录请求体（生产建议关闭）
	handler := otelhttp.NewHandler(mux, "HTTP Server", otelhttp.WithTracerProvider(otel.GetTracerProvider()))

	// 4. 启动 HTTP 服务
	log.Println("HTTP server starting on :8080")
	log.Fatal(http.ListenAndServe(":8080", handler))
}
```


#### 场景 2：HTTP 客户端自动埋点（调用下游服务）
当当前服务需要调用其他 HTTP 服务时，通过 `otelhttp.NewTransport` 包装 HTTP Client，自动传递上下文（`traceparent` 头）并生成“客户端 Span”。

##### 示例：带追踪的 HTTP 客户端
```go
package main

import (
	"context"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"

	"go.opentelemetry.io/contrib/instrumentation/net/http/otelhttp"
	"go.opentelemetry.io/otel"
	"go.opentelemetry.io/otel/trace"
)

// 调用下游 HTTP 服务（如 http://localhost:8081/api/payment）
func callPaymentService(ctx context.Context, orderID string) (string, error) {
	// 1. 创建带 otelhttp 包装的 HTTP Client：自动传递 Context 中的追踪信息
	client := &http.Client{
		Transport: otelhttp.NewTransport(
			http.DefaultTransport,
			otelhttp.WithTracerProvider(otel.GetTracerProvider()),
			otelhttp.WithSpanNameFormatter(func(req *http.Request) string {
				return fmt.Sprintf("HTTP Client: %s %s", req.Method, req.URL.Path)
			}),
		),
	}

	// 2. 创建请求，将 Context 传入（关键：确保追踪信息传递）
	req, err := http.NewRequestWithContext(ctx, "GET", fmt.Sprintf("http://localhost:8081/api/payment?order_id=%s", orderID), nil)
	if err != nil {
		return "", err
	}

	// 3. 发送请求：otelhttp 会自动生成“客户端 Span”，并传递 traceparent 头
	resp, err := client.Do(req)
	if err != nil {
		return "", err
	}
	defer resp.Body.Close()

	// 4. 处理响应
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}
	return string(body), nil
}

// 在之前的 orderHandler 中调用：
func orderHandler(w http.ResponseWriter, r *http.Request) {
	ctx := r.Context()
	span := trace.SpanFromContext(ctx)
	defer span.End()

	orderID := r.URL.Query().Get("order_id")
	span.SetAttributes(attribute.String("order.id", orderID))

	// 调用下游支付服务（传递 Context，自动携带追踪信息）
	paymentResp, err := callPaymentService(ctx, orderID)
	if err != nil {
		http.Error(w, fmt.Sprintf("call payment service failed: %v", err), http.StatusInternalServerError)
		span.RecordError(err)
		span.SetStatus(trace.Status{Code: trace.StatusCodeError, Description: err.Error()})
		return
	}

	w.WriteHeader(http.StatusOK)
	fmt.Fprintf(w, "order processed: %s, payment resp: %s", orderID, paymentResp)
}
```


#### 场景 3：数据库操作自动埋点（SQL）
通过 `otelsql` 包装数据库驱动，自动为 SQL 执行生成 Span，记录 SQL 语句、执行耗时、数据库实例等信息。

##### 示例：带追踪的 MySQL 操作
```go
package main

import (
	"context"
	"database/sql"
	"log"

	_ "github.com/go-sql-driver/mysql"
	"go.opentelemetry.io/contrib/instrumentation/database/sql/otelsql"
	"go.opentelemetry.io/otel"
	"go.opentelemetry.io/otel/attribute"
	semconv "go.opentelemetry.io/otel/semconv/v1.21.0"
)

// 初始化带追踪的 MySQL 连接
func initDB() (*sql.DB, error) {
	// 原始 DSN：user:password@tcp(127.0.0.1:3306)/dbname?parseTime=true
	dsn := "root:123456@tcp(127.0.0.1:3306)/order_db?parseTime=true"

	// 1. 用 otelsql 包装 MySQL 驱动（驱动名：mysql）
	// 参数 1：原始驱动名；参数 2：数据库类型（如 "mysql"）；参数 3：额外标签（如数据库实例名）
	driverName, err := otelsql.Register("mysql", "mysql",
		otelsql.WithAttributes(
			semconv.DBSystemMySQL,  // 标记数据库类型为 MySQL
			attribute.String("db.instance", "order_db"),  // 数据库实例名
		),
		otelsql.WithTracerProvider(otel.GetTracerProvider()),
	)
	if err != nil {
		return nil, err
	}

	// 2. 打开数据库连接（使用包装后的 driverName）
	db, err := sql.Open(driverName, dsn)
	if err != nil {
		return nil, err
	}

	// 3. 验证连接
	if err := db.Ping(); err != nil {
		return nil, err
	}

	return db, nil
}

// 数据库查询操作（自动埋点）
func queryOrder(ctx context.Context, db *sql.DB, orderID string) (string, error) {
	// 执行 SQL：otelsql 会自动从 Context 中获取父 Span，生成“SQL Span”
	row := db.QueryRowContext(
		ctx,
		"SELECT status FROM orders WHERE order_id = ?",  // SQL 语句会被记录到 Span 标签
		orderID,
	)

	var status string
	if err := row.Scan(&status); err != nil {
		return "", err
	}
	return status, nil
}

// 在 processOrder 函数中调用：
func processOrder(ctx context.Context, orderID string) error {
	tracer := otel.Tracer("go-trace-demo")
	_, span := tracer.Start(ctx, "processOrder")
	defer span.End()

	// 初始化 DB（全局初始化一次即可）
	db, err := initDB()
	if err != nil {
		span.RecordError(err)
		return err
	}

	// 查询订单状态（传递 Context，自动生成 SQL Span）
	orderStatus, err := queryOrder(ctx, db, orderID)
	if err != nil {
		span.RecordError(err)
		return err
	}

	// 将订单状态添加到 Span 标签
	span.SetAttributes(attribute.String("order.status", orderStatus))
	return nil
}
```


#### 场景 4：手动埋点（自定义业务逻辑）
对于框架无法自动拦截的场景（如复杂业务计算、缓存操作），通过 `tracer.Start()` 手动创建 Span，核心是“从 Context 继承父 Span，结束时显式调用 `span.End()`”。

##### 示例：手动埋点缓存操作
```go
package main

import (
	"context"
	"time"

	"go.opentelemetry.io/otel"
	"go.opentelemetry.io/otel/attribute"
	"go.opentelemetry.io/otel/trace"
)

// 模拟缓存操作（手动埋点）
func getFromCache(ctx context.Context, key string) (string, bool) {
	// 1. 手动创建子 Span：父 Span 从 Context 中获取，Span 名称为 "getFromCache"
	tracer := otel.Tracer("go-trace-demo")
	_, span := tracer.Start(ctx, "getFromCache")
	defer span.End()  // 必须调用，否则 Span 不会被上报

	// 2. 添加标签（缓存键）
	span.SetAttributes(attribute.String("cache.key", key))

	// 3. 模拟缓存查询耗时
	time.Sleep(20 * time.Millisecond)

	// 4. 模拟缓存命中/未命中（这里返回命中）
	span.AddEvent("cache hit", trace.WithAttributes(attribute.String("cache.value", "100")))
	return "100", true
}