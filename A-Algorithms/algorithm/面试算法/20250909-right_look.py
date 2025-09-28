"""
题目：两数之和
---        
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

你可以按任意顺序返回答案。

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]
"""
# 暴力解法，o(n^2)
def find_index(nums,target):
    length = len(nums)
    for index,value in enumerate(nums):
        if value > target:
            continue
        for index_2,value_2 in enumerate(nums[index+1:length]):
            if value+value_2 == target:
                return [index,index+index_2+1]
        return []
print(find_index([1,2,3,4,5,6,7,8,9],10))
# 使用hash表，o(n)
def find_index2(nums,target):
    map1= dict()
    for index,value in enumerate(nums):
        if map1.get(target - value) is not None:
            return [index,map1.get(target-value)]
        else:
            map1[value] = index
    return []




class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
def levelOrder(root: TreeNode) -> list[list[int]]:
    if not root:
        return []
    queue = [root]
    res = []
    while queue:
        level = []
        for i in range(len(queue)):
            node = queue.pop(0)
            level.append(node.val)
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        res.append(level)
    return res

if __name__ == '__main__':
    # print(find_index2([2,11,13,7,15],9))
    # print(find_index2([2,11,7,15],9))
    root = TreeNode(3)
    root.right = TreeNode(20)
    root.left = TreeNode(9)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(2)
    root.right.left = TreeNode(15)
    root.right.right   = TreeNode(7)
    root.right.right.right = TreeNode(18)
    print(levelOrder(root))
    leven_order = levelOrder(root)
    for i in leven_order:
        print(i[len(i)-1])

    