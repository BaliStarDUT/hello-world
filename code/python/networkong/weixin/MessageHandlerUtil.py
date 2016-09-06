#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
#解析微信发来的请求
from flask import Flask
from flask import request
app = Flask(__name__)

@app.route('/home',methods=['GET','POST'])
def home():
    #
    print("request come in...")
    print("parse request...")
    xml = request.stream
	
    print(request.data)
    print("build response...")
    responsexml = r'''<xml>
 <ToUserName><![CDATA[toUser]]></ToUserName>
 <FromUserName><![CDATA[fromUser]]></FromUserName> 
 <CreateTime>1348831860</CreateTime>
 <MsgType><![CDATA[text]]></MsgType>
 <Content><![CDATA[this is a test]]></Content>
 <MsgId>1234567890123456</MsgId>
 </xml>
 '''
    return responsexml
if __name__ == '__main__':
    app.run()
class DefaultSAXHandler(object):
    def start_element(self,name,sttrs):
        print('sax:start_element: %s, attrs: %s' % (name,str(sttrs)))
    def end_element(self,name):
        print('sax:end_element: %s' % name)
    def char_data(self,text):
        print('sax:char_data: %s ' % text)
xml1 = r'''<?xml version="1.0"?>
<ol>
    <li><a href="/python">Python</a></li>
    <li><a href="/ruby">Ruby</a></li>
</ol>
'''
handler = DefaultSAXHandler()
parser = ParserCreate()
parser.StartElementHandler = handler.start_element
parser.EndElementHandler = handler.end_element
parser.CharacterDataHandler = handler.char_data
parser.Parse(xml)