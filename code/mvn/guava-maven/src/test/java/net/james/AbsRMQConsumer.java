package net.james;

import java.util.logging.Logger;

/**
 * author: yang
 * datetime: 2020/5/28 17:35
 */

public class AbsRMQConsumer {

    static Logger logger = Logger.getLogger(RMQConsumer.class.getName());
    AbsRMQConsumer(){
        logger.info(Thread.currentThread().toString());
    }

}
