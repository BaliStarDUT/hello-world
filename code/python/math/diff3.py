import sympy as sp

t = sp.Symbol('t')
x = sp.exp(t) * sp.cos(t)
y = sp.exp(t) * sp.sin(t)
d = sp.diff(y, t) / sp.diff(x, t)
print('原参数方程导数结果为：%s' % d)
d = sp.simplify(d)
print('原参数方程导数化简为：%s' % d)
