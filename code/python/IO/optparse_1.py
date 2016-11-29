#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from optparse import OptionParser
import sys,os
def options():
    parser = OptionParser("Usage:%prog [file] [file2] ...")
    parser.add_option("-c","--chars",dest="chars",action="store_true",default=True,help="only count characters",)
    parser.add_option("-l","--lines",dest="lines",action="store_true",default  =True,help="only count lines")
    parser.add_option("-w","--wors",dest="words",action="store_true",default=True,help="only count words",)

    options,args=parser.parse_args()
    print(options,args)
    return options,args

def get_count(data):
    chars = len(data)
    words = len(data.split())
    lines = data.count('\n')
    print(words,lines,chars)
    return lines,words,chars
def print_wc(options,lines,words,chars,fn):
    if options.lines:
        print("lines=",lines)
    if options.words:
        print("words=",words)
    if options.chars:
        print("chars=",chars)
    print(fn)

def main():
    options,args = options()
    if args:
	fn = args[0]
	with open(args[0]) as fd:
	    data = fd.read()
    else:
	fn = ""
	print("Please input what to wc:")
	data = sys.stdin.read()

    lines,words,chars = get_count(data)
    print_wc(options,lines,words,chars,fn)

main()
