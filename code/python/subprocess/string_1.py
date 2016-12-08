#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys,os
import string
def isNum(s):
    for i in s:
        if i in string.digits:
            continue
        else:
            return False
    return True

if isNum(sys.argv[1]):
    print(sys.argv[1])
else:
    print("not a num")
#if __name__="__main__" 
for pid in os.listdir('/proc'):
    if isNum(pid):
        print(pid)
for pid in os.listdir('/proc'):
    if pid.isdigit():
        print(pid)
