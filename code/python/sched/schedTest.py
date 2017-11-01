#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import time
import datetime
import sched

total = 0

def worker(msg,startTime):
    print('Start time:'+ datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'))
    print('Message:'+msg)
    print('New worker time:%s' % startTime)
def scheduler():
    s = sched.scheduler(time.time,time.sleep)
    now_str =datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    print('Scheduler start time:'+now_str)
    s.enter(1,1,worker,('Hello',time.time()))
    s.enter(3,1,worker,('World',time.time()))
    s.run()
    endtime_str =datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    print('Scheduler end time:'+endtime_str)

def scheduler2():
    global total
    s = sched.scheduler(time.time,time.sleep)
    now_str =datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    print('Scheduler start time:'+now_str)
    while total <10:
        s.enter(3,1,worker,('Hello',time.time()))
        s.run()
        total +=1
    # s.enter(3,1,worker,('World',time.time()))
    # s.run()
    endtime_str =datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    print('Scheduler end time:'+endtime_str)

if __name__ == '__main__':
    # worker('hello','world')
    scheduler2()
