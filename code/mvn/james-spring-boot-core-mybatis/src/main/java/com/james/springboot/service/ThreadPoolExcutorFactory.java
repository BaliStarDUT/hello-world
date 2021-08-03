package com.james.springboot.service;

import com.alibaba.druid.util.DaemonThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author: yang
 * datetime: 2020/11/20 15:43
 */

public class ThreadPoolExcutorFactory {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolExcutorFactory.class);

    public static DaemonThreadFactory daemonThreadFactory = new DaemonThreadFactory("outside-req-pool");

    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,
            10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),daemonThreadFactory);

}
