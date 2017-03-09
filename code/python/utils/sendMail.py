#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'

import smtplib

server = smtplib.SMTP('localhost')

server.sendmail('james@james.com', 'james@james.com',
"""To: jcaesar@example.org
From: soothsayer@example.org
HelloWorld
Beware the Ides of March.
""")
server.quit()
