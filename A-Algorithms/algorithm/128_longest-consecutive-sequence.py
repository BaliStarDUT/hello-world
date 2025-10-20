#   128. Longest Consecutive Sequence
#   https://leetcode.com/problems/longest-consecutive-sequence/
#   Time Complexity: O(n)
#   Space Complexity: O(n)
#   Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
#   You must write an algorithm that runs in O(n) time.

class Solution(object):
    def longestConsecutive(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        num_set = set(nums)
        longest_streak = 0

        for num in num_set:
            if num - 1 not in num_set:
                current_num = num
                current_streak = 1

                while current_num + 1 in num_set:
                    current_num += 1
                    current_streak += 1

                longest_streak = max(longest_streak, current_streak)

        return longest_streak
    def longestConsecutive_v2(self, nums):
        if not nums:
            return 0
        
        nums = sorted(nums)
        longest_streak = 1
        current_streak = 1
        for i in range(1,len(nums)):
            if nums[i] != nums[i-1]:
                if nums[i] == nums[i-1] + 1:
                    current_streak += 1
                else:
                    longest_streak = max(longest_streak,current_streak)
                    current_streak = 1
        longest_streak = max(longest_streak,current_streak)
        return longest_streak


if __name__ == "__main__":
    s = Solution()
    print(s.longestConsecutive_v2([100, 4, 200, 1, 3, 2]))
    print(s.longestConsecutive_v2([0,3,7,2,5,8,4,6,0,1]))    
    print(s.longestConsecutive_v2([]))
    print(s.longestConsecutive_v2([1,2,0,1]))