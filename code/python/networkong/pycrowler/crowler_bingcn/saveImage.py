#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import urllib.request
import urllib.parse
import parseHtml
import request
import logging
import sys
import time
import datetime
import sched

logging.basicConfig(level=logging.DEBUG,
                format='%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s %(message)s',
                datefmt='%Y-%m-%d %H:%M:%S',
                filename='bingcn.log',
                filemode='a'
                )
#间隔多长时间
DYNAMIC_CHECK_INTERVAL = 24*60*60
#循环多少次
DYNAMIC_CHECK_COUNT = 3

def getImgUrlAndName():
    host="http://cn.bing.com"
    logging.info("From:"+host)
    html = request.getHtml(host)
    #imageurl = parseHtml.getJpg(html)
    #imageurl =  parseHtml.bing_fresh_Parser(html)
    imageurl =  parseHtml.getJpg(html)
    logging.info("Image url:"+imageurl)
    fileName = imageurl.split('/')[-1:][0]
    logging.info("Image file name:"+fileName)
    return imageurl,fileName

#传入图片地址，文件名，保存单张图片
def saveImg():
    imageURL,fileName = getImgUrlAndName()
    url = (imageURL)
    logging.info('Image file url:'+url)
    #url=urllib.parse.urlencode(url)
    u = urllib.request.urlopen(url)
    data = u.read()
    f = open(fileName, 'wb')
    f.write(data)
    logging.info("Save file :"+imageURL)
    f.close()

def schedule_run():
    count = 0
    sched_time = datetime.datetime.now() + datetime.timedelta(minutes=60)  # schedule to next hour.
    logging.info('Next collect will start at %s' % sched_time.strftime('%Y-%m-%d %H:00:00'))
    s = sched.scheduler(time.time,time.sleep)
    now_str =datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    logging.info('Scheduler start time:'+now_str)
    while count < DYNAMIC_CHECK_COUNT:
        now_str = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        sched_str = sched_time.strftime('%Y-%m-%d %H:00:00')  # schedule to next sharp hour.
        logging.info('now time:%s; next time:%s'% (now_str,sched_str))
        s.enter(DYNAMIC_CHECK_INTERVAL,1,1,saveImg())
        s.run()
        logging.info('Get images round %d end.' % (count + 1))
        count +=1
if __name__ == '__main__':
    schedule_run()
    # imageurl,fileName =
    # saveImg(imageurl,fileName)
