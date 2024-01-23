# 2024-1-23
## https://leetcode.cn/problems/count-of-integers/
### 1. 暴力求解 时间很长
class Solution(object):
    def count(self, num1, num2, min_sum, max_sum):
        """
        :type num1: str
        :type num2: str
        :type min_sum: int
        :type max_sum: int
        :rtype: int
        """
        count = 0
        for i in range(int(num1),int(num2)+1):
            sum = 0
            for j in str(i):
                sum = sum + int(j)
            if sum >= min_sum and sum <= max_sum :
                count = count +1  
                print(i)

        
        return count


num1 = "4179205230"
num2 = "7748704426"
min_sum=8
max_sum=46

so = Solution()
so.count(num1,num2,min_sum,max_sum)


## 动态规划dynamic programing

### 动态规划-斐波那契数列 https://blog.csdn.net/qq_42017331/article/details/102069527
def fibo(n):
    if n <1:
        return -1
    F = [0]*(n+1)
    F[1] = 1
    F[2] = 1
    for i in range(3,n+1):
        print(i)
        F[i]= F[i-1]+F[i-2]
    return F[n]


r = fibo(1000)
print("----------%s" % r)

