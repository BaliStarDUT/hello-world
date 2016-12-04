#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys,os
import hashlib
def md5sum2(fileName):
    md5 = hashlib.md5()
    with open(fileName) as fd:
        while True:
            data = fd.read(1024*4)
            if data:
                md5.update(data.encode('utf8'))
            else:
                break
    return md5.hexdigest()

def gen_dic_file_md5(topdir):
    dic ={} 
    a = os.walk(topdir)
    for p,d,f in a:
        for i in f:
            fn =os.path.join(p,i)
            md5 = md5sum2(fn)
            if dic.__contains__(md5):
                dic[md5].append(fn)
            else:
                dic[md5] = [fn]
    return dic
if __name__=="__main__":
    try:
        dic = gen_dic_file_md5(sys.argv[1])
        for k,v in dic.items():
            if len(v)>1:
                print(v)
    except IndexError:
        print("a file is needed.")

