package hibernate;

/**
* javac -cp .:/Users/air/.m2/repository/org/hibernate/hibernate-core/5.0.9.Final/hibernate-core-5.0.9.Final.jar HibernateTest.java
*
java -cp .:hibernate/:/Users/air/.m2/repository/org/hibernate/hibernate-core/5.0.9.Final/hibernate-core-5.0.9.Final.jar:/Users/air/.m2/repository/org/jboss/logging/jboss-logging/3.3.1.Final/jboss-logging-3.3.1.Final.jar:/Users/air/.m2/repository/javax/transaction/javax.transaction-api/1.2/javax.transaction-api-1.2.jar:/Users/air/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar:/Users/air/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.1.Final/hibernate-commons-annotations-5.0.1.Final.jar:/Users/air/.m2/repository/org/hibernate/javax/persistence/hibernate-jpa-2.1-api/1.0.0.Final/hibernate-jpa-2.1-api-1.0.0.Final.jar:/Users/air/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar:/Users/air/.m2/repository/javassist/javassist/3.12.1.GA/javassist-3.12.1.GA.jar  hibernate/HibernateTest
*/

// java -cp .:hibernate/:/Users/air/.m2/repository/org/hibernate/hibernate-core/5.0.9.Final/hibernate-re-5.0.9.Final.jar:/Users/air/.m2/repository/org/jboss/logging/jboss-logging/3.3.0.Final/jboss-logging-3.3.0.Final.jar:/Users/air/.m2/repository/javax/transaction/javax.transaction-api/1.2/javax.transaction-api-1.2.jar:/Users/air/.m2/repository/dom4j/dom4j/1.6.1/dom4j-1.6.1.jar:/Users/air/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.0.1.Final/hibernate-commons-annotations-5.0.1.Final.jar:/Users/air/.m2/repository/org/hibernate/javax/persistence/hibernate-jpa-2.1-api/1.0.0.Final/hibernate-jpa-2.1-api-1.0.0.Final.jar:/Users/air/.m2/repository/mysql/mysql-connector-java/5.1.39/mysql-connector-java-5.1.39.jar:/Users/air/.m2/repository/org/javassist/javassist/3.21.0-GA/javassist-3.21.0-GA.jar:/Users/air/.m2/repository/com/h2database/h2/1.4.192/h2-1.4.192.jar:/Users/air/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar  hibernate/HibernateTest

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;
import java.util.List;
// import hibernate.ShadowSocksLog;
// import HerosDao;

public class HibernateTest{
  private static SessionFactory sessionFactory = new Configuration()
    .configure()
    .addClass(Lolhero.class)
    .buildSessionFactory();

  public static void main(String[] args) {
    System.out.println(sessionFactory.toString());
    getLolHeros();
  }
  public static void getLolHeros(){
     Session session = sessionFactory.openSession();
    Transaction tx = null;
    try {
        tx = session.beginTransaction();
        // List result = session.createQuery("from heros").list();
        // List result = session.createSQLQuery("select * from heros").list();
        List result = session.createCriteria(Lolhero.class).list();

        System.out.println(result.get(0));
        for(Lolhero hero : (List<Lolhero>)result){
          System.out.println(hero.toString());
        }
        session.flush();
        tx.commit();
        session.close();
    }catch (Exception e) {
        if (tx!=null) tx.rollback();
        throw e;
    }
  }
}
