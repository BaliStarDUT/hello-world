import matplotlib.pyplot as plt
import numpy as np
import sympy as sp

# 导数与微分
x = sp.Symbol('x')
f = 2 * x ** 3 + 3 * x ** 2 - 12 * x + 7
d = sp.diff(f)
print('%s 的导函数为：%s' % (f, d))
y_d = d.evalf(subs={x: -1})
y_h = f.evalf(subs={x: -1})
print('将x=-1代入导函数求解为：%d' % (y_d))
print('将x=-1代入原函数求解为：%d' % (y_h))
f_d = y_d * (x + 1) + y_h
print('得出切线方程为：%s' % f_d)
# 绘制函数图和切线图
x = np.arange(-4, 3, 0.01)
y1 = 2 * x ** 3 + 3 * x ** 2 - 12 * x + 7
y2 = 8 - 12 * x
plt.title('函数y=2*x**3+3*x**2-12*x+7以及当x=-1时的切线')
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False
plt.plot(x, y1, x, y2)
plt.show()
