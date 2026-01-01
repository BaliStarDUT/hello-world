# golang如何查看项目依赖树

在 Go（Golang）中，查看项目的依赖树（即模块依赖关系图）有多种方式，主要通过 go mod 命令实现。以下是常用方法：

1. 使用 go mod graph 查看依赖图
该命令会输出项目的所有直接和间接依赖，以“模块 → 依赖模块” 的形式展示。

bash
go mod graph

输出示例：

myproject github.com/gin-gonic/gin@v1.9.1
github.com/gin-gonic/gin@v1.9.1 github.com/go-playground/validator/v10@v10.14.0
github.com/gin-gonic/gin@v1.9.1 github.com/mattn/go-isatty@v0.0.16
...
⚠️ 注意：输出是扁平的边列表（edge list），不是树形结构，但可以导入工具可视化。

2. 使用 go list -m all 查看完整的依赖列表
列出当前项目所有依赖的模块（包括版本），按层级展开。

bash
go list -m all

输出示例：

myproject
github.com/gin-gonic/gin v1.9.1
github.com/go-playground/validator/v10 v10.14.0
github.com/mattn/go-isatty v0.0.16
...

这会显示所有直接和间接依赖的模块及其版本，但不显示依赖关系图。

3. 使用第三方工具生成可视化依赖树
✅ 推荐工具：godepgraph
这是一个专门用于生成 Go 依赖关系图的工具，可输出为 Graphviz .dot 文件，并可视化为图片。

安装：
bash
go install github.com/kisielk/godepgraph@latest

生成依赖图：
bash
生成 .dot 文件
godepgraph . > deps.dot
转换为 PNG（需安装 Graphviz）
dot -Tpng deps.dot -o deps.png

然后打开 deps.png 即可看到清晰的依赖树。
安装 Graphviz（macOS）：
bash
brew install graphviz

4. IDE 支持（如 GoLand、VS Code）
GoLand：在 go.mod 文件中右键 → “Diagrams” → “Show Diagram” 可图形化查看依赖。
VS Code + Go 插件：虽然没有内置依赖图，但可通过终端运行上述命令辅助分析。

小结

方法 用途 是否树形
------ ------ --------
go mod graph 显示模块间依赖关系（边列表） ❌ 扁平
go list -m all 列出所有依赖模块及版本 ❌ 列表
godepgraph + Graphviz 生成可视化依赖树图 ✅ 是
IDE 图形化工具 交互式查看依赖 ✅ 是

如果你只是想快速检查是否有某个依赖或版本冲突，go list -m all grep xxx 就足够；如果要深入分析依赖结构，建议使用 godepgraph 生成可视化图。