package com.james.springboot.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.james.springboot.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(group = "demo",timeout = 5000,retries = 2,loadbalance = "leastactive",actives = 3)
public class DemoServiceImpl implements DemoService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String sayHello(String name) {
        logger.info("Provider invoke:{}",name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello "+name+"."+System.currentTimeMillis();
    }
}
