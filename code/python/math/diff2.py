import sympy as sp

x, y = sp.symbols('x y')
z = 2 * x ** 2 - 2 * x * y + y ** 2 + x + 2 * y + 1
d = -sp.diff(z, x) / sp.diff(z, y)
print('原方程导数为：%s' % d)
