#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys
print(sys.argv)
f = open(sys.argv[1])
for i in f:
    print i,
f.close()
