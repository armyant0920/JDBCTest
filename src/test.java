import java.sql.*;
import java.util.HashSet;
import java.util.TreeSet;

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

        Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB","scott","tiger");
//        Connection conn=DriverManager.getConnection("jdbc:sqlserver://localhost;","sa","manager");
        if(conn!=null) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select*from emp");
            TreeSet<String> columns=getTColumns(rs);

            for(String s:columns){

                System.out.print(String.format("%10s",s));
            }
            System.out.println();
            while (rs.next()) {
                for(String s:columns){
                    System.out.print(String.format("%10s",rs.getString(s))); }
                System.out.println();
//                System.out.println(rs.getString(1));

                //System.out.printf("empNo=%d,name=%s\n", rs.getInt("empno"), rs.getString("ename"));

            }
            rs.close();
            st.close();
        }

        conn.close();

    }

    public static int getColumnNum(ResultSet rs){
        return  1;

    }
    public static HashSet<String> getColumns(ResultSet rs) throws SQLException {
        HashSet<String> columns = new HashSet<String>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        for (int x = 1; x <= count; x++) {
            columns.add(rsmd.getColumnName(x));




        }
        return columns;
    }

    public static TreeSet<String> getTColumns(ResultSet rs) throws SQLException {
        TreeSet<String> columns = new TreeSet<String>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        for (int x = 1; x <= count; x++) {
            columns.add(rsmd.getColumnName(x));
        }
        return columns;
    }
}
