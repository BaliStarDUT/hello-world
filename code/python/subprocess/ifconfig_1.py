#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import os,sys
from subprocess import PIPE,Popen

def ifconfigFun():
    p = Popen(['ifconfig'],stdout=PIPE)
    data = str(p.stdout.read(),encoding='utf-8')
    dataList = data.split('\n\n')
    resultList = [i for i in dataList if i and not i.startswith('lo') and not i.startswith('eth0')]
    return resultList

def parseIfconfig(data):
    dic = {}
    for lines in data:
        lineList = lines.split('\n')
        devName = lineList[0].split()[0]
        macAddr = lineList[0].split()[-1]
        ipAddr = lineList[1].split()[1].split(':')[1]
        dic[devName] = [ipAddr,macAddr]
    return dic

if __name__=='__main__':
    data = ifconfigFun()
    dic = parseIfconfig(data)
    print(dic)

