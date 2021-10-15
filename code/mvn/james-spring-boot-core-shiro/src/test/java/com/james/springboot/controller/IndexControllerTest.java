package com.james.springboot.controller;

import org.casbin.jcasbin.main.Enforcer;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(JUnit4ClassRunner.class)
public class IndexControllerTest {

    Logger logger = LoggerFactory.getLogger(IndexControllerTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void home() {
        String modelPath = this.getClass().getClassLoader().getResource("./config/basic_model.conf").getPath();
        String policyFile = this.getClass().getClassLoader().getResource("./config/basic_policy.csv").getPath();
///C:/Users/yang/Documents/GitHub/hello-world/code/mvn/james-spring-boot-core-shiro/target/classes/config/basic_model.conf
        Enforcer enforcer = new Enforcer(modelPath, policyFile);
        String sub = "alice"; // the user that wants to access a resource.
        String obj = "data1"; // the resource that is going to be accessed.
        String act = "readd"; // the operation that the user performs on the resource.

        if (enforcer.enforce(sub, obj, act) == true) {
            // permit alice to read data1
            logger.info("alice to read data1");
        } else {
            // deny the request, show an error
            logger.info("deny");
        }

    }

    @Test
    public void hello() {
        Enforcer enforcer = new Enforcer( );
//        enforcer.
        String sub = "alice"; // the user that wants to access a resource.
        String obj = "data1"; // the resource that is going to be accessed.
        String act = "readd"; // the operation that the user performs on the resource.

        if (enforcer.enforce(sub, obj, act) == true) {
            // permit alice to read data1
            logger.info("alice to read data1");
        } else {
            // deny the request, show an error
            logger.info("deny");
        }
    }
}