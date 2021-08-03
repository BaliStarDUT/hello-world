package com.james.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by James Yang on 2017/4/7 0007 上午 11:15.
 */
@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringBootAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }
}
