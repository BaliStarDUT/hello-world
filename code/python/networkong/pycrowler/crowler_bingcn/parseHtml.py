#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from bs4 import BeautifulSoup
import json
import re
import logging

html = """
<html><head><title>The Dormouse's story</title></head>
<body>
<p class="title" name="dromouse"><b>The Dormouse's story</b></p>
<p class="story">Once upon a time there were three little sisters; and their names were
<a href="http://example.com/elsie" class="sister" id="link1"><!-- Elsie --></a>,
<a href="http://example.com/lacie" class="sister" id="link2">Lacie</a> and
<a href="http://example.com/tillie" class="sister" id="link3">Tillie</a>;
and they lived at the bottom of a well.</p>
<p class="story">...</p>
"""

host="http://cn.bing.com"
def bingParser(html):
    #soup=BeautifulSoup(html,"html.parser")
    soup=BeautifulSoup(open('Bing.html'),"html.parser")
    #print(soup.prettify())
    print(soup.title)
    #print(soup.a)
    print(type(soup.a))
    print(soup.select('#bgDiv'))
    style = (soup.select('#bgDiv')[0].attrs['style']).strip()
    #print(soup.select('#bgDiv').attrs['background-image'])
    print(style)
    json_style=json.dumps(style)
    print(json_style)
    imageurl=style.strip().split(';')[-3:-2]
    #print(imageurl[0].split('"')[1])
    imageUrl = (imageurl[0].split('"')[1])
    #imageUrl = (imageurl[0].split(':')[1].strip().split('"')[1])
    print(imageUrl)
    return imageUrl
def bing_fresh_Parser(html):
    soup=BeautifulSoup(html,"html.parser")
    #soup=BeautifulSoup(open('Bing.html'),"html.parser")
    #print(soup.prettify())
    print(soup.title)
    #print(soup.a)
    print(type(soup.a))
    #print(soup.style.string)
    print(soup.select('#bgDiv'))
    style = (soup.select('#bgDiv')[0].attrs['style']).strip()
    #print(soup.select('#bgDiv').attrs['background-image'])
    print(style)
    json_style=json.dumps(style)
    #print(json_style)
    imageurl=style.strip().split(';')[-2:-1]
    imageUrl = (imageurl[0].split(':')[1].strip().split('"')[1])
    print(imageUrl)
    return imageUrl

def getImg(html):
    reg = r'(.+\link)'
    imgre = re.compile(reg)
    #print(html)
    imglist = re.findall(imgre,html)
    print(len(imglist))
    print(imglist)
    jpg1 = re.search(r'<p',html)
    jpg2 = re.findall(r'<p',html)
    if jpg1:
        print(jpg1)
        print(jpg1.span())
        print(jpg1.groups())
    if jpg2:
        print(jpg2)
        print(len(jpg2))
    
    print(re.match('www', 'www.runoob.com').span())  # 在起始位置匹配
    print(re.match('com', 'www.runoob.com'))         # 不在起始位置匹配
    line = "Cats are smarter than dogs"
    matchObj = re.match( r'(.*) are (.*?) .*', line, re.M|re.I)
    if matchObj:
        print ("matchObj.group() : ", matchObj.group())
        print ("matchObj.groups() : ", matchObj.groups())
        print ("matchObj.group(1) : ", matchObj.group(1))
        print ("matchObj.group(2) : ", matchObj.group(2))
    else:
        print ("No match!!")
    return imglist    
def getJpg(html):
    reg = r'(url:.{10,90}jpg)'
    logging.debug("Using re "+reg+" to get Jpg")
    jpgre= re.compile(reg)
    jpglist=re.findall(jpgre,html)
    if jpglist:
        logging.debug("Get jpg list("+str(len(jpglist))+"):"+str(jpglist))
        jpgUrl = jpglist[0].split('"')[1]
        imageUrl = host+jpgUrl
        logging.info("Get jpg url:"+imageUrl)
        return imageUrl

#imglist = getImg(html)
#print(imglist.group())
#bing_fresh_Parser()
