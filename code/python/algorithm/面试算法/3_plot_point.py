import matplotlib.pyplot as plt
import numpy as np  # 用numpy生成正态分布数据（比手动实现更高效稳定）

# ---------------------- 步骤1：生成二维坐标点 ----------------------
def generate_2d_points(num_points=50):
    """
    生成指定数量的二维点：
    - x坐标：均值10，标准差5的正态分布
    - y坐标：均值-10，标准差5的正态分布
    """
    # numpy的random.normal(均值, 标准差, 数量)生成正态分布数据
    x_coords = np.random.normal(loc=10, scale=5, size=num_points)
    y_coords = np.random.normal(loc=-10, scale=5, size=num_points)
    
    # 组合为二维点列表（每个点是(x, y)元组）
    return list(zip(x_coords, y_coords))

# 生成50个二维点（数量可自定义）
points = generate_2d_points(num_points=50)
# 拆分x、y坐标（方便绘图）
x_list = [p[0] for p in points]
y_list = [p[1] for p in points]

# ---------------------- 步骤2：绘制二维坐标系与点 ----------------------
# 1. 创建绘图对象（可指定画布大小，单位为英寸）
plt.figure(figsize=(8, 6))  # 宽8、高6，避免图表过挤


# 设置字体（支持中文）
plt.rcParams["font.family"] = ["SimHei", "WenQuanYi Micro Hei", "Heiti TC"]


# 2. 绘制二维点（scatter：散点图，适合离散坐标点）
# marker='o'：点的形状为圆形；s=50：点的大小；c='blue'：点的颜色；alpha=0.7：透明度（避免重叠遮挡）
plt.scatter(
    x=x_list, 
    y=y_list, 
    marker='o', 
    s=50, 
    c='blue', 
    alpha=0.7, 
    label='二维坐标点'  # 图例标签，用于说明点的含义
)

# 3. 绘制原点（0,0）和数据中心（x均值10，y均值-10），用不同颜色标注关键位置
plt.scatter(x=0, y=0, marker='*', s=200, c='red', label='原点 (0,0)')  # 原点用星型标记
plt.scatter(x=10, y=-10, marker='s', s=100, c='orange', label='数据中心 (10,-10)')  # 数据中心用方形标记

# ---------------------- 步骤3：美化图表（提升可读性） ----------------------
# 添加坐标轴标签（说明x、y轴含义）
plt.xlabel('X 轴', fontsize=12)
plt.ylabel('Y 轴', fontsize=12)

# 添加标题（说明图表内容）
plt.title('二维坐标点分布图\n（x：均值10/标准差5；y：均值-10/标准差5）', fontsize=14, pad=20)

# 添加网格（便于读取点的大致坐标）
plt.grid(True, linestyle='--', alpha=0.5)  # linestyle='--'：虚线网格；alpha=0.5：半透明

# 添加图例（说明不同标记的含义）
plt.legend(fontsize=10, loc='best')  # loc='best'：自动选择最佳图例位置

# 调整坐标轴范围（可选，避免点超出视野或留白过多）
# 这里根据数据分布动态调整：x范围=数据范围±2，y同理
plt.xlim(min(x_list)-2, max(x_list)+2)
plt.ylim(min(y_list)-2, max(y_list)+2)

# ---------------------- 步骤4：显示/保存图表 ----------------------
plt.show()  # 显示图表（运行后会弹出窗口）
# plt.savefig('2d_points_plot.png', dpi=300, bbox_inches='tight')  # 可选：保存为图片（dpi=分辨率）