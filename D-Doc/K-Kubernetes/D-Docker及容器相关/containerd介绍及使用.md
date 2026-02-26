`containerd` 是一个工业级的容器运行时，由 Docker 贡献给 CNCF（云原生计算基金会），现已成为 Kubernetes 等主流平台默认支持的底层容器引擎。它负责管理容器的生命周期，包括镜像拉取、容器创建、启动、停止、删除等。

虽然 `containerd` 本身不直接面向终端用户设计（不像 `docker` 那样提供高层 CLI），但它提供了命令行工具 `ctr`（containerd CLI）和更友好的 `nerdctl`（Docker 兼容 CLI），可用于日常操作。

---
- 提示：Kubernetes 创建的容器在 k8s.io 命名空间下，普通 ctr 默认在 default
- ctr -n k8s.io containers list
## 一、安装 containerd

### 在 Linux 上（以 Ubuntu/Debian 为例）：
```bash
# 添加官方仓库（可选）
sudo apt update
sudo apt install -y containerd

# 或从 release 下载二进制（推荐生产环境）
wget https://github.com/containerd/containerd/releases/download/v1.7.16/containerd-1.7.16-linux-amd64.tar.gz
sudo tar Cxzvf /usr/local containerd-1.7.16-linux-amd64.tar.gz

# 启动并启用服务
sudo systemctl enable --now containerd
```

> ⚠️ 注意：Kubernetes 用户通常通过 `kubeadm` 自动配置 containerd，无需手动操作。

---

## 二、基本使用方式

### 方式 1：使用 `ctr`（containerd 原生命令行）

`ctr` 是 containerd 自带的 CLI，功能较底层，适合调试。

#### 1. 拉取镜像
```bash
ctr images pull docker.io/library/nginx:latest
```

#### 2. 列出镜像
```bash
ctr images list
```

#### 3. 创建容器（注意：`ctr` 创建的是“任务”前的容器定义）
```bash
ctr containers create \
  --net-host \
  docker.io/library/nginx:latest \
  my-nginx
```

#### 4. 启动容器（作为 task）
```bash
ctr tasks start my-nginx
```

#### 5. 查看运行中的任务
```bash
ctr tasks ls
```

#### 6. 进入容器（需容器内有 shell）
```bash
ctr tasks exec --exec-id $(uuidgen) -t my-nginx /bin/sh
```

> ❌ 缺点：`ctr` 不支持 `-it`、`-d`、端口映射、卷挂载等高级功能，**不适合日常开发使用**。

---

### 方式 2：使用 `nerdctl`（推荐！Docker 兼容 CLI）

`nerdctl` 是 containerd 官方推荐的用户友好 CLI，语法几乎与 `docker` 一致。

#### 安装 nerdctl
```bash
# 下载并解压
wget https://github.com/containerd/nerdctl/releases/download/v1.7.6/nerdctl-1.7.6-linux-amd64.tar.gz
sudo tar Cxzvf /usr/local/bin nerdctl-1.7.6-linux-amd64.tar.gz

# 验证
nerdctl version
```

#### 常用命令（与 docker 几乎相同）
```bash
# 拉取镜像
nerdctl pull nginx

# 运行容器（支持 -d, -p, -v, --name 等）
nerdctl run -d --name web -p 8080:80 nginx

# 列出容器
nerdctl ps

# 查看日志
nerdctl logs web

# 进入容器
nerdctl exec -it web /bin/bash

# 停止/删除
nerdctl stop web
nerdctl rm web

# 构建镜像（需 BuildKit）
nerdctl build -t myapp .
```

> ✅ 优点：完全兼容 Docker CLI 习惯，支持 Compose（`nerdctl compose up`）。

---

## 三、配置 containerd

配置文件通常位于 `/etc/containerd/config.toml`。

生成默认配置：
```bash
containerd config default > /etc/containerd/config.toml
```

常见配置项：
- **镜像仓库加速**（如阿里云、中科大镜像）：
  ```toml
  [plugins."io.containerd.grpc.v1.cri".registry.mirrors]
    [plugins."io.containerd.grpc.v1.cri".registry.mirrors."docker.io"]
      endpoint = ["https://<your-mirror>.mirror.aliyuncs.com"]
  ```
- **启用 SystemdCgroup**（K8s 推荐）：
  ```toml
  [plugins."io.containerd.grpc.v1.cri".containerd.runtimes.runc.options]
    SystemdCgroup = true
  ```

修改后重启：
```bash
sudo systemctl restart containerd
```

---

## 四、与 Kubernetes 集成

Kubernetes 通过 CRI（Container Runtime Interface）调用 containerd。

在 `kubeadm init` 前确保 containerd 已运行，并配置好 CRI socket（默认为 `/run/containerd/containerd.sock`）。

检查节点状态：
```bash
kubectl get nodes
# 如果 NotReady，检查 kubelet 是否指向正确 runtime
```

---

## 五、常用调试命令

| 功能 | 命令 |
|------|------|
| 查看 containerd 状态 | `systemctl status containerd` |
| 查看日志 | `journalctl -u containerd -f` |
| 查看命名空间 | `ctr namespaces list` |
| 切换命名空间（如 k8s 使用 `k8s.io`） | `ctr -n k8s.io containers list` |

> 💡 提示：Kubernetes 创建的容器在 `k8s.io` 命名空间下，普通 `ctr` 默认在 `default`。

---

## 总结

| 工具 | 用途 | 推荐场景 |
|------|------|--------|
| `ctr` | containerd 原生命令 | 调试、底层操作 |
| `nerdctl` | Docker 兼容 CLI | 日常开发、测试 |
| `crictl` | CRI 接口调试工具 | Kubernetes 节点排错 |

✅ **建议**：日常使用优先选择 `nerdctl`，它让 containerd 的体验接近 Docker，同时保持轻量高效。

> 官方文档：  
> - https://containerd.io/  
> - https://github.com/containerd/nerdctl
、

# containerd如何进入容器内运行命令？

在 `containerd` 中，**没有像 Docker 那样直接的 `docker exec` 命令**，但你可以通过其原生命令行工具 `ctr` 或更友好的 `nerdctl` 来进入正在运行的容器并执行命令。

---

## ✅ 推荐方式：使用 `nerdctl exec`（最简单，类似 Docker）

如果你已安装 [`nerdctl`](https://github.com/containerd/nerdctl)（强烈推荐），操作与 Docker 几乎一致：

```bash
# 进入容器并启动交互式 shell
nerdctl exec -it <容器名或ID> /bin/sh

# 例如
nerdctl exec -it my-nginx /bin/bash
```

> ✅ 优点：支持 `-i`（交互）、`-t`（TTY），体验与 `docker exec` 完全一致。  
> 🔧 安装 `nerdctl`：[https://github.com/containerd/nerdctl#install](https://github.com/containerd/nerdctl#install)

---

## ⚙️ 原生方式：使用 `ctr`（底层命令，较复杂）

`ctr` 是 containerd 自带的 CLI，但**不支持直接分配 TTY**，因此无法实现真正的“交互式 shell”，但可以执行单条命令。

### 步骤：

#### 1. **确认容器所在的命名空间**
containerd 支持多命名空间（namespace）。普通用户创建的容器通常在 `default` 命名空间，而 Kubernetes 使用 `k8s.io`。

```bash
# 查看所有命名空间
ctr namespaces list

# 列出 default 命名空间中的容器
ctr -n default containers list

# 如果是 K8s 容器，用：
ctr -n k8s.io containers list
```

#### 2. **执行命令（非交互式）**
```bash
# 语法：ctr -n <namespace> tasks exec --exec-id <唯一ID> <容器名> <命令>
ctr -n default tasks exec --exec-id $(uuidgen) my-nginx ls /usr/share/nginx/html
```

> 📌 注意：
> - `--exec-id` 必须是**唯一字符串**，常用 `$(uuidgen)` 生成（需安装 `uuid-runtime`）。
> - **不能使用 `-it`**，所以无法运行 `sh`、`bash` 等交互式 shell。
> - 如果容器内没有 `uuidgen`，可手动指定 ID，如 `--exec-id exec123`。

#### 3. **尝试交互式（有限支持）**
虽然 `ctr` 不原生支持 TTY，但可加 `-t` 参数（部分版本支持）：
```bash
ctr -n default tasks exec -t --exec-id test123 my-nginx /bin/sh
```
但在大多数情况下，这会**卡住或无法输入**，因为缺少完整的 PTY 支持。

---

## 🔍 如何获取容器名？

- 用 `nerdctl ps`（推荐）
- 或用 `ctr -n default tasks ls` 查看运行中的任务（task 名即容器名）

---

## 🛠️ 实用技巧

### 在 Kubernetes 环境中进入 Pod 容器
K8s 使用 `k8s.io` 命名空间，容器名格式为：
```
<POD_NAME>_<NAMESPACE>_<CONTAINER_NAME>-<RANDOM>
```

示例：
```bash
# 列出 K8s 容器
ctr -n k8s.io tasks ls

# 执行命令（假设容器名为 nginx-deployment-xxx_default_nginx-12345）
ctr -n k8s.io tasks exec --exec-id debug123 \
  nginx-deployment-xxx_default_nginx-12345 \
  cat /etc/os-release
```

> 💡 但生产环境中建议优先使用 `kubectl exec`，而非直接操作 containerd。

---

## ✅ 总结

| 方法 | 命令 | 是否支持交互式 shell | 推荐场景 |
|------|------|------------------|--------|
| **`nerdctl exec`** | `nerdctl exec -it <容器> sh` | ✅ 是 | 日常开发、调试（首选） |
| **`ctr tasks exec`** | `ctr tasks exec --exec-id xxx <容器> cmd` | ❌ 否（仅单条命令） | 底层调试、无 nerdctl 时 |

> **强烈建议安装 `nerdctl`**，它让 containerd 的使用体验接近 Docker，同时保持轻量高效。

🔗 安装参考：  
```bash
# Linux (amd64)
wget https://github.com/containerd/nerdctl/releases/download/v1.7.6/nerdctl-1.7.6-linux-amd64.tar.gz
sudo tar Cxzvf /usr/local/bin nerdctl-1.7.6-linux-amd64.tar.gz
```


