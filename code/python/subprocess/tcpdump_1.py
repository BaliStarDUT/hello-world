#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from subprocess import Popen,PIPE,TimeoutExpired
import sys
import os
import subprocess

def get_data():
    #data=[]
    p = Popen(['tcpdump -nn -e -i wlan0  port 80 -l'],stdin=PIPE,stdout=PIPE,stderr=PIPE,shell=True)
    try:
        print("wait 5s to collect data......")
        p.wait(timeout=5)
        p.terminate()
        p.send_signal(subprocess.signal.SIGSTOP)
    except TimeoutExpired:
        p.terminate()
        #p.send_signal(subprocess.signal.SIGSTOP)
        print("proc terminate after 15s:",p.returncode)
        print(type(p))
    finally:
        p.terminate()
        p.kill()
        print('finally')
        data = p.stdout.readlines()
        print(p.stderr.readline())
    return data

def get_file():
    with open('mac.txt','r') as fileRead:
        data = fileRead.readlines() 
    return data

def get_mac(fileLines):
    mac_ip_dic={}
    mac_lines=[]
    for line in fileLines:
        if line.strip():
            print(line)
            lineList = str(line,encoding='utf-8').split(', ')
            mac = lineList[0].split(' ')[1]
            #print(mac)
            ip = lineList[2].split(' ')[2]
            #print(ip)
            mac_ip_dic[mac] = ip;
    return mac_ip_dic       
if __name__=="__main__":
    dic = get_mac(get_data())
    print(type(dic))
    print(dic)
