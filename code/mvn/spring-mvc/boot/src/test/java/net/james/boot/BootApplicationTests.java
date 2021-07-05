package net.james.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.ByteBuffer;

//@RunWith(JUnit4.class)
public class BootApplicationTests {

    static Logger logger = LoggerFactory.getLogger(BootApplicationTests.class);

//    @Test
    public static void main(String[] args) throws InterruptedException {
//        ByteBuffer buffer =  ByteBuffer.allocate(1543503872);
        Thread.sleep(30000);

        ByteBuffer buffer =  ByteBuffer.allocate(1024000000);
        logger.info(buffer.toString());
        logger.info("buffer.limit():"+buffer.limit());
        logger.info(buffer.toString());
        logger.info("buffer.getLong():"+buffer.getLong());
        logger.info(buffer.toString());
        logger.info("buffer.get():"+buffer.get());
        logger.info(buffer.toString());
        logger.info("buffer.getChar():"+buffer.getChar());
        logger.info(buffer.toString());
        logger.info("buffer.getDouble():"+buffer.getDouble());
        logger.info(buffer.toString());
        logger.info("buffer.getShort():"+buffer.getShort());
        logger.info(buffer.toString());
        logger.info("buffer.getInt():"+buffer.getInt());


        logger.info(buffer.toString());
//        buffer = null;
        Thread.sleep(10000);
        logger.info(buffer.toString());
        System.gc();
        Thread.sleep(10000);
        logger.info(buffer.toString());

        buffer = null;
        Thread.sleep(10000);


        ByteBuffer allocateDirect  =  ByteBuffer.allocateDirect(1024000000);
        logger.info(allocateDirect.toString());
        logger.info("allocateDirect.limit():"+allocateDirect.limit());
        logger.info(allocateDirect.toString());
        logger.info("allocateDirect.getLong():"+allocateDirect.getLong());
        logger.info(allocateDirect.toString());
        logger.info("allocateDirect.get():"+allocateDirect.get());
        logger.info(allocateDirect.toString());
        logger.info("allocateDirect.getChar():"+allocateDirect.getChar());
        logger.info(allocateDirect.toString());
        logger.info("allocateDirect.getDouble():"+allocateDirect.getDouble());
        logger.info(allocateDirect.toString());
        logger.info("allocateDirect.getShort():"+allocateDirect.getShort());
        logger.info(allocateDirect.toString());
        logger.info("allocateDirect.getInt():"+allocateDirect.getInt());


        logger.info(allocateDirect.toString());
//        allocateDirect = null;
        Thread.sleep(10000);
        logger.info(allocateDirect.toString());
        System.gc();
        Thread.sleep(10000);
        logger.info(allocateDirect.toString());
        allocateDirect = null;
        Thread.sleep(10000);

    }

}
