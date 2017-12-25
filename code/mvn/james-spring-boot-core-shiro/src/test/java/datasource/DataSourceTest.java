package datasource;

import com.jfinal.plugin.activerecord.generator.Generator;
import org.junit.Test;
import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class DataSourceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Test
    public void testDataSource() throws SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-datasource.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        logger.info(dataSource.toString());

        System.out.println("Opened database successfully");
        Connection conn = dataSource.getConnection();
        String sql = "select name from sqlite_master where type='table' order by name;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        System.out.println("The result is:");
        while(rs.next()){
            System.out.println(rs.getString(1));
//            System.out.println(rs.getString(2));
        }
        System.out.println("Now close the connection!");
        rs.close();
        st.close();
        conn.close();

        Generator generator = new Generator(dataSource,"com.james.springboot.basemodel",
                "baseModel","model","model");
        generator.addExcludedTable("brute","sqlite_sequence");
        generator.generate();

    }
}
