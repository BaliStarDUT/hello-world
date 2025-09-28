class ListNode:
    def __init__(self, val=0):
        self.val = val
        self.next = None
    def add(self, val):
        self.next = ListNode(val)
        return self.next
    def __str__(self) -> str:
        result = []
        while self:
            result.append(self.val)
            self = self.next
        return str(result)
def merge_two_sorted_lists(l1: ListNode, l2: ListNode) -> ListNode:
    dummy = ListNode(0)
    current = dummy
    while l1 and l2:
        if l1.val < l2.val:
            current.next = l1
            l1 = l1.next
        else:
            current.next = l2
            l2 = l2.next
        current = current.next
    current.next = l1 or l2
    return dummy.next

if __name__ == '__main__':
    l1 = ListNode(1)
    l1.next = ListNode(2)
    l1.next.next = ListNode(4)
    print(l1)
    l2 = ListNode(1)
    l2.next = ListNode(3)
    l2.next.next = ListNode(4)
    print(l2)

    merged_list = merge_two_sorted_lists(l1, l2)
    while merged_list:
        print(merged_list.val)
        merged_list = merged_list.next