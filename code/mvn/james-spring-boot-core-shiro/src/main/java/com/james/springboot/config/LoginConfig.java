package com.james.springboot.config;

import com.james.springboot.config.auth.UserAuthorizingRealm;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author: yang
 * datetime: 2021/9/7 15:04
 */
@Configuration
public class LoginConfig {

    private static Logger logger = LoggerFactory.getLogger(LoginConfig.class);

    private static String casServer = "https://192.168.198.129:8443/cas";
    private static String appServer = "https://192.168.31.217:8084/";

    @Bean
    public FilterRegistrationBean filterRegistration(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new DelegatingFilterProxy("shiroFilterFactoryBean"));
        registrationBean.setEnabled(true);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
    @Bean
    public CasRealm casRealm(){
        CasRealm casRealm = new CasRealm();
        casRealm.setCasServerUrlPrefix(casServer);
        casRealm.setDefaultPermissions("all");
        casRealm.setDefaultRoles("all");
        return casRealm;
    }

//    @Bean
//    public SimpleAccountRealm simpleAccountRealm(){
//        SimpleAccountRealm realm = new SimpleAccountRealm();
//        realm.addAccount("yang","yang");
//        return realm;
//    }
    @Bean
    public SecurityManager securityManager(CasRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean
    public CasFilter casFilter() {
        CasFilter casFilter = new CasFilter();
        casFilter.setName("casFilter");
        casFilter.setEnabled(true);
        casFilter.setLoginUrl(casServer+"/login?service="+appServer);
        casFilter.setSuccessUrl("/success");
        casFilter.setFailureUrl("/failed222");
        return casFilter;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         CasFilter casFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录页面，无权限时跳转的路径
        shiroFilterFactoryBean.setLoginUrl(casServer+"/login?service="+appServer);
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // 配置拦截规则
        Map<String, String> filterMap = new LinkedHashMap();
        // 首页配置放行
        filterMap.put("/shiro-cas", "casFilter");
        // 登录页面和登录请求路径需要放行
        filterMap.put("/login", "anon");
        filterMap.put("/do_login", "anon");
        filterMap.put("/logout", "anon");
        // 其他未配置的所有路径都需要通过验证，否则跳转到登录页
        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("casFilter",casFilter);
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }


    //
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        autoProxyCreator.setProxyTargetClass(true);
//        return autoProxyCreator;
//    }
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
}