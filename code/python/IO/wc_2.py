#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from sys import stdin

data = stdin.read()
chars = len(data)
words = len(data.split())
lines = data.count('\n')
#print "%(lines)s %(words)s %(chars)s" %locals()
print(lines,words,chars)
