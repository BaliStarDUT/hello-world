#!/usr/bin/env/python3
# -*- coding: utf-8 -*-

from datetime import datetime
import os

pwd = os.path.abspath('.')

print('pwd:'+pwd)
print('	Size	Last Modified Time	Name')
print('-----------------------------------------------------------------------------------')
for f in os.listdir(pwd):
    fsize = os.path.getsize(f)
    #atime = datetime.fromtimestamp(os.path.getatime(f)).strftime('%Y-%m-%d %H:%M:%S')
    mtime = datetime.fromtimestamp(os.path.getmtime(f)).strftime('%Y-%m-%d %H:%M:%S')
    #ctime = datetime.fromtimestamp(os.path.getctime(f)).strftime('%Y-%m-%d %H:%M:%S')
    flag = '/' if os.path.isdir(f) else ''
    #print('%10d %s %s %s %s%s' % (fsize,atime,mtime,ctime,f,flag))
    print('%10d %s %s%s' % (fsize,mtime,f,flag))