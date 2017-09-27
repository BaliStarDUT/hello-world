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
