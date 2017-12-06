package hibernate;

/**
* javac -cp .:/Users/aliyun/.m2/repository/org/hibernate/hibernate-core/5.0.9.Final/hibernate-core-5.0.9.Final.jar HibernateTest.java
*
java -cp .:hibernate/:/Users/aliyun/.m2/repository/org/hibernate/hibernate-core/5.0.9.Final/hibernate-core-5.0.9.Final.jar:/Users/aliyun/.m2/repository/org/jboss/logging/jboss-logging/3.3.1.Final/jboss-logging-3.3.1.Final.jar:/Users/aliyun/.m2/repository/javax/transaction/javax.transaction-api/1.2/javax.transaction-api-1.2.jar:/Users/aliyun/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar:/Users/aliyun/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.1.Final/hibernate-commons-annotations-5.0.1.Final.jar:/Users/aliyun/.m2/repository/org/hibernate/javax/persistence/hibernate-jpa-2.1-api/1.0.0.Final/hibernate-jpa-2.1-api-1.0.0.Final.jar:/Users/aliyun/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar:/Users/aliyun/.m2/repository/javassist/javassist/3.12.1.GA/javassist-3.12.1.GA.jar  hibernate/HibernateTest
*/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
// import HerosDao;

public class HibernateTest{
  private static SessionFactory sessionFactory = new Configuration()
    .configure()
    .addClass(User.class)
    .buildSessionFactory();

  public static void main(String[] args) {
    System.out.println(sessionFactory.toString());
  }
}
