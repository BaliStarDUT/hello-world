#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from subprocess import Popen,PIPE
import sys
import os

def get_data():
    p = Popen([' tcpdump -nn -e -i wlan0  port 80 -l '],stdin=PIPE,stdout=PIPE,stderr=PIPE)
    data = p.stdout
    return data

def get_file():
    with open('mac.txt','r') as fileRead:
        data = fileRead.read() 
    return data
def get_file1():
    with open('mac.txt','r') as fileRead:
        return fileRead


def get_mac(file):
    while True:
        line = file.readline()
        if line:
            new_line = line.split(',')[1].strip()
            print(new_line)
        else:
            print("Stop")
            break
if __name__=="__main__":
    get_mac(get_file1())
