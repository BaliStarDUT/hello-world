package top.hunaner.log;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4j2Test {
  private static final Logger logger = LogManager.getLogger(Log4j2Test.class);
  public static void main(final String... args){
    logger.trace("Entering application.");
  	logger.info("hello {}");
    logger.debug("hello {}");
    logger.error("hello {}");
    logger.trace("hello {}");
    logger.warn("hello {}");
  }
}
