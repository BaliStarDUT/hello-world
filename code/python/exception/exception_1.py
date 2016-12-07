#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
class FuncError(Exception):
    def __self__(self):
        print("This is a FuncException")
    def __str__(self):
        print("This is a FuncException class")

def  fun():
    raise FuncError()
try:
    raise Exception
    fun()
except FuncError :
    print('funcError')
except NameError:
    print("name error")
except Exception:
    print("Exception")
print('hello world')
