package net.james;

import java.util.logging.Logger;

/**
 * author: yang
 * datetime: 2020/5/28 17:35
 */

public class RMQConsumer extends AbsRMQConsumer {

    static Logger logger = Logger.getLogger(RMQConsumer.class.getName());
    public RMQConsumer(){
        logger.info(Thread.currentThread().toString());
    }

    public static void main(String[] args){
        RMQConsumer rmqConsumer = new RMQConsumer();
    }

}
