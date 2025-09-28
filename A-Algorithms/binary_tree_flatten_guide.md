# 二叉树展开为中序链表实现指南

## 问题描述
将二叉树展开为链表，链表顺序为二叉树的中序遍历顺序。要求原地在二叉树上进行修改。

## 实现方法对比

### 方法1：递归收集法（简单易懂）
**Go实现**：`flatten_inorder_recursive()`  
**Python实现**：`flatten_inorder_recursive()`

**原理**：
1. 先通过中序遍历收集所有节点
2. 然后重新连接节点形成链表

**复杂度**：
- 时间复杂度：O(n)
- 空间复杂度：O(n) - 需要存储所有节点

**优点**：思路简单，易于理解和调试
**缺点**：需要额外空间存储节点

### 方法2：Morris遍历法（空间最优）
**Go实现**：`flattenInorder2()`  
**Python实现**：`flatten_inorder_morris()`

**原理**：
使用Morris遍历进行中序遍历，利用树的空闲指针建立临时连接

**复杂度**：
- 时间复杂度：O(n)
- 空间复杂度：O(1) - 常数级空间

**优点**：空间复杂度最优
**缺点**：实现较复杂，需要处理指针连接

### 方法3：迭代栈法（平衡选择）
**Python实现**：`flatten_inorder_iterative()`

**原理**：
使用栈进行中序遍历，边遍历边修改指针

**复杂度**：
- 时间复杂度：O(n)
- 空间复杂度：O(h) - h为树高，平均情况优于方法1

**优点**：空间效率较好，实现相对简单

## 测试用例

### 用例1：基本测试
```
    1
   / \
  2   5
 / \   \
3   4   6

中序遍历顺序：3 → 2 → 4 → 1 → 5 → 6
```

### 用例2：平衡二叉树
```
      4
     / \
    2   6
   / \ / \
  1  3 5  7

中序遍历顺序：1 → 2 → 3 → 4 → 5 → 6 → 7
```

### 边界条件
- 空树：返回 `nil/None`
- 单节点：返回该节点本身
- 左斜树：所有节点只有左子节点
- 右斜树：所有节点只有右子节点

## 使用方法

### Go版本
```bash
go run inorder_flatten_binary_tree.go
```

### Python版本
```bash
python inorder_flatten.py
```

## 代码结构

### 文件说明
- `inorder_flatten_binary_tree.go` - Go语言完整实现
- `inorder_flatten.py` - Python语言完整实现
- `binary_tree_flatten_guide.md` - 本使用指南

### 核心函数

#### Go版本
```go
// 递归法
func flattenInorder1(root *TreeNode) *TreeNode

// Morris遍历法
func flattenInorder2(root *TreeNode) *TreeNode

// 递归原地修改法
func flattenInorder3(root *TreeNode) *TreeNode
```

#### Python版本
```python
# 递归法
def flatten_inorder_recursive(self, root)

# Morris遍历法
def flatten_inorder_morris(self, root)

# 迭代栈法
def flatten_inorder_iterative(self, root)
```

## 性能对比

| 方法 | 时间复杂度 | 空间复杂度 | 实现难度 | 适用场景 |
|---|---|---|---|---|
| 递归收集法 | O(n) | O(n) | 简单 | 学习和调试 |
| Morris遍历法 | O(n) | O(1) | 较复杂 | 空间敏感场景 |
| 迭代栈法 | O(n) | O(h) | 中等 | 平衡选择 |

## 实际应用建议

1. **学习和调试**：使用递归收集法，代码清晰易懂
2. **生产环境**：根据内存限制选择
   - 内存充足：使用递归收集法或迭代栈法
   - 内存紧张：使用Morris遍历法
3. **面试准备**：建议掌握所有三种方法

## 扩展思考

### 其他遍历顺序的展开
- 前序遍历展开：根 → 左 → 右
- 后序遍历展开：左 → 右 → 根
- 层序遍历展开：按层次顺序

### 变种问题
- 展开为双向链表
- 保持树结构不变，只修改指针
- 多叉树的链表展开

## 验证方法

### 手动验证
1. 绘制原始二叉树
2. 确定中序遍历序列
3. 检查链表顺序是否匹配

### 自动化测试
使用提供的 `verify_inorder` 或 `validateInorder` 函数进行验证。

---
*实现完成时间：2025-09-14*
*包含Go和Python两种语言的完整实现*