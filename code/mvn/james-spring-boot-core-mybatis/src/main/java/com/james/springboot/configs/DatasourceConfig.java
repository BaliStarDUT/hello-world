//package com.james.springboot.configs;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * author: yang
// * datetime: 2020/10/22 14:14
// */
//
//@Configuration
//public class DatasourceConfig {
//
//    @Bean(destroyMethod =  "close", name = "DataSources.MASTER_DB")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();
//    }
//
//    @Bean(destroyMethod =  "close", name = "DataSources.LOG_DB")
//    @ConfigurationProperties(prefix = "spring.datasourceLog")
//    public DataSource dataSourceLog() {
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();
//    }
//}