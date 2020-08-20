import matplotlib.pyplot as plt
import numpy as np
from mpl_toolkits.mplot3d import Axes3D

#Matplotlib
#plt.rc('font',family='Arial')

x = np.arange(-1, 1, 0.05)
y = np.arange(-1, 1, 0.05)
x, y = np.meshgrid(x, y)
z1 = np.sqrt(x ** 2 + y ** 2)
z2 = np.sqrt(2 - x ** 2 - y ** 2)
ax = Axes3D(plt.figure())
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False
ax.set_title('三重积分曲面')
ax.plot_surface(x, y, z1)
ax.plot_surface(x, y, z2)
plt.show()
