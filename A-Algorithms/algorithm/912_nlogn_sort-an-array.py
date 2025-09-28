# sort-an-array
# https://leetcode.cn/problems/sort-an-array/description/

class Solution:
    def solution(self, nums):
        if len(nums) <= 1:
            return nums
        
        mid = len(nums) // 2
        pivot = nums[mid]
        left_part = []
        right_part = []
        for num in nums:
            if num < pivot:
                left_part.append(num)
            elif num > pivot:
                right_part.append(num)
        left_part = self.solution(left_part)
        right_part = self.solution(right_part)
        return left_part + [pivot] + right_part
    def quick_sort2(self, nums):
        if len(nums) <= 1:
            return nums
        import random
        mid = random.randint(0, len(nums)-1)
        # mid = len(nums) // 2
        pivot = nums[mid]
        left_part = []
        right_part = []
        i =0
        for num in nums:
            if num < pivot:
                left_part.append(num)
            elif num > pivot:
                right_part.append(num)
            else:
                i=i+1
                continue
        left_part = self.quick_sort2(left_part)
        right_part = self.quick_sort2(right_part)
        return left_part + [pivot] * i + right_part

if __name__ == "__main__":
    s = Solution()
    nums = [5,2,3,1]
    print(s.quick_sort2(nums))
    nums = [5,1,1,2,0,0]
    print(s.quick_sort2(nums))