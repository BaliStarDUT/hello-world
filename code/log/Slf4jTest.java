/**
* javac -cp /Users/aliyun/.m2/repository/org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar  Slf4jTest.java
* java -cp .:/Users/aliyun/.m2/repository/org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar:/Users/aliyun/.m2/repository/ch/qos/logback/logback-classic/1.1.7/logback-classic-1.1.7.jar:/Users/aliyun/.m2/repository/ch/qos/logback/logback-core/1.1.7/logback-core-1.1.7.jar  Slf4jTest
*/

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Slf4jTest{
    private static final Logger logger = LoggerFactory.getLogger(Slf4jTest.class);

	public static void main(String[] args) {
		try{
            // Double d1=new Double(1/0);
            Class classa = Class.forName("com.james.springboot.ClassA");
            // ClassA classA = (ClassA) classa.newInstance();
            // System.out.println(classA.add(2,4));
            // throw new Exception("error");
        }catch (ArithmeticException e){
			logger.error("main:",e);
			// logger.error(e.getMessage());
            // e.printStackTrace();
        }catch (Exception e){
			logger.error("main:",e);
            // e.printStackTrace();
        }
		logger.trace("hello {}",new Date());
		logger.info("hello {}",new Date());
	    logger.debug("hello {}",new Date());
	    logger.error("hello {}",new Date());
	    logger.trace("hello {}",new Date());
	    logger.warn("hello {}",new Date());
  }
}
