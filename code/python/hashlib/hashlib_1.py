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
if __name__=="__main__":
    print(md5sum(sys.argv[1]))
