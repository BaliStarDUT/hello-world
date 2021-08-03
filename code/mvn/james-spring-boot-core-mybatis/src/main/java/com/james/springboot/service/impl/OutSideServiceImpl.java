package com.james.springboot.service.impl;

import com.james.springboot.dao.bean.Busi;
import com.james.springboot.dao.busi.BusiMapper;
import com.james.springboot.dao.bean.BusiExample;
import com.james.springboot.service.OutSideService;
import com.james.springboot.service.ThreadPoolExcutorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

/**
 * author: yang
 * datetime: 2020/11/20 14:30
 */
@Service
public class OutSideServiceImpl implements OutSideService {

    private static Logger logger = LoggerFactory.getLogger(OutSideServiceImpl.class);

    @Autowired
    BusiMapper busiMapper;

    @Override
    public String addtoABC() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,ThreadPoolExcutorFactory.daemonThreadFactory);
        Runnable runnable = new Runnable() {
            int count = 0;
            @Override
            public void run() {
                int active = ((ScheduledThreadPoolExecutor) executorService).getActiveCount();
                logger.info("active thread:{}",active);
                long complete = ((ScheduledThreadPoolExecutor) executorService).getCompletedTaskCount();
                logger.info("complete task:{}",complete);
                logger.info(Thread.currentThread().getName());
                count +=1;
                if(count >5){
                    logger.error("add to CMDB{}:%s{}","faild","5 times");
                    executorService.shutdown();
                    return;
                }
                int status = (int) (System.currentTimeMillis()%10);
                logger.info("status:"+status);
                if(status>3){
                    return;
                }
                logger.info("add to CMDB{}:%s","ok");
                executorService.shutdown();
                return;
            }
        };
        executorService.scheduleAtFixedRate(runnable,1,3, TimeUnit.SECONDS);

        return "false";
    }

    @Override
    public List<Busi> getBusi(){
        BusiExample busiExample = new BusiExample();
        BusiExample.Criteria criteria = busiExample.createCriteria();
        criteria.andBusiCodeIsNotNull();
        List<Busi> busis = busiMapper.selectByExample(busiExample);
        return busis;
    }
}
