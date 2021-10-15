package com.james.springboot.config.casbin;

import org.casbin.annotation.CasbinDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * author: yang
 * datetime: 2021/9/13 11:32
 */

@Configuration
public class CasbinDataSourceConfiguration  {

    private static Logger logger = LoggerFactory.getLogger(CasbinDataSourceConfiguration.class);

    @Autowired
    @Qualifier("dataSource_main")
    DataSource dataSource;

//    @Bean(name = "casbinDataSource")
//    @CasbinDataSource
//    public DataSource casbinDataSource(@Autowired @Qualifier(value = "dataSource_main") DataSource dataSource) {
////
//        return dataSource;
//    }

}
