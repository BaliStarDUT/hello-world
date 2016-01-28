#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import logging
import pdb
logging.basicConfig(level=logging.INFO)
s='0'
n=int(s)
pdb.set_trace()
logging.info('n=%d' % n)
print(10/n)
def foo(s):
    n=int(s)
    assert n !=0,'n is zero!'
    return 10/n
def main():
    foo('0')
