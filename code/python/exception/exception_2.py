#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys,os
import subprocess
class FuncError(Exception):
    def __self__(self):
        print("This is a FuncException")
    def __str__(self):
        print("This is a FuncException class")

def  readFileWithException(fileName):
    if not os.path.isfile(fileName):
        raise IOError
    else:
        p=subprocess.Popen(['cat',fileName],stdin=subprocess.PIPE,stdout=subprocess.PIPE,stderr=subprocess.PIPE)
    list=p.stdout
    for line in list :
        print(line)
if __name__ =="__main__":
    try:
        if not len(sys.argv)==2:
            raise IndexError
        else:
            readFileWithException(sys.argv[1])
    except IndexError:
        print('Input a file name')
        sys.exit()
    except IOError:
        print('file not exist')
        sys.exit()
    except Exception:
        print("exception")
        sys.exit()
