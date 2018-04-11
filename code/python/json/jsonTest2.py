#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import json

def loadJson():
    f = open("services.json", encoding='utf-8')
    s = json.load(f)
    for ser in s['services']:
        #print(ser['domain'])
        for ser2 in ser['services']:
            print(ser2['name'])

loadJson()
