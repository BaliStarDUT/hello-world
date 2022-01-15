package net.james.test;

import net.james.mysql.driver.MysqlQueryExecutor;
import net.james.mysql.driver.MysqlUpdateExecutor;
import net.james.mysql.driver.packets.server.ResultSetPacket;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * author: yang
 * datetime: 2021/11/1 16:06
 */

public class MysqlConnectorTest extends BaseConnector{

    private static Logger logger = Logger.getLogger(MysqlConnectorTest.class.getName());

    private static final String INSERT_SQL="insert into drp.t_tube(name,create_time) values('zxm',now())";

    @org.junit.Test
    public void test_connect(){
        String name = new String("tables a not exists");
        logger.info(name);
        byte[] bytes = name.getBytes();
        logger.info(bytes.toString());
        for (byte b : bytes) {
            System.out.println(b);
        }
        String name_parse = new String(bytes);
        logger.info("parse name:"+name_parse);
    }

    @Test
    public void test_mysql_Insert() throws IOException {
        connector.connect();
        MysqlUpdateExecutor executor = new MysqlUpdateExecutor(connector);
        executor.update(INSERT_SQL);
    }


    @Test
    public void test_mysql_Query() throws IOException {
        connector.connect();
        MysqlQueryExecutor executor = new MysqlQueryExecutor(connector);
        ResultSetPacket result = executor.query("show variables like '%char%';");
        System.out.println(result);
        result = executor.query("select * from test.test1");
        System.out.println(result);
    }

    @Test
    public void test_mysql_Query2() throws IOException {
        connector.connect();
        MysqlQueryExecutor executor = new MysqlQueryExecutor(connector);
        ResultSetPacket result = executor.query("show databases;");
        System.out.println(result);
        List<String> fieldValues = result.getFieldValues();
        for(String value : fieldValues){
            logger.info(value);
        }
        connector.disconnect();
    }


}
