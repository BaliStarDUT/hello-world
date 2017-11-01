#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import json
requirement_id = ["1631202.01","1631205.16","1631207.02"]

def addRequirement_id():
    with open('testlog.log','r') as f:
        data = json.load(f)
    print("load testlog:",data)
    for case in data['test_run_results'][0]['case_results']:
        #print(case)
        case["requirement_id"]=requirement_id
        #print(case)
    #print(data)
    with open('testlog.log','w') as o:
         json.dump(data,o)
if __name__ == '__main__':
    addRequirement_id()
