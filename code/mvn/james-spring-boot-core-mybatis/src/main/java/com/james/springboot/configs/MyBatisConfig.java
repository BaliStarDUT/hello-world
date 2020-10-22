package com.james.springboot.configs;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by James Yang on 2017/6/27 0027 上午 11:01.
 */
@Configuration
//@MapperScan("com.james.springboot.dao")
public class MyBatisConfig {

//    private static final String DataSources = ;
    private static Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

//    @Autowired
//    private Environment env;

    @Bean(destroyMethod =  "close", name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public  DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        return dataSource;
    }

//    @Bean(destroyMethod =  "close", name = "DataSources.LOG_DB")
//    @ConfigurationProperties(prefix = "spring.datasourceLog")
//    public DataSource dataSourceLog() {
//        return DataSourceBuilder.create().type(DruidDataSource.class).build();
//    }
//
//    @Autowired
//    @Qualifier("DataSources.MASTER_DB")
//    private DataSource masterDB;
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "mybatis")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(masterDB);
//        return sqlSessionFactoryBean;
//    }


//    @Bean
//    public  DataSource dataSource(){
//        DruidDataSource  dataSource = new DruidDataSource();
//        logger.info("iiiiiiiiiiiiiiiiiiiiiiiiiiii");
//        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//        dataSource.setUrl(env.getProperty("jdbc.url"));
//        dataSource.setUsername(env.getProperty("jdbc.username"));
//        dataSource.setPassword(env.getProperty("jdbc.password"));
////        dataSource.setUrl();
////        return DataSourceBuilder.create().type(DruidDataSource.class).build();
//        return dataSource;
//    }

    @Bean(name = "sqlSessionFactoryBean")
    public  SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        logger.info("bbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        String resource = "mybatis/mybatis-config.xml";
        ClassPathResource classPathResource = new ClassPathResource(resource);
        sqlSessionFactoryBean.setConfigLocation(classPathResource);
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactoryBean;
    }

    @Bean
    public  MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.james.springboot.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}
