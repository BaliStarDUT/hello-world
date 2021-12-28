package net.james.test;

import net.james.mysql.driver.MysqlConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * author: yang
 * datetime: 2021/11/1 17:57
 */

public class BaseConnector {

    private static Logger logger = LoggerFactory.getLogger(BaseConnector.class);

    private static final String MYSQL_HOST="localhost";
    private static final int MYSQL_PORT=3306;
    private static final String MYSQL_USER_NAME="root";
    private static final String MYSQL_PASSWORD="";

    public static MysqlConnector connector = new MysqlConnector(new InetSocketAddress(MYSQL_HOST,
            MYSQL_PORT),MYSQL_USER_NAME,MYSQL_PASSWORD);
}
