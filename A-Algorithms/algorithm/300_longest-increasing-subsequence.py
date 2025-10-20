#longest-increasing-subsequenc

class Solution(object):
    def lengthOfLIS(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        size = len(nums)
        dp=[1]*size
        for i in range(1,size):
            dp[i] = 1
            for j in range(0,i):
                if nums[j] < nums[i]:
                    dp[i] = max(dp[i],dp[j]+1)
        print(dp)
        maxi = max(dp)
        return maxi

        