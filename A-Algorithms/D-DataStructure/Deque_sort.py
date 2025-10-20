from collections import deque

def sort_deque_by_first_element(dq):
    """
    对deque中存储的int型列表按第一个元素从小到大排序
    :param dq: 存储int列表的deque，如deque([[3, 1], [1, 5], [2, 7]])
    :return: 排序后的deque
    """
    # 转换为列表并按每个子列表的第一个元素排序
    sorted_list = sorted(dq, key=lambda x: x[2])
    # 重新构造deque并返回
    return deque(sorted_list)

# 测试示例
if __name__ == "__main__":
    # 创建一个包含int列表的deque
    dq = deque([
        [5, 2, 8],
        [3, 1, 4],
        [1, 9, 6],
        [4, 3, 5],
        [2, 7, 0]
    ])
    
    print("排序前的deque:", list(dq))
    
    # 按子列表第一个元素排序
    sorted_dq = sort_deque_by_first_element(dq)
    
    print("排序后的deque:", list(sorted_dq))
