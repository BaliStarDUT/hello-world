#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys
def lineCount(fd):
    n = 0
    for i in fd.readlines():
        n = n+1
    return n

input = sys.stdin
print(lineCount(input))
input.close()
