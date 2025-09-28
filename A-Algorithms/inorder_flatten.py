"""
二叉树展开为中序遍历顺序的链表
"""

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def flatten_inorder_recursive(self, root):
        """
        方法1：递归法，先收集节点再重新连接
        时间复杂度：O(n)
        空间复杂度：O(n)
        """
        if not root:
            return None
        
        # 收集中序遍历的节点
        nodes = []
        self.inorder_collect(root, nodes)
        
        if not nodes:
            return None
        
        # 重新构建链表
        for i in range(len(nodes) - 1):
            nodes[i].left = None
            nodes[i].right = nodes[i + 1]
        
        # 处理最后一个节点
        nodes[-1].left = None
        nodes[-1].right = None
        
        return nodes[0]
    
    def inorder_collect(self, node, nodes):
        """中序遍历收集节点"""
        if not node:
            return
        self.inorder_collect(node.left, nodes)
        nodes.append(node)
        self.inorder_collect(node.right, nodes)
    
    def flatten_inorder_morris(self, root):
        """
        方法2：Morris遍历，O(1)空间复杂度
        时间复杂度：O(n)
        空间复杂度：O(1)
        """
        if not root:
            return None
        
        dummy = TreeNode(0)  # 虚拟头节点
        prev = dummy
        curr = root
        
        while curr:
            if curr.left is None:
                # 左子树为空，处理当前节点
                curr.left = None
                prev.right = curr
                prev = curr
                curr = curr.right
            else:
                # 找到前驱节点
                predecessor = curr.left
                while predecessor.right and predecessor.right != curr:
                    predecessor = predecessor.right
                
                if predecessor.right is None:
                    # 建立临时连接
                    predecessor.right = curr
                    curr = curr.left
                else:
                    # 断开临时连接，处理当前节点
                    predecessor.right = None
                    curr.left = None
                    prev.right = curr
                    prev = curr
                    curr = curr.right
        
        return dummy.right
    
    def flatten_inorder_iterative(self, root):
        """
        方法3：迭代法，使用栈
        时间复杂度：O(n)
        空间复杂度：O(h)，h为树高
        """
        if not root:
            return None
        
        dummy = TreeNode(0)
        prev = dummy
        stack = []
        curr = root
        
        while stack or curr:
            while curr:
                stack.append(curr)
                curr = curr.left
            
            curr = stack.pop()
            
            # 构建链表
            curr.left = None
            prev.right = curr
            prev = curr
            
            curr = curr.right
        
        return dummy.right

def create_test_tree():
    """创建测试用例"""
    # 构建如下二叉树：
    #      1
    #     / \
    #    2   5
    #   / \   \
    #  3   4   6
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(5)
    root.left.left = TreeNode(3)
    root.left.right = TreeNode(4)
    root.right.right = TreeNode(6)
    return root

def create_complex_tree():
    """创建复杂测试用例"""
    #      4
    #     / \
    #    2   6
    #   / \ / \
    #  1  3 5  7
    root = TreeNode(4)
    root.left = TreeNode(2)
    root.right = TreeNode(6)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(3)
    root.right.left = TreeNode(5)
    root.right.right = TreeNode(7)
    return root

def print_linked_list(head):
    """打印链表"""
    result = []
    curr = head
    while curr:
        result.append(str(curr.val))
        curr = curr.right
    print(" -> ".join(result))

def verify_inorder(head, expected):
    """验证链表顺序是否正确"""
    curr = head
    index = 0
    while curr and index < len(expected):
        if curr.val != expected[index]:
            return False
        curr = curr.right
        index += 1
    return index == len(expected) and curr is None

def test_all_methods():
    """测试所有方法"""
    solution = Solution()
    
    print("=== 二叉树展开为中序链表测试 ===")
    
    # 测试用例1
    print("\n测试用例1:")
    tree1 = create_test_tree()
    head1 = solution.flatten_inorder_recursive(tree1)
    print("递归法结果: ", end="")
    print_linked_list(head1)
    
    # 测试用例2
    print("\n测试用例2:")
    tree2 = create_complex_tree()
    head2 = solution.flatten_inorder_morris(tree2)
    print("Morris法结果: ", end="")
    print_linked_list(head2)
    expected = [1, 2, 3, 4, 5, 6, 7]
    print(f"验证结果: {verify_inorder(head2, expected)}")
    
    # 测试空树
    print("\n测试空树:")
    empty_head = solution.flatten_inorder_recursive(None)
    print(f"空树结果: {empty_head}")
    
    # 测试单节点
    print("\n测试单节点:")
    single = TreeNode(42)
    single_head = solution.flatten_inorder_iterative(single)
    print("单节点结果: ", end="")
    print_linked_list(single_head)

if __name__ == "__main__":
    test_all_methods()