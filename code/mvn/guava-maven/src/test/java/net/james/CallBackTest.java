package net.james;

import java.util.logging.Logger;

/**
 * author: yang
 * datetime: 2020/5/28 16:45
 */

public class CallBackTest {
    private  static Logger logger = Logger.getLogger(CallBackTest.class.getName());

    static {

    }
    public int number = 0;

    final String name = "mysql_big";
    final String namespace = "mysql";
    final String cluster = "HA_CORE";
    final String object = "mysql_cluster";

    public static  void main(String[] args){
        logger.info("开始进行测试");

        CallBackTest test = new CallBackTest();
        test.testTime(new CallBack() {
            @Override
            public void execute(Boolean updated) {
                logger.info("updated:"+updated);
            }
        });
    }

    public void testTime(CallBack callBack){
        long  begin = System.currentTimeMillis(); //测试起始时间
        Boolean updated = update_info(name,namespace,cluster,object); //测试方法
        long  end = System.currentTimeMillis(); //测试结束时间
        System.out.println("[use time]:"  + (end - begin)); //打印使用时间
        callBack.execute(updated);
    }

    public  void testTime(){
        long  begin = System.currentTimeMillis(); //测试起始时间
        update_info(name,namespace,cluster,object); //测试方法
        long  end = System.currentTimeMillis(); //测试结束时间
        logger.info("进行测试");
        System.out.println("[use time]:"  + (end - begin)); //打印使用时间
    }

    public Boolean update_info(String name, String namespace, String cluster, String Object){
        logger.info(name+":"+namespace+":"+cluster+":"+Object+"=="+number);
        number++;
        try {
            Thread thread = Thread.currentThread();
            logger.info(thread.getName()+thread.getThreadGroup()+thread.getId()+thread.getState().toString());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(number < 10){
            return false;
        }
        return true;
    }

}
