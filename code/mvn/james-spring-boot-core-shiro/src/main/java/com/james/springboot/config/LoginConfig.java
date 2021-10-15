package com.james.springboot.config;

import com.james.springboot.config.auth.UserAuthorizingRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author: yang
 * datetime: 2021/9/7 15:04
 */
@Configuration
public class LoginConfig {

    private static Logger logger = LoggerFactory.getLogger(LoginConfig.class);

    @Bean
    public SimpleAccountRealm simpleAccountRealm(){
        SimpleAccountRealm realm = new SimpleAccountRealm();
        realm.addAccount("yang","yang");
        return realm;
    }
    @Bean
    public SecurityManager securityManager(UserAuthorizingRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录页面，无权限时跳转的路径
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 配置拦截规则
        Map<String, String> filterMap = new LinkedHashMap();
        // 首页配置放行
        filterMap.put("/", "anon");
        // 登录页面和登录请求路径需要放行
        filterMap.put("/login", "anon");
        filterMap.put("/do_login", "anon");
        filterMap.put("/logout", "anon");
        // 其他未配置的所有路径都需要通过验证，否则跳转到登录页
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

}
