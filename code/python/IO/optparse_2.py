#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from optparse import OptionParser
import sys,os
def opt():
    parser = OptionParser("Usage:%prog [file] [file2] ...")
    parser.add_option("-c","--chars",dest="chars",action="store_true",default=False,help="only count characters",)
    parser.add_option("-l","--lines",dest="lines",action="store_true",default  =False,help="only count lines")
    parser.add_option("-w","--words",dest="words",action="store_true",default=False,help="only count words",)

    options,args=parser.parse_args()
    if not (options.lines or options.words or options.chars):
        options.lines,options.words,options.chars = True,True,True
    print(options,args)
    return options,args
def get_count(data):
    chars = len(data)
    words = len(data.split())
    lines = data.count('\n')
    #print(words,lines,chars)
    return lines,words,chars
def print_wc(options,lines,words,chars,fn):
    if options.lines:
        #print("lines=",lines,end=' ')
        print(lines,end=' ')
    if options.words:
        #print("words=",words,end=' ')
        print(words,end=' ')
    if options.chars:
        #print("chars=",chars,end=' ')
        print(chars,end=' ')
    print(fn)
def main():
    options,args=opt()
    if args:
        total_lines,total_words,total_chars=0,0,0
        for fn in args:
            if os.path.isfile(fn):
                with open(fn) as fd:
                    data=fd.read()
                lines,words,chars=get_count(data)
                print_wc(options,lines,words,chars,fn)
                total_lines +=lines
                total_words+=words
                total_chars += chars
            elif os.path.isdir(fn):
                print(fn,'is a directory',file=sys.stderr)
            else:
                print(fn,'No such file or directory',file=sys.stderr)
        if(len(args)>1):
            print_wc(options,total_lines,total_words,total_chars,"total")
    else:
        data=sys.stdin.read()
        fn=''
        lines,words,chars=get_count(data)
        print_wc(options,lines,words,chars,fn)
if __name__ =="__main__":
    main()
