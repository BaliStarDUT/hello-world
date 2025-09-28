# search-a-2d-matrix
# https://leetcode.com/problems/search-a-2d-matrix/

from typing import List

# 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
# 输出：true

def find_target(matrix,target):
    for row in matrix:
        if target in row:
            return True
    return False

def find_target_binary_search(matrix,target):
    left = 0
    right = len(matrix) - 1
    while left < right:
        mid = (left + right+1) // 2
        if matrix[mid][0] <= target:
            left = mid
        else:
            right = mid - 1
    if left < 0 or left >= len(matrix):
        return False
    row = matrix[left]
    left = 0
    right = len(row) - 1
    for i in range(len(row)):
        if row[i] == target:
            return True
        elif row[i] > target:
            right = i - 1
        else:
            left = i + 1
    return False

def find_target_binary(matrix,target):
    if not matrix:
        return False
    m,n = len(matrix),len(matrix[0])
    left,right = 0,m*n-1
    while left <= right:
        mid = (left+right) // 2
        mid_val = matrix[mid//n][mid%n]
        if mid_val == target:
            return True
        elif mid_val < target:
            left = mid + 1
        else:
            right = mid - 1
    return False

if __name__ == "__main__":
    matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]]
    # matrix = [[1],[3]]
    target = 12
    # print(find_target(matrix,target))
    print(find_target_binary_search(matrix,target))