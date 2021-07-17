//package com.james.springboot.configure;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.thymeleaf.spring4.view.ThymeleafView;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;
//
//import java.util.HashMap;
//import java.util.Locale;
//
///**
// * author: yang
// * datetime: 2020/11/24 13:11
// */
//
//public class ThymeleafConfig {
//
//    private static Logger logger = LoggerFactory.getLogger(ThymeleafConfig.class);
//
//    public void thymeleafResolve() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        try {
//            ThymeleafView view = (ThymeleafView) resolver.resolveViewName("mysql", Locale.CHINA);
////            view.render(new HashMap(), );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
