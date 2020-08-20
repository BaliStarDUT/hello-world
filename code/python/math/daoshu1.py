import sympy as sp

x = sp.Symbol('x')
y = x ** 10 + 2 * (x - 10) ** 9
for n in range(1, 12):
    y = d = sp.diff(y)
    print('第%2d阶导数为：%s' % (n, d))
