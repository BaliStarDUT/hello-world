# 114-flatten-binary-tree-to-linked-list
class Solution(object):
    def flatten(self, root):
        """
        :type root: TreeNode
        :rtype: None Do not return anything, modify root in-place instead.
        """
        if not root:
            return
        stack = [root]
        prev = None
        while stack:
            node = stack.pop()
            if prev:
                prev.left = None
                prev.right = node
            prev = node
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)
        return root
    def flatten(self, root) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        if root is None:
            return
        stack = [root]
        while stack:
            node = stack.pop()
            if node.right:
                stack.append(node.right)
            if node.left :
                stack.append(node.left)
            if len(stack) >= 1:
                node.right = stack[len(stack)-1]
            node.left = None
        return root


    def middle(self, root):
        if not root:
            return
        self.middle(root.left)
        print(root.val)
        self.middle(root.right)


class Node:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def print_tree(self):
        print(self.val)
        if self.left:
            self.left.print_tree()
        if self.right:
            self.right.print_tree()
    
if __name__ == "__main__":
    root = Node(1)
    root.left = Node(2)
    root.right = Node(5)
    root.left.left = Node(3)
    root.left.right = Node(4)
    root.right.right = Node(6)

    root.print_tree()

    s = Solution()
    linked_root = s.middle(root)
    linked_root.print_tree()

