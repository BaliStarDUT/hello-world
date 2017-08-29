#encoding:utf-8
import ConfigParser
import hashlib
import httplib
import time
from urlparse import urlparse
from Util import parseURL,calSignature
"""
消息发布者
"""
class HttpProducer(object):
    def __init__(self):
        """签名值"""
        self.signature = "Signature"
        """ProducerID"""
        self.producerid = "ProducerID"
        """消息主题"""
        self.topic = "Topic"
        """访问码"""
        self.ak = "AccessKey"
        """配置文件解析器"""
        self.cf = ConfigParser.ConfigParser()
        """MD5对象"""
        self.md5 = hashlib.md5()
    """
    发布消息主流程
    """
    def process(self):
        """读取配置文件"""
        self.cf.read("user.properties")
        """读取消息主题"""
        topic = self.cf.get("property","Topic")
        """存储消息URL路径"""
        url = self.cf.get("property","URL")
        """访问码"""
        ak = self.cf.get("property","Ak")
        """密钥"""
        sk = self.cf.get("property","Sk")
        """Producer ID"""
        pid = self.cf.get("property","ProducerID")
        """HTTP请求主体内容"""
        content = U"中文".encode('utf-8')
        """分隔符"""
        newline = "\n"
        """获取URL域名地址"""
        urlname = urlparse(url).hostname
        """根据HTPP主体内容计算MD5值"""
        self.md5.update(content)
        """建立HTTP连接对象"""
        conn = httplib.HTTPConnection(parseURL(urlname))
        try:
            for index in range(0,100):
                """时间戳"""
                date = repr(int(time.time() * 1000))[0:13]
                """构造签名字符串"""
                signString = str(topic + newline + pid + newline + self.md5.hexdigest() + newline + date)
                """计算签名"""
                sign = calSignature(signString,sk)
                """内容类型"""
                contentFlag ="Content-type"
                """HTTP请求头部对象"""
                headers = {
                    self.signature : sign,
                    self.ak : ak,
                    self.producerid : pid,
                    contentFlag : "text/html;charset=UTF-8"
            }
                """开始发送HTTP请求消息"""
                conn.request(method="POST",url=url + "/message/?topic="+topic+"&time="+date+"&tag=http&key=http",
                             body=content,
                             headers=headers)
                """获取HTTP应答消息"""
                response = conn.getresponse()
                """读取HTTP应答内容"""
                msg = response.read()
                print "response:"+msg
        except Exception,e:
            print e
        finally:
            conn.close()
"""流程入口"""
if __name__ == '__main__':
    """创建消息发布者"""
    producer = HttpProducer()
    """开启消息发布者"""
    producer.process()
