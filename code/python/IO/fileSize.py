#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from optparse import OptionParser
import sys,os
parser = OptionParser("Usage:%prog [file] [file2] ...")
parser.add_option("-H","--human",dest="human",action="store_true",default=False,help="display in humanlity",)

options,args=parser.parse_args()
print(options,args)
try:
    fn=args[0]
except IndexError:
    print("Please follow a filename")
    sys.exit()
if not os.path.exists(fn):
    print(fn,"is not exist")
    sys.exit()
fileSize = os.path.getsize(args[0])

if options.human:
    if fileSize>1024 and fileSize<(1024*1024) :
        print("fileSize=",fileSize/1024,"KB")
    elif fileSize >=(1024*1024):
        print("fileSize=",fileSize/1024/1024,"MB")
else:
    print("fileSize=",fileSize,"Byte")
