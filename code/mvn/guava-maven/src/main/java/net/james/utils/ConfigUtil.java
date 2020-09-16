package net.james.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * author: yang
 * datetime: 2020/7/13 21:12
 */

public class ConfigUtil {

    private static final String CONFIG_FILE = "application.properties";

    private static Properties properties = null;

    static {
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE));
        } catch (IOException e) {
            throw  new RuntimeException(String.format("load config file '%s' failed",CONFIG_FILE));
        }
    }

    public static String getApplicationConfig(String key){
        return properties.getProperty(key);
    }
}
