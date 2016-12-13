#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import os
import sys
import urllib.request
import json
CUR_DIR = os.path.dirname(__file__)
CONF_DIR = os.path.join(os.path.abspath(CUR_DIR),'hosts')
if not os.path.exists(CONF_DIR):
    os.mkdir(CONF_DIR)
HOST_TEMP = """ define host{
    use     linux_server
    host_name    %(hostname)s
    alias    %(hostname)s
    address    %(ip)s
    }
"""
url ='http://192.168.0.107:81/hostinfo/getjson/'
req = urllib.request.urlopen(url)
data = json.loads(str(req.read(),encoding='utf-8'))
conf = ''
print(data)
for hg in data['data']:
    print(hg)
    for h in hg['members']:
        print(h)
        conf+=HOST_TEMP % h

hostconf = os.path.join(CONF_DIR,'hosts.cfg')
with open(hostconf,'w') as fd:
    fd.write(conf)
print(conf)

