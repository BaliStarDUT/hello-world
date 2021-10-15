package com.james.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.casbin.jcasbin.main.Enforcer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.net.URI;
import java.util.List;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequiresPermissions(value = {"user:list"},logical = Logical.OR)
    @RequestMapping("/")
    String a() {
        return "a";
    }


    @RequestMapping("/hello")
    String home() {
//        Enforcer enforcer = new Enforcer("config/basic_model.conf", "config/basic_policy.csv");

        return "Hello World!"+"你好世界";
    }

    @RequestMapping("/login")
    String hello(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            System.out.println("认证成功");
            request.getSession().setAttribute("username", username);
        } catch (AuthenticationException e) {
            System.out.println("认证失败");
            logger.error("认证失败:{}={}",e.getCause(),e.getMessage());
            return "failed";
        }
        return "ok";
    }
    @RequestMapping("/do_login")
    String login_do() {
        return "login_1";
    }

    @RequestMapping("/do")
    String hello_do() {
        return "login_2";
    }

    @Autowired
    private Enforcer enforcer;

    @RequestMapping("/ok")
    String hello_ok() {
        String sub = "alice"; // the user that wants to access a resource.
        String obj = "data1"; // the resource that is going to be accessed.
        String act = "readd"; // the operation that the user performs on the resource.
        List<List<String>> permissionsForUser = enforcer.getPermissionsForUser(sub);
        logger.info(permissionsForUser.toString());
        if (enforcer.enforce(sub, obj, act) == true) {
            // permit alice to read data1
            logger.info("alice to read data1");
        } else {
            // deny the request, show an error
            logger.info("deny");
        }
        return "login_3";
    }

    @RequestMapping("/logout")
    String logout(HttpServletRequest request, HttpServletResponse response) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        request.getSession().invalidate(); //直接让session失效
        logger.info("logout");
        return "logout";
    }
}
