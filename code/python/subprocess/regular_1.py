#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import re
from subprocess import Popen,PIPE
def getIfconfig():
    p = Popen(['ifconfig'],stdout=PIPE)
    #return p.stdout.read()
    data = p.stdout.read()
    dataList = str(data,encoding='utf-8').split('\n\n')
    resultList = [i for i in dataList if i and not i.startswith('lo')]# and not i.startswith('eth0')
    return resultList


def parseIfconfig(data):
    #print(data)
    re_devname = re.compile(r'(br|eth|lo|wlan)[\d:]+',re.M)
    re_mac = re.compile(r'HWaddr [0-9A-Fa-f:]{17}',re.M)
    re_ip = re.compile(r'inet addr:[\d.]{7,15}',re.M)
    devname = re_devname.search(data).group()
    #print(devname)
    mac = re_mac.search(data).group()
    #print(mac)
    ip = re_ip.findall(data)
    #print(ip)
    return {devname:[ip,mac]}

if __name__ == '__main__':
    data = getIfconfig()
    for i in data:
        print(parseIfconfig(i))
