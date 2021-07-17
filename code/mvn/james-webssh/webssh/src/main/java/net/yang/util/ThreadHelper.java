package net.yang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: yang
 * datetime: 2021/3/14 10:16
 */

public class ThreadHelper {

    private static Logger logger = LoggerFactory.getLogger(ThreadHelper.class);

    public static void start(Runnable runnable){
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
