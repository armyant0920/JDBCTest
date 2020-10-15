package Lab;


import JDBC.Meta;

import java.sql.*;
import java.util.TreeSet;

public class UpdateTest {

    public static void main(String[] args) {

    try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");)
    {
        Statement st=conn.createStatement();
        ResultSet rs = st.executeQuery("select*from emp");

        TreeSet<String> columns = Meta.getTColumns(rs);
        for (String s : columns) {
            System.out.print(rs.getString(s)+"\t");
        }



    }

    catch (SQLException throwables) {throwables.printStackTrace();}


    }
}
