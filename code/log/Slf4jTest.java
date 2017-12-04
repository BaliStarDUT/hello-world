/**
* javac -cp /Users/aliyun/.m2/repository/org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar  Slf4jTest.java
* java -cp .:/Users/aliyun/.m2/repository/org/slf4j/slf4j-api/1.7.7/slf4j-api-1.7.7.jar:/Users/aliyun/.m2/repository/ch/qos/logback/logback-classic/1.1.7/logback-classic-1.1.7.jar:/Users/aliyun/.m2/repository/ch/qos/logback/logback-core/1.1.7/logback-core-1.1.7.jar  Slf4jTest
*/

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Slf4jTest{
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
		logger.info("hello {}",new Date());
    logger.debug("hello {}",new Date());
    logger.error("hello {}",new Date());
    logger.trace("hello {}",new Date());
    logger.warn("hello {}",new Date());
  }
}
