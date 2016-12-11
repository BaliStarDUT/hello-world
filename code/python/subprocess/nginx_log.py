#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import re
def get_NginxLog():
    #re_nginx_log = re.compile(r'[\d.]{7,15} - - \[.{26}\] "[.*?]" [\d]{3} [\d]{3} "[.*?]" "[.*?]"')
    re_nginx_log = re.compile(r'(?P<ip>(\d{0,3}\.){0,3}\d{1,3}|[:\d]{3}) - - (?P<datatime>\[.{26}\]) (?P<head>".*?") (?P<code>[\d]{2,3}) (?P<length>[\d]{0,}) (?P<url>".*?") (?P<client>".*?")')
    with open('/var/log/nginx/access.log.1','r') as log:
        for line in log:
            #print(line)
            nginx_log = re_nginx_log.search(line).groupdict()
            print(nginx_log)
if __name__ == "__main__":
    get_NginxLog()

#127.0.0.1 - - [08/Dec/2016:23:09:56 +0800] "GET /b?c1=1&c2=7293931&c3=&c4=&c5=&c6=100 HTTP/1.1" 404 151 "http://static.youku.com/v20161208.0/v/swf/loader.swf" "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:47.0) Gecko/20100101 Firefox/47.0"

