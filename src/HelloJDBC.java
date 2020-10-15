import java.sql.*;
import java.util.TreeSet;

public class HelloJDBC {

    class Student{



    }


    public static void main(String[] args) {


//10.31.25.70
        try (Connection conn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Project","sa","manager");
             Statement st=conn.createStatement();


        ){
            System.out.println(update(st));
            ResultSet rs=st.executeQuery("select*from emp");
            TreeSet<String> columns=Meta.getTColumns(rs);
            while (rs.next()) {
                for(String s:columns){
                    System.out.print(String.format("%10s",rs.getString(s))); }
                System.out.println();
//                System.out.println(rs.getString(1));

                //System.out.printf("empNo=%d,name=%s\n", rs.getInt("empno"), rs.getString("ename"));

            }





        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static boolean update(Statement st){
        int update= 0;
        try {
            update = st.executeUpdate("update emp set salary=20000 where empno=1001");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(update>0){return true;}else{return  false;}



    }
}
