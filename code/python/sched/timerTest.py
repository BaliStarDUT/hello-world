#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import time
import datetime
from threading import Timer

total = 0

def worker(msg,startTime):
    print('Start time:'+ datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    print('Message:'+msg)
    print('New timer time:%s' % startTime)

def timer():
    now_str =datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    print('Scheduler start time:'+now_str)
    Timer(5,worker,('hello',time.time())).start()
    Timer(3,worker,('world',time.time())).start()

def timer2():
    global total
    now_str =datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    print('Scheduler start time:'+now_str)
    while total <10:
        Timer(3,worker,('world%d'% total,time.time())).start()
        total +=1
    endtime_str =datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    print('Scheduler end time:'+endtime_str)

if __name__ == '__main__':
    timer2()
