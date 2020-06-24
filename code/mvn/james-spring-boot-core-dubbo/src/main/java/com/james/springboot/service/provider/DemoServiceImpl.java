package com.james.springboot.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.james.springboot.service.DemoService;

@Service()
public class DemoServiceImpl implements DemoService{

    @Override
    public String sayHello(String name) {
        return "Hello "+name+"."+System.currentTimeMillis();
    }
}
