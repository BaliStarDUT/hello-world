class ListNode:
    def __init__(self,value=0,next=None):
        self.val = value
        self.next = next
    
    def add(self,value):
        node = self
        while node.next:
            node = node.next
        node.next = ListNode(value)
        return self

class TreeNode:
    def __init__(self,value=0,left=None,right=None):
        self.val = value
        self.left = left
        self.right = right

    
class Solution:
    def sortedListToBST(self, head: ListNode) -> TreeNode:
        def getMedian(left: ListNode, right: ListNode) -> ListNode:
            fast = slow = left
            while fast != right and fast.next != right:
                fast = fast.next.next
                slow = slow.next
            return slow
        
        def buildTree(left: ListNode, right: ListNode) -> TreeNode:
            if left == right:
                return None
            mid = getMedian(left, right)
            root = TreeNode(mid.val)
            root.left = buildTree(left, mid)
            root.right = buildTree(mid.next, right)
            return root
        
        return buildTree(head, None)
    
if __name__ == '__main__':
    s = Solution()
    print(s.sortedListToBST([-10,-3,0,5,9]))
    print(s.sortedListToBST([1,3]))