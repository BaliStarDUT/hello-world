#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys,os
#print(sys.argv)
try:
    fn = sys.argv[1]
except IndexError:
    print("please follow a argument")
    sys.exit()
if not os.path.exists(fn):
    print(fn," is not exist")
    sys.exit()
fd = open(sys.argv[1])
data = fd.read()
chars = len(data)
words = len(data.split())
lines = data.count('\n')
print(words,lines,chars)
