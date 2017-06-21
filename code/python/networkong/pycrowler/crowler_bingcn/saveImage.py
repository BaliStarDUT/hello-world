#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import urllib.request
import urllib.parse
import parseHtml
import request
import logging
import sys

logging.basicConfig(level=logging.DEBUG,
                format='%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s %(message)s',
                datefmt='%Y-%m-%d %H:%M:%S',
                filename='bingcn.log',
                filemode='a'
                )

host="http://cn.bing.com"
logging.info("From:"+host)
html = request.getHtml(host)
#imageurl = parseHtml.getJpg(html)
#imageurl =  parseHtml.bing_fresh_Parser(html)
imageurl =  parseHtml.getJpg(html)
logging.info("Image url:"+imageurl)
fileName = imageurl.split('/')[-1:][0]
logging.info("Image file name:"+fileName)
#传入图片地址，文件名，保存单张图片
def saveImg(imageURL,fileName):
    url = (imageURL)
    logging.info('Image file url:'+url)
    #url=urllib.parse.urlencode(url)
    u = urllib.request.urlopen(url)
    data = u.read()
    f = open(fileName, 'wb')
    f.write(data)
    logging.info("Save file :"+imageURL)
    f.close()

saveImg(imageurl,fileName)
