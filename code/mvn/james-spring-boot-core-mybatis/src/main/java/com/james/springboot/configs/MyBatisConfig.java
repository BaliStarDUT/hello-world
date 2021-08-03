package com.james.springboot.configs;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by James Yang on 2017/6/27 0027 上午 11:01.
 */
//@Configuration
@Component
//@MapperScan(basePackages = "com.james.springboot.dao", sqlSessionFactoryRef = "sqlSessionFactoryBean")
public class MyBatisConfig {

    private  Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("DataSources.MASTER_DB")  DataSource masterDB) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(masterDB);

        Resource resource = new PathMatchingResourcePatternResolver()
                .getResource("classpath:mybatis/mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean;
    }

    @Bean(name = "logSqlSessionFactory")
    @ConfigurationProperties(prefix = "mybatisLog")
    public SqlSessionFactoryBean logSqlSessionFactoryBean(@Qualifier("DataSources.LOG_DB")  DataSource logDB) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(logDB);
        Resource resource = new PathMatchingResourcePatternResolver()
                .getResource("classpath:mybatis/mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/logMappers/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean;
    }

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.james.springboot.dao.hero");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }

    @Bean(name = "logMapperScannerConfigurer" )
    public MapperScannerConfigurer logMapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.james.springboot.dao.busi");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("logSqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
