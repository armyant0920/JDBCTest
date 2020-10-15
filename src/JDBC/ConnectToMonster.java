package JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;

public class ConnectToMonster {


    public static void main(String[] args) {


        try (Connection conn = Connect.connect("1433", "Project", "sa", "manager");
             Statement st = conn.createStatement();
        ) {

            ResultSet rs = st.executeQuery("select*from Monster");
            ArrayList<String> columns = Meta.getAColumns(rs);

            for (int i=0;i<columns.size() ;i++) {
                System.out.print(columns.get(i) + "\t");
            }
            System.out.println();
            while (rs.next()) {
                for (int i=0;i<columns.size();i++) {
                    System.out.print(rs.getString(columns.get(i)) + "\t");
                }
                System.out.println();
//                System.out.println(rs.getString(1));

                //System.out.printf("empNo=%d,name=%s\n", rs.getInt("empno"), rs.getString("ename"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
