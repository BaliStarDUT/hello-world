def sort_points_by_distance(points):
    """
    对二维平面上的点按到原点的距离进行排序
    
    参数:
        points: 包含二维点的列表，每个点表示为[x, y]
        
    返回:
        按到原点距离从小到大排序后的点列表
    """
    # 使用距离的平方作为排序键（避免计算平方根，提高效率）
    return sorted(points, key=lambda p: p[0]**2 + p[1]** 2)


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
    sorted_points = sort_points_by_distance(test_points)
    
    # 输出结果
    print("原始点列表:", test_points)
    print("按到原点距离排序后的点列表:", sorted_points)
    
    # 输出距离验证
    print("\n距离验证:")
    for point in sorted_points:
        distance_sq = point[0]**2 + point[1]** 2
        print(f"点{point}，距离平方: {distance_sq}")
