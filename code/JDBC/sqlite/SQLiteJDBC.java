import java.sql.*;

public class SQLiteJDBC{
    public static void main( String args[] ){
        Connection conn = null;
        try {
          Class.forName("org.sqlite.JDBC");
          conn = DriverManager.getConnection("jdbc:sqlite:ghost.db");
          System.out.println("Opened database successfully");

          String sql = "select * from roles";
          Statement st = conn.createStatement();
          ResultSet rs = st.executeQuery(sql);
          System.out.println("The result is:");
          while(rs.next()){
              System.out.println(rs.getString(1));
              System.out.println(rs.getString(2));
          }
          System.out.println("Now close the connection!");
          rs.close();
          st.close();
          conn.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }



  }
}
