# reverse linklist
# LCR 024. 反转链表
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
    def add(self,val):
        new_node = ListNode(val)
        current = self
        while current.next:
            current = current.next
        current.next = new_node
        return self

    def reverse(self):
        prev = None
        current = self
        while current:
            next_node = current.next
            current.next = prev
            prev = current
            current = next_node
        return prev
    def print_list(self):
        node = self
        while node:
            print(node.val)
            node = node.next
    

if __name__ == '__main__':
    node = ListNode(1)
    node.add(2).add(3).add(4).add(5)
    node.print_list()
    node.reverse().print_list()