package net.james.utils;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * author: yang
 * datetime: 2020/7/13 20:36
 */

public class PropertiesUtil {

    private static Logger logger = Logger.getLogger(PropertiesUtil.class.getName());

    public static void main(String[] args){
        Properties properties = PropertiesUtil.loadProperties();
        logger.info(properties.get("EMAIL_SUBJECT").toString());
        try {
            PropertiesUtil.getConf();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized static  Properties loadProperties(){
        logger.info("开始加载properties文件内容.......");
        Properties props = new Properties();
        InputStream in = null;
        try {
//            第一种，通过类加载器进行获取properties文件流-->
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("mail.properties");
//            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("mail.properties");

            //                    <!--第二种，通过类进行获取properties文件流-->
//                    in = PropertiesUtil.class.getResourceAsStream("/jdbc.properties");
//            props.
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.info("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.info("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.info("jdbc.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
        return props;
    }

    public static void getConf() throws IOException {
//        / 获取当前路径

        String basepathString = PropertiesUtil.class.getResource("./").getPath();
//        PropertiesUtil.class.getClassLoader().getResource("./").getPath();
        logger.info("class.getResouce('./').getPath()"+basepathString);
        String projectPath = basepathString.substring(0,basepathString.lastIndexOf("/") - 7)
                + "resources/email.properties";

//        InputStream in = new BufferedInputStream(new FileInputStream(projectPath.replace("%20", " ")));
    // 读取配置文件中的项
//        Properties p = new Properties();
//        p.load(in);
//        System.out.println(p);
//        OutputStream fos = new FileOutputStream(projectPath);
        // 将此 Properties 表中的属性列表（键和元素对）写入输出流

    //下面的SERVER_IP为从前提form表单中获取的参数值
//        p.setProperty("SERVER_IP", "SERVER_IP");
//        p.setProperty("SERVER_PORT", "SERVER_PORT");
//        p.setProperty("EMAIL_SERVER_HOST", "EMAIL_SERVER_HOST");
//        p.setProperty("EMAIL_SERVER_PORT", "EMAIL_SERVER_PORT");
//        p.setProperty("EMAIL_USERNAME", "EMAIL_USERNAME");
//        p.setProperty("EMAIL_PASSWORD", "EMAIL_PASSWORD");
//        p.setProperty("EMAIL_SUBJECT", "EMAIL_SUBJECT");
//        p.setProperty("EMAIL_GETPWD", "EMAIL_GETPWD");
//        p.store(fos, "Update 'EMAIL_SET' value");//此处的字符串是修改前头的注释项内容

    }
}
