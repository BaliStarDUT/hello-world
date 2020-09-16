package com.james.springboot.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: yang
 * datetime: 2020/7/27 15:19
 */

public class MonitorInfoTask {

    private static Logger logger = LoggerFactory.getLogger(MonitorInfoTask.class);

    private int monitor_count = 0;

    public void getAppInfo(){
        logger.info(Thread.currentThread().getName());
        logger.info("APP monitor count:"+monitor_count);
        monitor_count++;
    }

}
