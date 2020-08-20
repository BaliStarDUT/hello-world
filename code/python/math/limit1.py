import matplotlib.pyplot as plt
import numpy as np
import sympy as sp

# 分析两个重要极限
x = sp.Symbol('x')
f1 = sp.sin(x) / x
f2 = (1 + 1 / x) ** x
x1 = sp.limit(f1, x, 0)
x2 = sp.limit(f2, x, 'oo')
print('%s 第一重要极限的值：%s' % (str(f1), str(x1)))
print('%s 第二重要极限的值：%s' % (str(f2), str(x2)))
# 绘制函数图像分析两个重要极限
x1 = np.arange(-30, 30, 0.01)
x2 = np.arange(0.01, 100, 0.1)
y1 = np.sin(x1) / x1
y2 = (1 + 1 / x2) ** x2
plt.figure(figsize=(12, 5))
plt.subplot(121)
plt.title('y=sin(x)/x')
plt.plot(x1, y1)
plt.subplot(122)
plt.title('y=(1+1/x)**x')
plt.plot(x2, y2)
plt.show()
