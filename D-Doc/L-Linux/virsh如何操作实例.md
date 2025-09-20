`virsh` 是管理 KVM 虚拟机（实例）的命令行工具，通过它可以对虚拟机进行启动、停止、重启、查看状态等操作。以下是 `virsh` 操作实例的常用命令，以及重启实例的具体方法：


### **一、virsh 常用操作实例的命令**
首先，需要知道虚拟机的 **名称** 或 **UUID**（可通过 `virsh list` 命令查看）。

| 命令 | 功能 |
|------|------|
| `virsh list` | 查看当前运行的虚拟机 |
| `virsh list --all` | 查看所有虚拟机（包括关闭的） |
| `virsh start <虚拟机名/UUID>` | 启动指定虚拟机 |
| `virsh shutdown <虚拟机名/UUID>` | 优雅关闭虚拟机（类似系统正常关机） |
| `virsh destroy <虚拟机名/UUID>` | 强制关闭虚拟机（类似断电，可能丢失数据） |
| `virsh reboot <虚拟机名/UUID>` | 重启虚拟机（下文详细说明） |
| `virsh suspend <虚拟机名/UUID>` | 暂停虚拟机（冻结状态） |
| `virsh resume <虚拟机名/UUID>` | 恢复被暂停的虚拟机 |
| `virsh console <虚拟机名/UUID>` | 连接到虚拟机的控制台（需虚拟机配置支持） |
| `virsh dominfo <虚拟机名/UUID>` | 查看虚拟机的详细信息 |


### **二、重启实例（虚拟机）的方法**
重启虚拟机主要有两种方式，根据场景选择：

#### 1. 优雅重启（推荐）
使用 `reboot` 命令，会向虚拟机发送重启信号，类似物理机的“重启”按钮，系统会正常关闭再启动，安全性高：
```bash
virsh reboot <虚拟机名/UUID>
```
例如，重启名为 `centos7` 的虚拟机：
```bash
virsh reboot centos7
```

#### 2. 强制重启（应急）
如果虚拟机无响应，无法通过 `reboot` 正常重启，可先强制关闭再启动（类似断电后重启）：
```bash
# 强制关闭
virsh destroy <虚拟机名/UUID>

# 重新启动
virsh start <虚拟机名/UUID>
```
例如：
```bash
virsh destroy centos7
virsh start centos7
```


### **三、注意事项**
- 操作前建议通过 `virsh list --all` 确认虚拟机名称或 UUID，避免误操作。
- `reboot` 依赖虚拟机内部系统响应信号，若系统卡死可能失败，此时需用 `destroy + start` 强制重启。
- 生产环境中尽量使用优雅重启，减少数据丢失风险。

通过以上命令，即可完成对 KVM 虚拟机的基本管理和重启操作。