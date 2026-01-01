Ansible 是一款**开源的自动化运维工具**，由 Red Hat 主导开发，基于 Python 实现，核心特点是**无代理（agentless）**、**使用 YAML 配置文件**（Playbook）、**通过 SSH 协议远程执行任务**，广泛用于服务器配置管理、应用部署、任务编排、批量操作等场景。


### 一、核心优势与基础特性
1. **无代理架构**  
   无需在被控节点（目标服务器）安装客户端软件，仅通过 SSH 协议（默认）或 WinRM（Windows 节点）通信，降低部署和维护成本。

2. **声明式配置（Playbook）**  
   使用 YAML 格式的 Playbook 定义“目标状态”，而非“步骤式命令”，Ansible 会自动处理从当前状态到目标状态的过程（类似“基础设施即代码”）。

3. **模块化设计**  
   内置数千个模块（Module），覆盖系统操作（文件、用户、服务）、云服务（AWS、Azure）、容器（Docker、K8s）、数据库等场景，支持自定义模块扩展。

4. **幂等性保障**  
   重复执行同一 Playbook 不会导致意外结果（如“创建用户”模块多次执行只会创建一次），适合自动化流程的稳定性。


### 二、高级特性详解
#### 1. **Playbook 进阶：变量、模板与条件判断**
- **变量管理**  
  支持多级别变量定义（Inventory、Playbook、Role、命令行），灵活传递参数：  
  ```yaml
  # 在 Playbook 中定义变量
  - hosts: webservers
    vars:
      app_port: 8080
    tasks:
      - name: 启动应用
        service:
          name: myapp
          state: started
          port: "{{ app_port }}"  # 引用变量
  ```
  变量优先级：命令行 `-e "var=value"` > Playbook 变量 > Inventory 变量 > 内置变量。

- **Jinja2 模板（Template）**  
  动态生成配置文件（如 Nginx 配置、应用配置），支持变量替换、循环、条件等逻辑：  
  ```jinja2
  # templates/nginx.conf.j2
  server {
      listen {{ nginx_port }};  # 变量替换
      {% if enable_ssl %}       # 条件判断
      ssl on;
      {% endif %}
  }
  ```
  在 Playbook 中通过 `template` 模块使用：  
  ```yaml
  - name: 部署 Nginx 配置
    template:
      src: templates/nginx.conf.j2
      dest: /etc/nginx/nginx.conf
  ```

- **条件判断（when）**  
  根据变量或事实（Facts）动态决定是否执行任务：  
  ```yaml
  - name: 仅在 CentOS 系统安装 httpd
    yum:
      name: httpd
      state: present
    when: ansible_os_family == "RedHat"  # 基于 Facts 判断系统类型
  ```


#### 2. **角色（Roles）：代码复用与模块化**
Roles 是 Ansible 中**组织 Playbook 的最佳实践**，将任务、变量、模板、文件等按功能模块化，实现代码复用和团队协作。  
一个典型的 Role 目录结构：  
```
roles/
  webserver/          # 角色名
    tasks/            # 核心任务（main.yml 是入口）
    handlers/         # 触发器（如配置变更后重启服务）
    vars/             # 角色专属变量
    defaults/         # 默认变量（优先级最低）
    templates/        # 模板文件
    files/            # 静态文件（如脚本、证书）
    meta/             # 角色依赖信息
```
使用 Role 示例：  
```yaml
- hosts: webservers
  roles:
    - role: webserver        # 引用 webserver 角色
      vars:
        nginx_port: 80       # 传递变量给角色
```


#### 3. ** handlers：事件触发机制**
Handlers 用于**响应任务状态变化的“事件”**（如配置文件更新后重启服务），避免重复执行重启操作（仅在任务真正改变状态时触发）。  
示例：  
```yaml
- hosts: webservers
  tasks:
    - name: 修改 Nginx 配置
      template:
        src: nginx.conf.j2
        dest: /etc/nginx/nginx.conf
      notify: 重启 Nginx  # 任务执行成功且配置有变化时，通知 handler

  handlers:
    - name: 重启 Nginx    # handler 定义
      service:
        name: nginx
        state: restarted
```


#### 4. **Facts：系统信息自动收集**
Facts 是 Ansible 自动从被控节点收集的**系统信息**（如操作系统、IP 地址、硬件配置等），可在 Playbook 中直接引用，无需手动定义。  
示例：  
```yaml
- hosts: all
  tasks:
    - name: 打印节点 IP 地址
      debug:
        msg: "节点 {{ inventory_hostname }} 的 IP 是 {{ ansible_default_ipv4.address }}"
```
- 禁用 Facts 以提高效率（适合大规模节点）：  
  ```yaml
  - hosts: all
    gather_facts: no  # 不收集 Facts
  ```


#### 5. **Inventory：动态主机管理**
Inventory 用于定义被控节点（主机/主机组），支持静态文件和动态生成（如从云厂商 API、CMDB 拉取主机列表）。  
- **静态 Inventory 示例**（`inventory.ini`）：  
  ```ini
  [webservers]          # 主机组
  web1.example.com      # 主机1
  web2.example.com:2222 # 自定义 SSH 端口
  192.168.1.100

  [dbservers]
  db[1:3].example.com   # 批量定义主机（db1、db2、db3）

  [all:vars]            # 所有主机的默认变量
  ansible_user=root     # SSH 用户名
  ```

- **动态 Inventory**：通过脚本（Python/Bash 等）生成 JSON 格式的主机列表，适合云环境（如 AWS EC2 实例动态增减）：  
  ```bash
  # 动态 Inventory 脚本示例（简化）
  #!/usr/bin/env python
  import json
  print(json.dumps({
      "webservers": {
          "hosts": ["web1", "web2"]
      }
  }))
  ```


#### 6. **并行执行与滚动更新**
- **并行执行**：通过 `forks` 参数控制并发数（默认 5），适合大规模节点操作：  
  ```bash
  ansible-playbook -f 20 site.yml  # 20 个并发进程
  ```

- **滚动更新**：在部署应用时，避免同时重启所有节点（减少服务中断），通过 `serial` 控制每次更新的节点数量：  
  ```yaml
  - hosts: webservers
    serial: 2  # 每次更新 2 个节点
    tasks:
      - name: 部署新版本应用
        shell: /opt/deploy.sh
  ```


#### 7. **加密与安全：Vault**
Ansible Vault 用于**加密敏感信息**（如密码、API 密钥），避免明文存储在 Playbook 或变量文件中。  
- 创建加密文件：  
  ```bash
  ansible-vault create secrets.yml  # 输入密码，编辑加密内容
  ```
- 查看/编辑加密文件：  
  ```bash
  ansible-vault view secrets.yml
  ansible-vault edit secrets.yml
  ```
- 执行 Playbook 时解密：  
  ```bash
  ansible-playbook --ask-vault-pass site.yml  # 手动输入解密密码
  ```


#### 8. **回调插件与扩展**
通过回调插件（Callback Plugins）扩展 Ansible 的输出格式、集成日志系统（如 ELK）、发送通知（如 Slack/邮件）等。  
示例：启用 `timer` 插件统计任务执行时间，修改 `ansible.cfg`：  
```ini
[defaults]
callback_whitelist = timer
```


### 三、典型应用场景
1. **批量配置管理**：统一设置服务器时区、防火墙规则、用户权限等。  
2. **应用部署**：自动化部署 Web 应用（Nginx + Python/Java）、数据库（MySQL/PostgreSQL）等。  
3. **云资源编排**：通过模块创建 AWS EC2 实例、阿里云 ECS、K8s 资源等。  
4. **滚动升级与回滚**：安全更新应用版本，失败时自动执行回滚操作。  
5. **监控与合规检查**：定期检查服务器配置是否符合规范（如密码复杂度、端口开放策略）。


### 总结
Ansible 以“简单、无代理、可扩展”为核心优势，其高级特性（Roles、变量、模板、Vault 等）使其能满足从小型服务器集群到大型云环境的自动化需求。相比 Chef、Puppet 等工具，Ansible 入门门槛更低，更适合快速上手和团队协作。

如果需要具体场景的 Playbook 示例（如部署 Nginx、滚动更新应用），可以进一步说明！