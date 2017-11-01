#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import os
print('Process(%s) start...' % os.getpid())
pid=os.fork()
print(pid)
if pid==0:
    print('Im child process (%s) and parent is %s.' %(os.getpid(),os.getppid()))
else:
    print('I (%s) just created a child process (%s).' % (os.getpid(),pid))
