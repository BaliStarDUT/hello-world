#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import urllib.request
import urllib.parse
import parseHtml
import request
host="http://cn.bing.com"
html = request.getHtml(host)
#print(html)
#imageurl = parseHtml.getJpg(html)
#imageurl =  parseHtml.bing_fresh_Parser(html)
imageurl =  parseHtml.getJpg(html)
print(imageurl)
fileName = imageurl.split('/')[-1:][0]
print(fileName)
#传入图片地址，文件名，保存单张图片
def saveImg(imageURL,fileName):
    url = (imageURL)
    print(url)
    #url=urllib.parse.urlencode(url)
    u = urllib.request.urlopen(url)
    data = u.read()
    f = open(fileName, 'wb')
    f.write(data)
    print("Save file :",imageURL)
    f.close()

saveImg(imageurl,fileName)
