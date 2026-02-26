# Python的语法
你想了解的 `print(f"{}")` 是 Python 3.6+ 引入的 **f-string（格式化字符串字面值）** 语法，是目前 Python 中最简洁、高效的字符串格式化方式。核心作用是将变量/表达式直接嵌入字符串中，替代传统的 `%` 格式化、`str.format()` 等方式。

### 一、核心语法解析
#### 1. 基础结构
```python
f"固定文本{变量/表达式}固定文本"
# 或
F"固定文本{变量/表达式}固定文本"  # f/F 大小写均可
```
- **前缀 `f/F`**：标识这是 f-string，告诉解释器要解析花括号 `{}` 内的内容；
- **花括号 `{}`**：占位符，内部可放**变量、表达式、函数调用**，解释器会执行/取值后替换到字符串中；
- **其余部分**：普通字符串，原样输出。

#### 2. 最基础用法（变量嵌入）
直接将变量放入 `{}`，替代变量为实际值：
```python
name = "张三"
age = 25
# 基础使用：嵌入变量
print(f"姓名：{name}，年龄：{age}")  # 输出：姓名：张三，年龄：25

# 对比传统 format 方式（更繁琐）
print("姓名：{}，年龄：{}".format(name, age))
# 对比 % 格式化（易出错）
print("姓名：%s，年龄：%d" % (name, age))
```

### 二、进阶用法（核心优势）
f-string 比传统格式化更强大，支持**表达式计算、格式控制、函数调用**等：

#### 1. 嵌入表达式（直接计算）
`{}` 内可写任意合法的 Python 表达式，解释器会先计算再输出：
```python
a = 10
b = 20
# 算术运算
print(f"a + b = {a + b}")  # 输出：a + b = 30
print(f"a * b = {a * b}")  # 输出：a * b = 200

# 字符串操作
text = "hello"
print(f"大写：{text.upper()}")  # 输出：大写：HELLO
print(f"长度：{len(text)}")     # 输出：长度：5

# 条件表达式
score = 85
print(f"成绩等级：{'及格' if score >= 60 else '不及格'}")  # 输出：成绩等级：及格
```

#### 2. 格式控制（数值/日期等）
通过 `{变量:格式符}` 控制输出格式（如保留小数、补零、百分比等），常用格式符：
| 格式符 | 作用                | 示例                          | 输出          |
|--------|---------------------|-------------------------------|---------------|
| `.2f`  | 保留 2 位小数       | `f"{3.1415:.2f}"`             | 3.14          |
| `04d`  | 补零到 4 位整数     | `f"{5:04d}"`                  | 0005          |
| `%`    | 百分比格式（乘100） | `f"{0.75:.1%}"`               | 75.0%         |
| `,`    | 千分位分隔符        | `f"{1234567:,}"`              | 1,234,567     |

```python
# 数值格式化
pi = 3.1415926
print(f"π 保留 3 位小数：{pi:.3f}")  # 输出：π 保留 3 位小数：3.142

# 日期格式化（需导入 datetime）
from datetime import datetime
now = datetime.now()
print(f"当前时间：{now:%Y-%m-%d %H:%M:%S}")  # 输出：当前时间：2026-01-21 10:30:00
```

#### 3. 嵌套花括号（输出 `{}` 本身）
若需要输出 `{` 或 `}`，只需写**两个花括号** `{{`/`}}`：
```python
print(f"数学公式：{{a + b}} = {10 + 20}")  # 输出：数学公式：{a + b} = 30
```

#### 4. 调用函数/方法
`{}` 内可直接调用函数，甚至是自定义函数：
```python
# 内置函数
def add(x, y):
    return x + y

print(f"计算 10+20：{add(10, 20)}")  # 输出：计算 10+20：30

# 结合对象方法
class Person:
    def __init__(self, name):
        self.name = name
    def get_name(self):
        return self.name

p = Person("李四")
print(f"用户名：{p.get_name()}")  # 输出：用户名：李四
```

### 三、使用注意事项
1. **版本要求**：仅支持 Python 3.6 及以上版本，若用低版本需升级或改用 `str.format()`；
2. **引号冲突**：字符串内的引号需与外层引号错开（外层双引号，内层单引号，反之亦然）：
   ```python
   # 正确
   print(f"他说：{'我是Python程序员'}")
   # 错误（引号冲突）
   # print(f"他说：{"我是Python程序员"}")
   ```
3. **变量存在性**：`{}` 内的变量/表达式必须已定义，否则会报 `NameError`：
   ```python
   # 错误：未定义变量 x
   # print(f"x = {x}")
   ```
4. **性能优势**：f-string 的执行效率远高于 `%` 格式化和 `str.format()`，是 Python 官方推荐的格式化方式。

### 总结
1. **核心定义**：`f"{}"` 是 f-string 语法，前缀 `f` 标识格式化字符串，`{}` 内嵌入变量/表达式；
2. **核心优势**：简洁、高效、支持表达式计算和格式控制，替代传统格式化方式；
3. **关键用法**：
   - 基础：`f"{变量}"` 嵌入变量；
   - 进阶：`f"{表达式:格式符}"` 计算并格式化输出；
   - 特殊：`{{}}` 输出花括号本身，`{函数()}` 调用函数。

f-string 是 Python 中最常用的字符串格式化方式，日常开发中优先使用，能大幅简化代码并提升可读性。


# Python 中查看已安装的包
在 Python 中查看已安装的包（packages），有多种方法，适用于不同场景（如全局环境、虚拟环境、特定解释器等）。以下是**最常用且可靠的方式**：

---

## ✅ 方法一：使用 `pip list`（推荐）

这是最标准、最常用的方法，列出当前 Python 环境中所有通过 `pip` 安装的包。

```bash
# 列出所有已安装的包及其版本
pip list

# 以“需求文件”格式输出（可用于 requirements.txt）
pip freeze

# 查看某个包的详细信息（如位置、依赖）
pip show numpy
```

> 💡 **注意**：
> - 如果你使用了 **虚拟环境（venv / conda）**，请先激活该环境再运行命令。
> - 若系统有多个 Python 版本（如 `python3.9`, `python3.10`），建议用对应版本的 pip：
>   ```bash
>   python3 -m pip list
>   # 或
>   /path/to/python -m pip list
>   ```

---

## ✅ 方法二：使用 `pip freeze`（用于生成依赖文件）

```bash
pip freeze
```
输出示例：
```
numpy==1.24.3
requests==2.31.0
flask==2.3.2
```
> ✅ 适合复制到 `requirements.txt` 文件中，用于项目依赖管理。

---

## ✅ 方法三：在 Python 代码中查看

### 方式 1：使用 `pkg_resources`（兼容旧版）
```python
import pkg_resources
installed_packages = [d.project_name for d in pkg_resources.working_set]
print(sorted(installed_packages))
```

### 方式 2：使用 `importlib.metadata`（Python 3.8+ 推荐）
```python
import importlib.metadata
packages = [dist.metadata["Name"] for dist in importlib.metadata.distributions()]
print(sorted(packages))
```

> ⚠️ 注意：这些方法可能包含非 pip 安装的包（如系统包），但通常与 `pip list` 结果一致。

---

## ✅ 方法四：查看包的安装路径

有时候你想知道某个包具体装在哪里：

```bash
# 查看包的安装位置
pip show requests | grep "Location"

# 或在 Python 中
python -c "import requests; print(requests.__file__)"
```

---

## 🔍 常见问题排查

### Q1: `pip list` 和 `conda list` 结果不一样？
- 因为 `pip` 和 `conda` 是**两个不同的包管理器**。
- 如果你用的是 Anaconda/Miniconda，建议统一用 `conda` 管理，或在 conda 环境中用 `pip`（但避免混用）。

### Q2: 为什么有些包在 `pip list` 里看不到？
- 可能是通过 `setup.py install`、`easy_install` 或系统包管理器（如 `apt`）安装的。
- 这些包通常不会被 `pip` 管理，建议统一使用 `pip` 安装。

### Q3: 如何只列出用户安装的包（排除系统包）？
```bash
pip list --user
```

---

## ✅ 总结

| 场景 | 命令 |
|------|------|
| **查看所有包 + 版本** | `pip list` |
| **生成 requirements.txt** | `pip freeze` |
| **查看包详情（路径、依赖）** | `pip show <package>` |
| **在 Python 脚本中获取** | `importlib.metadata.distributions()` |
| **确认当前环境是否激活** | `which python` / `which pip` |

> 💡 **最佳实践**：
> - 始终在**虚拟环境**中开发（`python -m venv venv`）
> - 使用 `pip list` 或 `pip freeze` 管理依赖
> - 不要直接在系统 Python 中安装包（避免污染）

这样你就能清晰掌握当前 Python 环境中安装了哪些包了！