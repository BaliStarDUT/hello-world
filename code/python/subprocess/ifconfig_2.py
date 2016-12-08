#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import os,sys
from subprocess import PIPE,Popen

def  ifconfigFun():
    p = Popen(['ifconfig'],stdout=PIPE)
    data = str(p.stdout.read(),encoding='utf-8')
    result =  [i for i in data.split('\n') if  i and not i.startswith('lo')]
    return result

def parseIp(data):
    new_line=''
    lines=[]
    for line in data:
        if line[0].strip():
            lines.append(new_line)
            new_line = line+'\n'
        else:
            new_line += line+'\n'
    return lines

if __name__=='__main__':
    data = parseIp(ifconfigFun())
    print(data)
