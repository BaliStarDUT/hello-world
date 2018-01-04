import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import java.util.Date;

/**
* java -cp .:/Users/air/.m2/repository/org/slf4j/slf4j-api/1.7.24/slf4j-api-1.7.24.jar:/Users/air/.m2/repository/org/slf4j/jcl-over-slf4j/1.7.24/jcl-over-slf4j-1.7.24.jar:/Users/air/.m2/repository/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.jar CommonsLoggingTest
*/
public class CommonsLoggingTest{

  public static void main(String[] args) {
    Log logger = LogFactory.getLog(CommonsLoggingTest.class);
    logger.info("hello {info}"+new Date());
    logger.debug("hello {debug}"+new Date());
    logger.error("hello {error}"+new Date());
    logger.trace("hello {trace}"+new Date());
    logger.warn("hello {warn}"+new Date());
  }
}
