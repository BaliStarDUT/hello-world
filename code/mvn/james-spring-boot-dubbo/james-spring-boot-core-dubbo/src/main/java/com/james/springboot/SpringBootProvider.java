package com.james.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private  static  final Logger logger = LoggerFactory.getLogger(SpringBootProvider.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootProvider.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Stop the Server");
            }
        });
    }
}
