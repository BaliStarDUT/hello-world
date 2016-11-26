#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from optparse import OptionParser
import sys,os
parser = OptionParser("Usage:%prog [file] [file2] ...")
parser.add_option("-c","--chars",dest="chars",action="store_true",default=True,help="only count characters",)
parser.add_option("-l","--lines",dest="lines",action="store_true",default  =True,help="only count lines")
parser.add_option("-w","--wors",dest="words",action="store_true",default=True,help="only count words",)

options,args=parser.parse_args()
print(options,args)

data = sys.stdin.read()
chars = len(data)
words = len(data.split())
lines = data.count('\n')
print(words,lines,chars)

if options.chars:
    print("chars=",chars)
if options.words:
    print("words=",words)
if options.lines:
    print("lines=",lines)
