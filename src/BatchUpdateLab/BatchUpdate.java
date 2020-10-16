package BatchUpdateLab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdate {

    public static void main(String[] args) {
        batchUpdate();

    }


    private static void batchUpdate(){


        try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");

             Statement st=conn.createStatement();

        )
        {
            st.addBatch("update emp set commission =200 where deptno=10");
            st.addBatch("update emp set commission =300 where deptno=20");
            st.addBatch("update emp set commission =400 where deptno=30");
            //呼叫addbatch時還不會送到資料庫,累積起來等executeBatch()才一次送出
            st.executeBatch();
            System.out.println("批次執行送出");



        }

        catch (SQLException throwables) {throwables.printStackTrace();}

    }
}
