#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from subprocess import Popen,PIPE

def getDmi():
    p = Popen(['dmidecode'],stdout=PIPE)
    data =p.stdout.read()
    return data

def parseDmi(data):
    lines=[]
    line_in =False
   # print(type(data))
    dmi_list = [i for i in str(data,encoding='utf-8').split('\n') if i]
    for line in dmi_list:
        if line.startswith('System Information'):
            line_in = True
            continue
        if line_in:
            if not line[0].strip():
                lines.append(line.strip())
            else:
                break
    return lines
def dmiDic():
    dmi_dic={}
    data = getDmi()
    lines = parseDmi(data)
    dic = dict([i.strip.split(':') for i in lines])
    dmi_dic['vendor'] = dic['Manufactor']
    dmi_dic['Product'] = dic['']

if __name__=='__main__':
    data = getDmi()
    dataInf = parseDmi(data)
    for i in dataInf:
        print(i)
