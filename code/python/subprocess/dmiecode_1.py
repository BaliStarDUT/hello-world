#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys,os
from subprocess import Popen,PIPE

p = Popen(['dmidecode'],stdout=PIPE)
data = p.stdout
fileName = 'dmi.txt'
while True:
    line = data.readline()
    #if line.statswith('System Information'):
    #    line = data.readlines(4)
    if line:
        with open(fileName,'a') as fileSave:
            fileSave.write(line)
        print(line)
    else:
        break

