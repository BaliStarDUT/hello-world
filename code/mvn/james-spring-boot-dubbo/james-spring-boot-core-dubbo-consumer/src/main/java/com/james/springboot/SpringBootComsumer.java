package com.james.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by James Yang on 2017/4/7 0007 上午 11:15.
 */
@SpringBootApplication
//@EnableScheduling
@Configuration
@ImportResource(locations={"classpath:dubbo/dubbo-demo-consumer.xml"})
public class SpringBootComsumer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootComsumer.class, args);
    }
}
