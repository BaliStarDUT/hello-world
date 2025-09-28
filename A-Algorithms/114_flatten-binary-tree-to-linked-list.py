class tree_node:
    def __init__(self,val=0,left=None,right=None):
        self.val = val
        self.left = None
        self.right = None

    # init a binary tree with count nodes
    def init_a_tree(self,count):
        if count <= 0:
            return None
        from random import randint
        root = tree_node(randint(0,100))
        count -= 1
        root.left = self.init_a_tree(count//2)
        root.right = self.init_a_tree(count - count//2)
        return root
    def init_a_order_tree(self,count):
        if count <= 0:
            return None
        from random import randint
        root = tree_node(count)
        count -= 1
        root.left = self.init_a_order_tree(count//2)
        root.right = self.init_a_order_tree(count - count//2)
        return root
    def print_tree(self):
        if not self:
            return
        from collections import deque
        queue = deque([self])
        result = []
        while queue: 
            node = queue.popleft()
            if node:
                result.append(node.val)
                print(node.val,end=",")
                queue.append(node.left)
                queue.append(node.right)
                x = len(result)
                # print("x=",x,end=",")
                if x==1 or x & x-1 == 0:
                    print("==") 
        return result
    
    def level_order(self):
        if not self:
            return
        from collections import deque
        queue = deque([self])
        result = []
        while queue:
            node = queue.popleft()
            if node:
                result.append(node.val)
                queue.append(node.left)
                queue.append(node.right)
        return result
    
    def pre_order_1(self):
        result =[]
        def _pre_order(node):
            if not node:
                return
            result.append(node.val)
            _pre_order(node.left)
            _pre_order(node.right)
        _pre_order(self)
        return result
    def pre_order_2(self):
        result = []
        stack = [self]
        while stack:
            node = stack.pop()
            if node:
                result.append(node.val)
                stack.append(node.right)
                stack.append(node.left)

        return result
    def pre_order_3(self):
        if self is None:
            return []
        result,stack = [],[self]
        while stack:
            node = stack.pop()
            result.append(node.val)
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)
        return result
    def mid_order_1(self):
        result = []
        def _mid_order(node):
            if not node:
                return
            _mid_order(node.left)
            result.append(node.val)
            _mid_order(node.right)
        _mid_order(self)
        return result

    def mid_order_2(self):
        if self is None:
            return []
        result = []
        stack = []
        current = self
        while current or stack:
            if current:
                stack.append(current)
                current = current.left
            elif stack:
                node = stack.pop()
                result.append(node.val)
                current = node.right            
        return result
    def post_order_1(self):
        if self is None:
            return
        result = []
        def _post_order(node):
            if not node:
                return
            _post_order(node.left)
            _post_order(node.right)
            result.append(node.val)
        _post_order(self)
        return result
    
   
    def post_order_2(self):
        result = []
        stack = [self]
        while stack:
            node = stack.pop()
            if node:
                result.append(node.val)
                stack.append(node.left)
                stack.append(node.right)
 
        return result[::-1]

    def post_order_4(self):
        if self is None:
            return
        stack = []
        result = []
        current = self
        while current:
            if current.right:
                stack.append(current.right)
            
            if current.left:
                current = current.left
            stack.append(current)
            
            stack.append(current.right)
            current = current.left
        

        while current or stack:
            if current:
                stack.append(current)
                stack.append(current.right)
                current = current.left
            elif stack:
                node = stack.pop()
                if node.right:
                    stack.append(node)
                result.append(node.val)
                current = node.right


        return result        



class Solution:
    def flatten_to_linked_list(self,root):
        if not root:
            return None


    def mergeTrees_2(self, root1, root2):
        if not root1:
            return root2
        if not root2:
            return root1
        from collections import deque
        root = tree_node(root1.val + root2.val)
        queue = deque([root])
        queue1 = deque([root1])
        queue2 = deque([root2])
        while queue1 or queue2:
            node = queue.popleft()
            node1 = queue1.popleft() if queue1 else None
            node2 = queue2.popleft() if queue2 else None
            left1 = node1.left if node1 else None
            right1 = node1.right if node1 else None
            left2 = node2.left if node2 else None
            right2 = node2.right if node2 else None
            if left1 or left2:
                left = tree_node((left1.val if left1 else 0) + (left2.val if left2 else 0))
                node.left = left
                queue.append(left)
                queue1.append(left1)
                queue2.append(left2)
            if right1 or right2:
                right = tree_node((right1.val if right1 else 0) + (right2.val if right2 else 0))
                node.right = right
                queue.append(right)
                queue1.append(right1)
                queue2.append(right2)
        return root



    def mergeTrees_1(self, root1, root2):
        """
        :type root1: Optional[TreeNode]
        :type root2: Optional[TreeNode]
        :rtype: Optional[TreeNode]
        """
        # while root1 or root2:
        #     root1.val = root1.val + root2.val
        #     root1 = root1.left 
        #     root2 = root2.left
        # return root1
    
        stack_1 = [root1]
        result_1 = []
        while stack_1:
            node = stack_1.pop()
            result_1.append(node)
            if node and (node.left is not None or node.right is not None):
                # stack_1.left?stack_1.append(node.left):stack_1.append(None)
                stack_1.append(node.left) if node.left else stack_1.append(None)
                stack_1.append(node.right) if node.right else stack_1.append(None)                
            else:
                break
        stack_2 = [root2]
        result_2 = []
        while stack_2:
            node = stack_2[len(stack_2)-1]
            if node and (node.left is not None or node.right is not None):
                stack_2.append(node.left) if node.left else stack_2.append(None)
                stack_2.append(node.right) if node.right else stack_2.append(None)
            else:
                break
        len_1 = len(stack_1)
        len_2 = len(stack_2)
        if len_1 >= len_2:
            for i in range(len_1):
                print(stack_1[i])
                if stack_1[i]:
                    stack_1[i].val = (stack_1[i].val if stack_1[i] else 0) + (stack_2[i].val if stack_2[i] else 0) 
                else:
                    stack_1[i] = tree_node(0)
                    stack_1[i].val = (stack_1[i].val if stack_1[i] else 0) + (stack_2[i].val if stack_2[i] else 0) 

            return stack_1[0]
        else:
            for i in range(len_2):
                print(stack_2[i])
                if stack_2[i]:
                    stack_2[i].val = (stack_2[i].val if stack_2[i] else 0 ) +( stack_2[i].val if stack_2[i] else 0)
                else:
                    stack_2[i] = tree_node(0)
                    stack_2[i].val = (stack_2[i].val if stack_2[i] else 0 ) +( stack_2[i].val if stack_2[i] else 0)

            return stack_2[0]
    def mergeTrees(self, root1, root2):
        """
        :type root1: Optional[TreeNode]
        :type root2: Optional[TreeNode]
        :rtype: Optional[TreeNode]
        """ 
        if not root1:
            return root2
        if not root2:
            return root1
        root1.val += root2.val
        root1.left = self.mergeTrees(root1.left, root2.left)
        root1.right = self.mergeTrees(root1.right, root2.right)
        return root1
        
if __name__ == "__main__":
    root = tree_node(1)
    root.left = tree_node(2)
    root.right = tree_node(5)
    root.left.left = tree_node(3)
    root.left.right = tree_node(4)
    root.right.right = tree_node(6)

    tree1 = tree_node(2)
    tree1.left = tree_node(1)
    tree1.left.right = tree_node(4)
    tree1.right = tree_node(3)
    tree1.right.right = tree_node(7)

    solution = Solution()
    merge_tree = solution.mergeTrees_1(root,tree1)
    print(merge_tree.print_tree())

    # atree = root.init_a_order_tree(100)
    # leven_order_result = atree.level_order()
    # print("100个节点的树的层序遍历:", leven_order_result)
    # print("100个节点的树:", atree.print_tree())
    # print("原始树的前序遍历:", root.print_tree())
    # print("中序遍历：",root.post_order_2())

    # solution = Solution()
    # solution.flatten_to_linked_list(root)

    # # 打印展平后的链表
    # current = root
    # flattened_values = []
    # while current:
    #     flattened_values.append(current.val)
    #     current = current.right

    # print("展平后的链表:", flattened_values)
