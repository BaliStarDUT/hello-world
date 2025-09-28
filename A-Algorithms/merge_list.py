class listNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class LinkedList:
    def __init__(self):
        self.head = None
        self.tail = None
    def add(self, x):
        node = listNode(x)
        if self.head is None:
            self.head = node
            self.tail = node
        else:
            self.tail.next = node
            self.tail = node
    def printList(self):
        node = self.head
        while node is not None:
            formatted_string= "->{}".format(node.val)
            print(formatted_string, end="")
            node = node.next


def mergeList(pHead1, pHead2):
    if pHead1 is None:
        return pHead2
    if pHead2 is None:
        return pHead1
    if pHead1.val <= pHead2.val:
        pHead1.next = mergeList(pHead1.next, pHead2)
        return pHead1
    else:
        pHead2.next = mergeList(pHead1, pHead2.next)
        return pHead2

if __name__ == '__main__':
    l1 = LinkedList()
    l1.add(1)
    l1.add(3)
    l1.add(5)
    l2 = LinkedList()
    l2.add(2)
    l2.add(4)
    l2.add(6)
    l1.head = mergeList(l1.head, l2.head)
    l1.printList()

