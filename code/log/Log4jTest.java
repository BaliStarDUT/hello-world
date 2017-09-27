import java.util.Date;
import org.apache.log4j.Logger;

class Log4jTest{
	public static void main(String[] args) {
  	Logger logger = Logger.getLogger(Log4jTest.class);
		logger.info("hello {}");
    logger.debug("hello {}");
    logger.error("hello {}");
    logger.trace("hello {}");
    logger.warn("hello {}");
  }
}
