package com.james.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by James Yang on 2017/4/7 0007 上午 11:15.
 */
@EnableAutoConfiguration
@EnableScheduling
public class SpringBootAdminApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }
}
