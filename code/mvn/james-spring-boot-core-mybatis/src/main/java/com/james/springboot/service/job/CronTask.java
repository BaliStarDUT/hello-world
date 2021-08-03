package com.james.springboot.service.job;

import com.james.springboot.service.ThreadPoolExcutorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Component
public class CronTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(fixedDelay=50000)
    public void  scheduledTask() {
        int active = ThreadPoolExcutorFactory.threadPoolExecutor.getActiveCount();
        logger.info("active thread:{}",active);
        long complete = ThreadPoolExcutorFactory.threadPoolExecutor.getCompletedTaskCount();
        logger.info("complete task:{}",complete);
        logger.info(Thread.currentThread().getName());
//        logger.debug("Logger Level :DEBUG "+"Hello World!");
//        logger.info("Logger Level :INFO "+"163");
        try {
            if(Desktop.isDesktopSupported()){
                logger.warn("Logger Level :WARN "+"Open browser");
                Desktop.getDesktop().browse(new URI("http://music.163.com/outchain/player?type=0&id=71889585&auto=0&height=430"));
            }
        } catch (Exception e) {
            logger.error("Logger Level:ERROR "+e.getMessage());
            e.printStackTrace();
        }
    }
}
