import java.sql.*;

public class test {

    public static void main(String[] args) throws SQLException {

//方法1
  /*      try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
//方法2
/*        try {
            Driver driver=new com.microsoft.sqlserver.jdbc.SQLServerDriver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DemoLab","sa","manager");
//        Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost;","sa","manager");
        if(conn!=null) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select*from emp");

            while (rs.next()) {
                System.out.printf("empNo=%d,name=%s\n", rs.getInt("empno"), rs.getString("ename"));

            }
        }

    }
}
