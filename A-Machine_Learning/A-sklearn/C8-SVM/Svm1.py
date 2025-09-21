#load the libraries we have been using
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt #Library for visualization

from sklearn import datasets

iris = datasets.load_iris()
X_w = iris.data[:, :2] #load the first two features of the iris data
y_w = iris.target #load the target of the iris data

#select only the first two classes for both the feature set and target set
#the first two classes of the iris dataset: Setosa (0), Versicolour (1)

X = X_w[y_w < 2]
y = y_w[y_w < 2]

#to visualize within IPython:
# %matplotlib inline
plt.figure(figsize=(10,7)) #change figure-size for easier viewing
plt.scatter(X_0[:,0],X_0[:,1], color = 'red')
plt.scatter(X_1[:,0],X_1[:,1], color = 'blue')