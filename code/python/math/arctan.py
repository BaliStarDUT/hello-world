import matplotlib.pyplot as plt
import numpy as np
import sympy as sp

# 求函数 y=arctan(1/x) 的左右极限
x = sp.Symbol('x')
fr = sp.atan(1 / x)
xl = sp.limit(fr, x, 0, dir='-')
xr = sp.limit(fr, x, 0, dir='+')
print('%s 左极限是：%s' % (str(fr), str(xl)))
print('%s 右极限是：%s' % (str(fr), str(xr)))
# 绘制函数 y=arctan(1/x) 的图像
x = np.arange(-6, 6, 0.01)
y = np.arctan(1 / x)
plt.title('y=arctan(1/x)')
plt.plot(x, y)
plt.show()
