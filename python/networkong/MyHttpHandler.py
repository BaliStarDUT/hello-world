#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
from http.server import HTTPServer,BaseHTTPRequestHandler
import io,shutil

class MyHttpHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        r_str="Hello World!"
        enc="UTF-8"
        encoded = ''.join(r_str).encode(enc)
        f = io.BytesIO()
        f.write(encoded)
        f.seek(0)
        self.send_response(200)
        self.send_header("Content-type","text/html;charset=%s" % enc)
        self.send_header("Content-Length",str(len(encoded)))
        self.end_headers()
        shutil.copyfileobj(f,self.wfile)

httpd=HTTPServer(('',9999),MyHttpHandler)
print("Server started on 127.0.0.1,prot 9999 ....")
httpd.serve_forever()
