package top.hunaner;

import com.jfinal.core.JFinal;
/**
* javac -cp /Users/aliyun/.m2/repository/com/jfinal/jfinal/2.2/jfinal-2.2.jar top/hunaner/JfinalApplication.java
* java -cp .:/Users/aliyun/.m2/repository/com/jfinal/jfinal-java8/3.4-SNAPSHOT/jfinal-java8-3.4-SNAPSHOT.jar:/Users/aliyun/.m2/repository/org/eclipse/jetty/jetty-server/9.2.7.v20150116/jetty-server-9.2.7.v20150116.jar:/Users/aliyun/.m2/repository/org/eclipse/jetty/jetty-util/9.2.7.v20150116/jetty-util-9.2.7.v20150116.jar:/Users/aliyun/.m2/repository/org/eclipse/jetty/jetty-http/9.2.7.v20150116/jetty-http-9.2.7.v20150116.jar:/Users/aliyun/.m2/repository/org/eclipse/jetty/jetty-servlet/9.2.7.v20150116/jetty-servlet-9.2.7.v20150116.jar:/Users/aliyun/.m2/repository/org/eclipse/jetty/jetty-webapp/9.2.7.v20150116/jetty-webapp-9.2.7.v20150116.jar:/Users/aliyun/.m2/repository/org/eclipse/jetty/jetty-io/9.2.7.v20150116/jetty-io-9.2.7.v20150116.jar:/Users/aliyun/.m2/repository/org/eclipse/jetty/jetty-security/9.2.7.v20150116/jetty-security-9.2.7.v20150116.jar:/Users/aliyun/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar:/Users/aliyun/.m2/repository/org/eclipse/jetty/jetty-xml/9.2.7.v20150116/jetty-xml-9.2.7.v20150116.jar top/hunaner/JfinalApplication
*/
public class JfinalApplication{
    public static void main(String[] args) {
        JFinal.start("src/main/webapp",8006, "/",5);
    }
}
