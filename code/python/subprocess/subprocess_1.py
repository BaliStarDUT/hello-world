#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from subprocess import Popen,PIPE
p = Popen('ifconfig',shell=True)
print(p)
p = Popen('chown james:james java_error_in_IDEA_24822.log',shell=True)
print(p)
print("main process")
sh1 = Popen(['ifconfig -a'],stdin=PIPE,stdout=PIPE,shell=True)
print(sh1.stdout.read())
sh2 = Popen(['ifconfig','eth0'],stdout=PIPE,stderr=PIPE)
print(sh2.communicate())
print("ifconfig pid:",sh2.pid)
