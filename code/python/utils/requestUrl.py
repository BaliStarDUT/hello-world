#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from urllib.request import urlopen
for line in urlopen("https://www.zhihu.com"):
    line = line.decode("utf-8")
    if '<h1' in line or '<h2' in line:
        print(line)
