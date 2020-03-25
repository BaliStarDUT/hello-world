/**
 * author: yang
 * datetime: 2020/3/24 10:41
 */
package com.james.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.support.http.StatViewServlet;
import com.mysql.jdbc.JDBC4Connection;
import org.eclipse.jetty.http.HttpGenerator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static com.alibaba.druid.pool.DruidAbstractDataSource.DEFAULT_TIME_BETWEEN_CONNECT_ERROR_MILLIS;

public class MysqlDruidStatus {
    private  static  Logger logger = LoggerFactory.getLogger(MysqlDruidStatus.class.getName());

    public static void main(String[] args) throws Exception {

        QueuedThreadPool pool = new QueuedThreadPool(100);
        InetSocketAddress addr = new InetSocketAddress("localhost",8888);
        Server server = new Server(addr);
        server.setSendServerVersion(true);
        server.setSendDateHeader(true);
        server.setStopAtShutdown(true);
        server.setGracefulShutdown(5000);

        SelectChannelConnector http = new SelectChannelConnector();
        http.setMaxIdleTime(3000);//设置连接超时时间
        http.setPort(9999);
        http.setThreadPool(pool);

        server.addConnector(http);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(StatViewServlet.class,"/druid/*");

        server.setHandler(servletHandler);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    MysqlDruidStatus.run_db();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();
        HttpGenerator.setServerVersion("yang");//由于该值是启动前被写死的，只能启动后修改，可以有效果
        server.join();
    }

    private static  void run_db() throws SQLException {
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
        dataSource.addFilters("stat");
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
