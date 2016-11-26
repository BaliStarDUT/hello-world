#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys
print("Please input the file name:")
#tee = sys.stdin
#fileName = tee.read()
fileName = input()
print("The file name :",fileName)
print("Please input content:")
#fileContent = tee.read()
fileContent = input()
if(fileName!=""):
    with open(fileName+".txt",'w') as fileSave:
        fileSave.write(fileContent)
print("File have saved.")
