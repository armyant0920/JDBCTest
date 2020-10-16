package Lab;

import java.math.BigDecimal;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class CallableStatement {
    public static void main(String[] args) {

//    statement2();
        statament3();
    }

    private static void statement1() {

        try (
                Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");) {
            java.sql.CallableStatement cs = conn.prepareCall("{call QueryEmployee}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                System.out.println("emp=" + rs.getInt("empno") + ",name=" + rs.getString("ename"));
            }

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private static void statement2() {

        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");
             java.sql.CallableStatement cs = conn.prepareCall("{call sum_salary(?,?)}");) {

            cs.setInt(1, 10);
            cs.registerOutParameter(2, Types.NUMERIC);//註冊第2個問號為輸出,型態為Numeric,if 整數為java.sql.Types.Integer
            cs.execute();
            BigDecimal sum = cs.getBigDecimal(2);//取得第二個問號回傳值
            System.out.println(sum);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void statament3() {

        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");
             java.sql.CallableStatement st = conn.prepareCall("{?=call CheckManager(?)}")//?=表示有回傳值,且在第一個問號

        ) {
            st.registerOutParameter(1, Types.INTEGER);//註冊回傳值
            st.setInt(2, 1002);
            st.execute();
            int statue = st.getInt(1);
            if (statue == 1) {
                System.out.println("manager");

            } else {
                System.out.println("other");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
