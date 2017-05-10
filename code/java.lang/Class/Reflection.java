package com.james.springboot;
import java.lang.reflect.Method;

public class Reflection {
    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SpringBootAdminApplication.class, args);
        Class classa = Class.forName("com.james.springboot.ClassA");
        ClassA classA = (ClassA) classa.newInstance();
        System.out.println(classA.add(2,4));
        Method method = classa.getMethod("add",int.class,int.class);
        Method method1 = classa.getMethod("add",int.class,int.class,int.class);

        System.out.println(method.invoke(classA,new Integer(3).intValue(),new Integer(3).intValue()));
        System.out.println(method1.invoke(classA,new Integer(3).intValue(),new Integer(3).intValue(),4));

        System.out.println();
    }
}
class ClassA{
    public  int add(int a,int b){
        return a+b;
    }
    public  int add(int a,int b,int c){
        return a+b+c;
    }
}
