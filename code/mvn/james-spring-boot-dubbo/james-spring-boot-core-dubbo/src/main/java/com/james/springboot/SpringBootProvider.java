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
@Configuration
@ImportResource(locations={"classpath:spring/applicationContext.xml"})
//@EnableScheduling
//C:\Users\yang\Documents\GitHub\hello-world\code\mvn\james-spring-boot-core-dubbo\target\classes\dubbo\dubbo-demo-provider.xml
public class SpringBootProvider {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootProvider.class, args);
    }
}
