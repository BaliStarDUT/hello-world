package com.james.springboot;

import com.james.springboot.configs.MyBatisConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/** 
* MyBatisConfig Tester. 
* 
* @author <Authors name> 
* @version 1.0 
*/ 
public class MyBatisConfigTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: sqlSessionFactory() 
* 
*/ 
@Test
public void testSqlSessionFactory() throws Exception {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    applicationContext.register(MyBatisConfig.class);
    applicationContext.refresh();
    SqlSessionFactory sqlSessionFactory = applicationContext.getBean(SqlSessionFactory.class);
    logger.info(sqlSessionFactory.toString()+"");

}


} 
