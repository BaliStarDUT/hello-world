#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import urllib.request
import re
import logging

def getHtml(url):
    page = urllib.request.urlopen(url)
    html = page.read()
    if html:
        logging.debug("Get Response:"+str(len(html)))
    else:
        logging.warning("Request failed!")
    return html.decode('utf-8')

def getImg(html):
    #reg = r'(url:.*jpg)'
    #reg = r'(.{125}jpg)'
    reg = r'(url:.{10,90}jpg)'
    imgre = re.compile(reg)
    imglist = re.findall(imgre,html)
    #imglist = re.match('jpg','jpghtmljpg')
    if imglist:
        print(imglist)
        print(len(imglist))
    

    return imglist      

#html = getHtml("http://cn.bing.com/")
#print(html)
#print(type(html))
#jpglist=getImg(html)
#print(html)
