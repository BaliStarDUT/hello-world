#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import json

def loadFile():
    f = open("services.sql", encoding='utf-8') 
    f1 = open("services.json", encoding='utf-8')
    s = json.load(f1)
    l=f.readline()
    print(l.split('|')[1].strip())
    while l!= '':
        l=f.readline()
        #print(l.split('|')[1].strip())
        trip = l.split('|')[1].strip()
        for ser in s['services']:
            #print(ser['domain'])
            for ser2 in ser['services']:
                #print(ser2['name'])
                sername = ser2['name']
                if trip == sername:
                    print(trip)



def loadJson():
    f = open("services.json", encoding='utf-8')
    s = json.load(f)
    for ser in s['services']:
        #print(ser['domain'])
        for ser2 in ser['services']:
            print(ser2['name'])

#loadJson()
loadFile()
