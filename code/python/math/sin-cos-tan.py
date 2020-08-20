#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Time    : 18/2/11 上午11:34
# @Site    :
# @File    : pic.py
# @Software: PyCharm

import  numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-10, 10, 1000)
a = np.sin(x)
b = np.cos(x)
c = np.tan(x)
# d = np.log(x)

plt.figure(figsize=(8,4))
plt.plot(x,a,label='$sin(x)$',color='green',linewidth=0.5)
plt.plot(x,b,label='$cos(x)$',color='red',linewidth=0.5)
plt.plot(x,c,label='$tan(x)$',color='blue',linewidth=0.5)
# plt.plot(x,d,label='$log(x)$',color='grey',linewidth=0.5)

plt.xlabel('Time(s)')
plt.ylabel('Volt')
plt.title('PyPlot')
plt.xlim(0,10)
plt.ylim(-5,5)
plt.legend()
plt.show()
