#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
#
from wsgiref.simple_server import make_server
from httpWSGI import application
httpd = make_server('',8000,application)
print('Server HTTP on port 8000...')
httpd.serve_forever()
