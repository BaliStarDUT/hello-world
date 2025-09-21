import pandas as pd 

heros = pd.read_csv("../data/英雄.csv",header=1)

heros.head()

heros[]
heros.iloc

from matplotlib import pyplot as plt
plt.rcParams['font.family'] = 'monospace'

colors = ["red","blue","green","yellow","black"]

markers = ['o','^','s','1','2']

plt.figure(dpi=150)

for i in range(len(X3)):
    plt.scatter(X3[i,0],X3[i,1],c=colors[y[i]],marker=markers[y[i]])
    plog.scatter

plt.show()