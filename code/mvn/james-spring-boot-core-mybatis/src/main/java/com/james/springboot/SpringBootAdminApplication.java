package com.james.springboot;

import com.james.springboot.configs.MyBatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by James Yang on 2017/4/7 0007 上午 11:15.
 */
@SpringBootApplication
//@EnableScheduling
//@EnableAutoConfiguration
//@EnableConfigurationProperties(MyBatisConfig.class)
//@ImportResource(locations = "classpath:mybatis/mybatis-config.xml")
@ComponentScan(basePackages = "com.james.springboot")
public class SpringBootAdminApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }
}
