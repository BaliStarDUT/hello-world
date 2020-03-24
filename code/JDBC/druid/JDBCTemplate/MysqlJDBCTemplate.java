/**
 * author: yang
 * datetime: 2020/3/24 10:41
 */
package com.james.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mysql.jdbc.JDBC4Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.alibaba.druid.pool.DruidAbstractDataSource.DEFAULT_TIME_BETWEEN_CONNECT_ERROR_MILLIS;

public class MysqlJDBCTemplate {
    private  static  Logger logger = LoggerFactory.getLogger(MysqlJDBCTemplate.class.getName());
    public static void main(String[] args) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://upgrade.xl9.xunlei.com:3306/test?user=retl&password=retl&autoReconnect=true");
        dataSource.setUsername("retl");
        dataSource.setPassword("retl");
        dataSource.setDbType("mysql");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(3);
        dataSource.setMaxActive(5);
        dataSource.setRemoveAbandonedTimeoutMillis(20000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setConnectionErrorRetryAttempts(3);
        dataSource.setBreakAfterAcquireFailure(false);
        dataSource.setTimeBetweenConnectErrorMillis(DEFAULT_TIME_BETWEEN_CONNECT_ERROR_MILLIS);
//        dataSource.init();
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
            Connection mysql_connection = jdbcTemplate.getDataSource().getConnection();
            if(mysql_connection instanceof DruidPooledConnection){
                DruidPooledConnection druidPooledConnection = (DruidPooledConnection)jdbcTemplate.getDataSource().getConnection();
                JDBC4Connection jdbc4Connection = (JDBC4Connection)druidPooledConnection.getConnection();
                String host = jdbc4Connection.getHost() ;
                logger.info("host:"+host);
                logger.info("jdbc4Connection.getHostPortPair():"+jdbc4Connection.getHostPortPair());
                logger.info("jdbc4Connection.getURL():"+jdbc4Connection.getURL());

                logger.info(jdbc4Connection.getIO().mysqlConnection.getInetAddress().toString());
                logger.info(jdbc4Connection.getIO().mysqlConnection.getRemoteSocketAddress().toString());
                logger.info(jdbc4Connection.getIO().mysqlConnection.getPort()+"");
            }
                Thread.sleep(3000);
            }catch (Exception e){
//                logger.info(e.getMessage());
                e.printStackTrace();
            }

        }

    }
}
