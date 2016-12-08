#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from subprocess import Popen,PIPE
import os
import sys
def getPid():
    p = Popen(['pidof','nginx'],stdout=PIPE,stderr=PIPE)
    pids = p.stdout.read().split()
    return pids

def parsePidFile(pids):
    sum = 0
    for i in pids:
        fn = os.path.join('/proc',str(i,encoding='utf-8'),'status')
        with open(fn) as fd:
            for line in fd:
                if line.startswith('VmRSS'):
                    mem = int(line.split()[1])
                    sum += mem
                    break
    return sum

def total_mem(f):
    with open(f) as fd:
        for line in fd:
            if line.startswith('MemTotal'):
                return int(line.split()[1])

if __name__=='__main__':
    pids= getPid()
    nginx_men = parsePidFile(pids)
    total = total_mem('/proc/meminfo')
    print("Nginx mem/TotalMem:",nginx_men,total,100*nginx_men/total)

#print(getPid())
