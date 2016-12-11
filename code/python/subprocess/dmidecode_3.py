#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from subprocess import Popen,PIPE

def getIfconfig():
    p = Popen(['ifconfig'],stdout=PIPE)
    return p.stdout.read()
    #data = str(p.stdout.read(),encoding='utf-8')
    #dataList = data.split('\n\n')
    #resultList = [i for i in dataList if i and not i.startswith('lo') and not i.startswith('eth0')]
    #return resultList

def getDmi():
    p = Popen(['dmidecode'],stdout=PIPE)
    data =p.stdout.read()
    return data

def parseData(data):
    parsed_data = []
    new_line = ''
    data = [i for  i in str(data,encoding='utf-8').split('\n') if i]
    for line in data:
       if line[0].strip():
           parsed_data.append(new_line)
           new_line = line+'\n'
       else:
           new_line+= line+'\n'
    parsed_data.append(new_line)
    return [ i for i in parsed_data if i]

def parseDmi(str_parsed_data):
    #str_parsed_data = str(parsed_data,encoding='utf-8')
    #print(str_parsed_data)
    parsed_data = [i for i in str_parsed_data if i.startswith('System Information')]
    parsed_data_list = parsed_data[0].split('\n')[1:-2]
    #print(parsed_data_list)
    #for dmiLine in parsed_data_list:
    #    print(dmiLine.strip())
    #    dic = dict(dmiLine.strip().split(': '))
    #print(dic)
    #print(parsed_data[0].split('\n')[1].strip())
    return dict([i.strip().split(': ') for i in parsed_data_list])

def parseIfconfig(parsed_data):
    dic = {}
    parsed_data = [i for i in parsed_data if not i.startswith('lo') and not i.startswith('eth0')]
    for lines in parsed_data:
        line_list = lines.split('\n')
        devname = line_list[0].split()[0]
        macaddr = line_list[0].split()[-1]
        ipaddr = line_list[1].split()[1].split(':')[1]
        break
    dic['ip']= ipaddr
    return dic

def getHostname(fileName):
    with open(fileName) as fd:
        for line in fd:
            if line:
                hostname = line.strip()
                break
    return {'hostname':hostname}

def getOsVer(fileName):
    with open(fileName) as fd:
        for line in fd :
            osver = line.strip()
            break
    return {'osver':osver}
def getCpuInfo(f):
    num = 0
    with open(f) as fd:
        for line in fd:
            if line.startswith('processor'):
                num+=1
            if line.startswith('model name'):
                cpu_model = line.strip().split(':')[1].split()
                #print(cpu_model)
                cpu_model_1 = cpu_model[0] +' ' +cpu_model[-1]
    return {'cpu_num':num,'cpu_model':cpu_model_1}

def getMemInfo(f):
    with open(f) as fd:
        for line in fd:
            if line.startswith('MemTotal'):
                mem = int(line.split()[1].strip())
                memM = mem/1024
                break
    return {'memory':memM}

if __name__ == "__main__":
    dic = {}
    data = getIfconfig()
    parsedataIp = parseData(data)
    #print(parseIfconfig(parsedataIp))
    parseIfconfig = parseIfconfig(parsedataIp)
    data_dmi = getDmi()
    parsed_data_dmi = parseData(data_dmi)
    #print(parseDmi(parsed_data_dmi))
    dmi = parseDmi(parsed_data_dmi)
    hostname = getHostname('/proc/sys/kernel/hostname')
    osver = getOsVer('/etc/issue')
    cpu = getCpuInfo('/proc/cpuinfo')
    mem = getMemInfo('/proc/meminfo')
    dic.update(parseIfconfig)
    dic.update(dmi)
    dic.update(hostname)
    dic.update(osver)
    dic.update(cpu)
    dic.update(mem)
    print(dic)
