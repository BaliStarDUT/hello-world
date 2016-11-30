#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import hashlib
import sys
def md5sum(fileName):
    md5 = hashlib.md5()
    fd = open(fileName)
    while True:
        data = fd.read(1024*4)
        if data:
            md5.update(data)
        else:
            break
    fd.close()
    return md5.hexdigest()
def md5sum2(fileName):
    md5 = hashlib.md5()
    with open(fileName) as fd:
        while True:
            data = fd.read(1024*4)
            if data:
                md5.update(data)
            else:
                break
    return md5.hexdigest()


if __name__=="__main__":
    try:
        print(md5sum2(sys.argv[1]))
    except IndexError:
        print("a file is needed.")
