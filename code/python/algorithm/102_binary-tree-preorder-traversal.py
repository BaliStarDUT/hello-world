# binary-tree-level-order-traversal
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

# 二叉树的层序遍历
class Solution:
    def levelOrder(self, root: TreeNode) -> list[list[int]]:
        if not root:
            return []
        queue = [root]
        res = []
        while queue:
            level = []
            for i in range(len(queue)):
                node = queue.pop(0)
                level.append(node.val)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            res.append(level)
        return res


def binary_tree_preorder_traversal(root):
    if not root:
        return []
    stack = [root]
    res = []
    while stack:
        node = stack.pop()
        res.append(node.val)
        if node.right:
            stack.append(node.right)
        if node.left:
            stack.append(node.left)
    return res

def preOrderRecur(root):
    if not root:
        return []
    res = []
    res.append(root.val)
    res += preOrderRecur(root.left)
    res += preOrderRecur(root.right)
    return res

def inOrderRecur(root):
    if not root:
        return []
    res = []
    res += inOrderRecur(root.left)
    res.append(root.val)
    res += inOrderRecur(root.right)
    return res

def postOrderRecur(root):
    if not root:
        return []
    res = []    
    res += postOrderRecur(root.left)    
    res += postOrderRecur(root.right)
    res.append(root.val)
    return res

if __name__ == '__main__':
    root = TreeNode(3)
    root.left = TreeNode(9)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(2)
    root.right = TreeNode(20)
    root.right.left = TreeNode(15)
    root.right.right = TreeNode(7)
    print(binary_tree_preorder_traversal(root))
    print(preOrderRecur(root))
    print(inOrderRecur(root))
    print(postOrderRecur(root))

    Solution = Solution()
    print(Solution.levelOrder(root))