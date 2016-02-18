#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from http.server import HTTPServer,BaseHTTPRequestHandler
import io,shutil,urllib

class HttpHandlerGet(BaseHTTPRequestHandler):
    def do_GET(self):
        name="World"
        #print(self.path)
        if '?' in self.path:#如果带有参数
            self.queryString=urllib.parse.unquote(self.path.split('?',1)[1])
            params=urllib.parse.parse_qs(self.queryString)
            print(params)
            signature=params["signature"][0] if "signature" in params else None
            print("signature:"+signature)
            timestamp=params["timestamp"][0] if "timestamp" in params else None
            print("timestamp:"+timestamp)
            nonce=params["nonce"][0] if "nonce" in params else None
            print("nonce:"+nonce)
            echostr=params["echostr"][0] if "echostr" in params else None
            print("echostr:"+echostr)			
        r_str="signature:"+signature+"timestamp:"+timestamp+"nonce:"+nonce+"echostr:"+echostr+name+"<form action='' method='POST'>Name:<input name='name' /> <br /> <input type='submit' value='submit' /></ form>"
        enc="UTF-8"
        encoded = ''.join(r_str).encode(enc)
        f = io.BytesIO()
        f.write(encoded)
        f.seek(0)
        self.send_response(200)
        self.send_header("Content-type","text/html; charset=%s" % enc)
        self.send_header("Content-Length",str(len(encoded)))
        self.end_headers()
        shutil.copyfileobj(f,self.wfile)
    def do_POST(self):
        s=str(self.rfile.readline(),'UTF-8')#先解码
        print(urllib.parse.parse_qs(urllib.parse.unquote(s)))#解释参数
        self.send_response(301)#URL跳转
        self.send_header("Location","/?"+s)
        self.end_headers()
       

httpd = HTTPServer(('',8002),HttpHandlerGet)
print("Server started on 127.0.0.1 prot 8002....")
httpd.serve_forever()