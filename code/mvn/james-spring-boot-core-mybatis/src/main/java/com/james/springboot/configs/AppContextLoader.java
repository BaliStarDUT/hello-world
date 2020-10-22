//package com.james.springboot.configs;
//
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * author: yang
// * datetime: 2020/7/22 14:58
// */
//
//public class AppContextLoader {
//
//    private static ClassPathXmlApplicationContext context = null;
//
//    private static RuntimeException initException = null;
//
//    static {
//        try{
//            context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml"){
//                @Override
//                protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory){
//                    super.customizeBeanFactory(beanFactory);
//                    beanFactory.setAllowBeanDefinitionOverriding(false);
//                }
//            };
//            context.registerShutdownHook();
//
//        }catch (RuntimeException e){
//            throw e;
//        }
//
//    }
//
//    public static ApplicationContext getApplicationContext() {
//        if(context == null){
//            throw initException;
//        }
//        return context;
//    }
//
//    public static void close() {
//        context.close();
//    }
//
//    public static <T>T getBean(String beanName){
//        return (T)getApplicationContext().getBean(beanName);
//    }
//
//    public static void autowire(Object obj){
//        context.getAutowireCapableBeanFactory().autowireBeanProperties(obj,
//                AutowireCapableBeanFactory.AUTOWIRE_BY_NAME,false);
//    }
//}
