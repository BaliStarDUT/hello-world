# find-the-kth-largest-integer-in-the-array
# https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/

class Solution:
    def kthLargestNumber(self, nums: List[str], k: int) -> str:
        nums.sort(key=lambda x: (len(x), x))
        return nums[-k]
    
if __name__ == "__main__":
    solution = Solution()
    print(solution.kthLargestNumber(["3","6","7","10"], 4))  # Output: "3"
    print(solution.kthLargestNumber(["2","21","12","1"], 3))  # Output: "2"
    print(solution.kthLargestNumber(["0","0"], 2))            # Output: "0"