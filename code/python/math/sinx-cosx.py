import matplotlib.pyplot as plt
import numpy as np

a = np.linspace(0, 2 * np.pi, 50)
b = np.log(a)
plt.plot(a,b)								#生成一个正弦函数图
mask = b >= 0
plt.plot(a[mask], b[mask], 'bo')			#符合条件的标注蓝色圆点
mask = (b >= 0) & (a <= np.pi / 2)
plt.plot(a[mask], b[mask], 'go')			#符合条件的标注绿色圆点
plt.show()
