
# https://leetcode.cn/problems/binary-tree-right-side-view/?envType=daily-question&envId=2025-10-19
# 给你二叉树的根节点 root ，想象自己站在它的右侧，返回从顶部到达底部所能看到的节点值。
# binary-tree-right

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def rightview(node, level, max_level, result):
    if node is None:
        return
    if level > max_level[0]:
        result.append(node.val)
        max_level[0] = level
    rightview(node.right, level + 1, max_level, result)
    rightview(node.left, level + 1, max_level, result)


if __name__ == "__main__":
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.right.right = TreeNode(4)
    root.left.right = TreeNode(5)

    result = []
    max_level = [-1]
    rightview(root, 0, max_level, result)
    print(result)  # Output: [1, 3, 4]