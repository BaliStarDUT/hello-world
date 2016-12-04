#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import os 
import sys
import operator
import hashlib_1
def sortList(fileList):
    size=[]
    index=0
    for fileName in fileList:
        print(len(size))
        print(size)
        if len(size)==0:
            size.append(fileName)
        else:
            if os.path.getsize(fileName)>=os.path.getsize(size[index]):
                size.append(fileName)
            else:
                temp = size[index]
                size[index]=fileName
                #size[index+1]=temp
                size.append(temp)
            index+=1
    print(size)
    return size
def sortList2(fileList):
    dic={}
    for fileName in fileList:
        f_size=os.path.getsize(fileName)
        dic[fileName] = f_size
    return dic
a = os.walk(sys.argv[1])
#print(a)
alist=[]
for p,d,f in a:
    for i in f:
        #print(os.path.join(p,i))
        #print(hashlib_1.md5sum2(os.path.join(p,i)))
        #print(os.path.getsize(os.path.join(p,i)))
        alist.append(os.path.join(p,i))
#print('alist=',len(alist))
dic = sortList2(alist)
sorted_dic = sorted(dic.items(),key=operator.itemgetter(1),reverse=True)
for k,v in sorted_dic[:10]:
    print(k,v)
#print(sorted_dic)


