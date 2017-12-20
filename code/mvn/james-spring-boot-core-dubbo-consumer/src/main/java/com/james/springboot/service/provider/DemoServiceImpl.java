package com.james.springboot.service.provider;

import com.james.springboot.service.DemoService;

public class DemoServiceImpl implements DemoService{

    @Override
    public String sayHello(String name) {
        return "Hello "+name+"."+System.currentTimeMillis();
    }
}
