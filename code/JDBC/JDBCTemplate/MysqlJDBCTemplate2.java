/**
 * author: yang
 * datetime: 2020/3/24 20:48
 */
package com.james.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlJDBCTemplate2 {

    private static Logger logger = LoggerFactory.getLogger(MysqlJDBCTemplate2.class.getName());

    public static void main(String args[]){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://upgrade.xl9.xunlei.com:3306/test?user=retl&password=retl&autoReconnect=true");
        dataSource.setUser("retl");
        dataSource.setPassword("retl");
        dataSource.setDatabaseName("test");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        while (true){
            try{
                jdbcTemplate.query("select * from busi", new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet resultSet) throws SQLException {
                        logger.info(resultSet.getString(1));
                    }
                });
                jdbcTemplate.query("select sleep(3)", new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet resultSet) throws SQLException {
                        logger.info(resultSet.getString(1));
                    }
                });
//                String[] sqls = new String[]{"select now()","select now()+0"};
//                int[] ints = jdbcTemplate.batchUpdate(sqls);

//                Connection mysql_connection = jdbcTemplate.getDataSource().getConnection();
//                if(mysql_connection instanceof DruidPooledConnection){
//                    DruidPooledConnection druidPooledConnection = (DruidPooledConnection)jdbcTemplate.getDataSource().getConnection();
//                    JDBC4Connection jdbc4Connection = (JDBC4Connection)druidPooledConnection.getConnection();
//                    String host = jdbc4Connection.getHost() ;
//                    logger.info("host:"+host);
//                    logger.info("jdbc4Connection.getHostPortPair():"+jdbc4Connection.getHostPortPair());
//                    logger.info("jdbc4Connection.getURL():"+jdbc4Connection.getURL());
//
//                    logger.info(jdbc4Connection.getIO().mysqlConnection.getInetAddress().toString());
//                    logger.info(jdbc4Connection.getIO().mysqlConnection.getRemoteSocketAddress().toString());
//                    logger.info(jdbc4Connection.getIO().mysqlConnection.getPort()+"");
//                }
                Thread.sleep(3000);
            }catch (Exception e){
//                logger.info(e.getMessage());
                e.printStackTrace();
            }

        }
    }
}
