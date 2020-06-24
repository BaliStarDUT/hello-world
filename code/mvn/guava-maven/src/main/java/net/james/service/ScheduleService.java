package net.james.service;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * author: yang
 * datetime: 2020/5/26 15:09
 */

public class ScheduleService {
    static Logger logger = Logger.getLogger(ScheduleService.class.getName());

    public int number = 0;

    final String name = "mysql_big";
    final String namespace = "mysql";
    final String cluster = "HA_CORE";
    final String object = "mysql_cluster";

    public static  void main(String[] args){
        ScheduleService scheduleService = new ScheduleService();
        Thread thread = Thread.currentThread();
        logger.info(thread.getName()+thread.getThreadGroup()+thread.getId()+thread.getState().toString());
        scheduleService.update_info_timer_task();
    }

    public void update_info_1_task(){

        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.info("Task scheduled once.");
                try {
                    Thread thread = Thread.currentThread();
                    logger.info(thread.getName()+thread.getThreadGroup()+thread.getId()+thread.getState().toString());
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Boolean updateed  = update_info(name,namespace,cluster,object);
                if(updateed){
                    logger.info("Stop task!");
                }
                logger.info("return task!");
            }
        };
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                ThreadGroup threadGroup = new ThreadGroup("yz-group");
                return  new Thread(threadGroup,r,"yz",1);
            }
        };
        ExecutorService executorService = new ThreadPoolExecutor(1,1,1,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>());
        ((ThreadPoolExecutor) executorService).setThreadFactory(threadFactory);
        executorService.execute(task);
        logger.info("shutdown task!");
        Thread thread = Thread.currentThread();
        logger.info(thread.getName()+thread.getThreadGroup()+thread.getId()+thread.getState().toString());

        executorService.shutdown();
//        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(1);

    }

    public void update_info_timer_task(){
        final String name = "mysql_big";
        final String namespace = "mysql";
        final String cluster = "HA_CORE";
        final String object = "mysql_cluster";
        final Boolean[] added = {false};
        logger.info("Start task:"+name+"in:"+namespace+":"+cluster+":"+object);
        Thread thread = Thread.currentThread();
        logger.info(thread.getName()+thread.getThreadGroup()+thread.getId()+thread.getState().toString());

        final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(5);
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                ThreadGroup threadGroup = new ThreadGroup("yz-group");
                return  new Thread(threadGroup,r,"yz",1);
            }
        };
        ((ScheduledThreadPoolExecutor) executorService).setThreadFactory(threadFactory);
        try {
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    added[0] = update_info(name,namespace,cluster,object);
                    Thread thread = Thread.currentThread();
                    logger.info(thread.getName()+thread.getThreadGroup()+thread.getId()+thread.getState().toString());
                    if(added[0]){
                        executorService.shutdown();
                        logger.info("Stop task!");
                    }
                }
            }, 5, 10, TimeUnit.SECONDS);
            logger.info("Task scheduled.");
        }catch (Exception e){
            executorService.shutdown();
            logger.info(e.getMessage());
        }
    }

    public Boolean update_info(String name, String namespace, String cluster, String Object){
        logger.info(name+":"+namespace+":"+cluster+":"+Object+"=="+number);
        number++;
        Thread thread = Thread.currentThread();
        logger.info(thread.getName()+thread.getThreadGroup()+thread.getId()+thread.getState().toString());

        if(number < 10){
            return false;
        }
        return true;
    }

}
