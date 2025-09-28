def distance_squared(point):
    """计算点到原点的距离平方（避免平方根运算）"""
    return point[0] **2 + point[1]** 2

def quick_sort(points):
    """快速排序实现：按点到原点的距离排序"""
    # 基线条件：空列表或只有一个元素的列表已经是排序好的
    if len(points) <= 1:
        return points
    
    # 选择基准点
    pivot = points[0]
    pivot_dist = distance_squared(pivot)
    
    # 分区：小于、等于、大于基准点的元素
    less = []
    equal = []
    greater = []
    
    for point in points:
        dist = distance_squared(point)
        if dist < pivot_dist:
            less.append(point)
        elif dist == pivot_dist:
            equal.append(point)
        else:
            greater.append(point)
    
    # 递归排序并合并结果
    return quick_sort(less) + equal + quick_sort(greater)


# 测试示例
if __name__ == "__main__":
    # 测试数据
    test_points = [
        [3, 4],   # 距离5
        [1, 1],   # 距离√2
        [0, 5],   # 距离5
        [2, 3],   # 距离√13
        [0, 0]    # 距离0
    ]
    
    # 排序
    sorted_points = quick_sort(test_points)
    
    # 输出结果
    print("原始点列表:", test_points)
    print("按到原点距离排序后的点列表:", sorted_points)
    
    # 输出距离验证
    print("\n距离验证:")
    for point in sorted_points:
        dist_sq = distance_squared(point)
        print(f"点{point}，距离平方: {dist_sq}")
