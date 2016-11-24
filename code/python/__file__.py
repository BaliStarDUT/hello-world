#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import os,sys
print("sys.argv[0]=",sys.argv[0])
print("os.path.abspath(sys.argv[0])=",os.path.abspath(sys.argv[0]))
dirname,filename = os.path.split(os.path.abspath(sys.argv[0]))
print("running from:",dirname)
print("file is:",filename)

print("__file__=",__file__)
print("os.path.realpath(__file__)=",os.path.realpath(__file__))
