# merge-k-sorted-lists
# Definition for singly-linked list.
from collections import deque
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution(object):
    def mergeKLists(self, lists):
        """
        :type lists: List[Optional[ListNode]]
        :rtype: Optional[ListNode]
        """
        head = []
        kqueue = deque()
        for k_head in lists:
            kqueue.append(k_head)
        sorted_list = sorted(kqueue,key=lambda x: x[0])
        kqueue = deque(sorted_list)
        nextx = kqueue.popleft()
        head.append(nextx[0])
        if len(nextx) >=1:
            kqueue.append(nextx[1:])
        while kqueue:
            sorted_list = sorted(kqueue,key=lambda x:x[0])
            kqueue = deque(sorted_list)
            cur = kqueue.popleft()
            if len(cur) >=1:
                head.append(cur[0])
                cur = cur[1:]
                if cur:
                    kqueue.append(cur)

        return head
    
    def mergeKLists_heap(self,lists):
        import heapq
        heap1 = []
        for i in range(len(lists)):
            if lists[i]:
                heapq.heappush(heap1,(lists[i].val,i))
                lists[i] = lists[i].next
        tmp_head = ListNode()
        cur = tmp_head

        while heap1:
            val, i = heapq.heappop(heap1)
            cur.next = ListNode(val)
            cur = cur.next
            if lists[i]:
                heapq.heappush(heap1,(lists[i].val,i))
                lists[i] = lists[i].next
        return tmp_head

if __name__=="__main__":
    s = Solution()
    head = s.mergeKLists_heap([[1,4,5],[1,3,4],[2,6]])
    print(head)
    head = s.mergeKLists_heap([[33,44,55],[11,13,14],[22,36]])
    print(head)