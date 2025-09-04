import random
import math

def generate_normal_distribution(mean, std_dev, count):
    """
    生成符合正态分布的随机数
    
    参数:
        mean: 均值
        std_dev: 标准差
        count: 生成的数量
        
    返回:
        包含count个符合正态分布的随机数列表
    """
    result = []
    for _ in range(count):
        # 使用Box-Muller变换生成正态分布随机数
        u1 = random.random()
        u2 = random.random()
        z0 = math.sqrt(-2.0 * math.log(u1)) * math.cos(2.0 * math.pi * u2)
        # 转换为指定均值和标准差的分布
        result.append(mean + z0 * std_dev)
    return result

def generate_2d_points(count):
    """生成二维点坐标，x和y分别符合指定的正态分布"""
    # 生成x坐标：均值10，标准差5
    x_coords = generate_normal_distribution(10, 5, count)
    # 生成y坐标：均值-10，标准差5
    y_coords = generate_normal_distribution(-10, 5, count)
    
    # 组合成二维点
    return [(x, y) for x, y in zip(x_coords, y_coords)]

# 示例用法
if __name__ == "__main__":
    # 生成10个二维点
    num_points = 100
    points = generate_2d_points(num_points)
    
    print(f"生成的{num_points}个二维点坐标：")
    for i, (x, y) in enumerate(points, 1):
        # print(f"点{i}: ({x:.2f}, {y:.2f})")
        print("[{},{}],".format(x,y))
