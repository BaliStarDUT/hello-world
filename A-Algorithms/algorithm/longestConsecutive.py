#给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
def longestConsecutive(nums):
    nums = set(nums)
    max = 0
    for num in nums:
        if num - 1 not in nums:
            current_num = num
            current_streak = 1
            while current_num + 1 in nums:
                current_num += 1
                current_streak += 1
            max = max(max, current_streak)
    return max

def longestConsecutive1(nums):
    nums = set(nums)
    max = 0
    for num in nums:
        if num - 1 not in nums:
            current_num = num
            current_streak = 1
            while current_num + 1 in nums:
                current_num += 1
                current_streak += 1
            max = max(max, current_streak)
    return max

def longestConsecutive2(nums):
    if not nums:
        return 0
    nums = set(nums)
    print(nums)
    result = ""
    max = 0
    for num in nums:
        if num + 1  in nums:
            result += str(num)
            print(result)
        else:
            result = ""
        if len(result) > max:
            max = len(result)
    return max
print(longestConsecutive2([100,4,200,1,3,2]))
