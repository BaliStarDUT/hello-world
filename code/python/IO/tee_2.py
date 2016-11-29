#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import sys
import os
from optparse import OptionParser
parser = OptionParser()
parser.add_option("-a", "--append",dest = "append",action = "store_true",default = False,help = "append the string")
options, args = parser.parse_args()
if not len(args)==1:
    print("One file needed.",file=sys.stderr)
    sys.exit()
if not os.path.exists(args[0]):
        print(args[0],": No such file or directory",file=sys.stderr)
        sys.exit()
if not os.path.isfile(args[0]):
    print(args[0],"is not a file.",file=sys.stderr())
    sys.exit()
if options.append:
        fd = open(args[0], 'a')
        tmpInput = sys.stdin.read()
        fd.write(tmpInput)
        fd.close()
        print(tmpInput)
else:
        fd = open(args[0], 'w')
        tmpInput = sys.stdin.read()
        fd.write(tmpInput)
        fd.close()
        print(tmpInput)
