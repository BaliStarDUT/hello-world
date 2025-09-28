# https://leetcode.cn/problems/largest-number/submissions/665370366/

from typing import List
from functools import cmp_to_key

class LinkedNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution:
    def list_to_linked(self, list):
        head = LinkedNode(0)
        current = head
        for value in list:
            node = LinkedNode(value)
            current.next = node
            current = node
        return head.next
    
    def largestNumber(self, nums: List[int]) -> str:
        metrics = [[] for _ in range(len(nums))]
        for index,n in enumerate(nums):
            metrics[index] = []
            str_n = str(n)
            print(str_n[len(str_n)-1])
            i = 0
            head = LinkedNode(0)
            current = head
            while i < len(str_n):
                node = LinkedNode(str_n[i])
                current.next = node
                current = node
                i += 1
            print(head.next)
            metrics[index].append(head.next)

        print(metrics)
        # metrics[0].sort(reverse=True)
        # print(metrics)
        list = []
        for i,value in enumerate(metrics):
            list.append(value)
        print(list)
        list.sort(key=lambda x: x[0].val, reverse=True)
        print(list)
        res = ""
        for i in range(len(list)):
            node = list[i][0]
            while node:
                res += node.val
                node = node.next
        print(res)
        return res
    def largestNumber_2(self, nums: List[int]) -> str:
        str_nums = [str(num) for num in nums]
        str_nums.sort(key=cmp_to_key(self.compare), reverse=True)
        largest_num = ''.join(str_nums)
        print(largest_num)
        return '0' if largest_num[0] == '0' else largest_num

    def compare(self, a: str, b: str) -> int:
        if a+b < b+a:
            return 1
        else:
            return -1
    # 定义比较函数：x+y < y+x 时，x应排在y后面（返回1表示x需要被排序到y之后）
    def compare2(self, x: str, y: str) -> int:
        if x + y < y + x:
            return 1
        else:
            return -1


if __name__ == "__main__":
    s = Solution()
    s.largestNumber_2([3,30,34,5,9])
