#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from urllib.request import urlopen
for line in urlopen("http://tycho.usno.navy.mil/cgi-bin/timer.pl"):
    line = line.decode("utf-8")
    if 'EST' in line or 'EDT' in line:
        print(line)
