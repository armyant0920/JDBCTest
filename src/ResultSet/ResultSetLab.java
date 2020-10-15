package ResultSet;

import JDBC.Meta;

import java.sql.*;
import java.util.TreeSet;

public class ResultSetLab {


    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Project", "sa", "manager");
             Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);


        ) {

            ResultSet rs = st.executeQuery("select*from emp");
            rs.absolute(2);
            TreeSet<String> columns = Meta.getTColumns(rs);
            for (String s : columns) {
                System.out.print(rs.getString(s)+"\t");
            }
/*            while (rs.next()) {
                for(String s:columns){
                    System.out.print(String.format("%10s",rs.getString(s))); }
                System.out.println();


            }*/


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

