package Lab;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class Lab {


    public static void main(String[] args) {
        System.out.println("input commission $(int),location(String)");
        Scanner sc=new Scanner(System.in);

        String input[]=sc.next().split(",");
        int add= Integer.parseInt(input[0]);
        String loc=input[1];
        if(checkLocation(loc)) {//

            try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");
                 PreparedStatement ps = conn.prepareStatement("update emp set commission=? where empno=?");
                 Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("select*from emp,dept where emp.deptno=dept.deptno and dept.location='"+loc+"'");//and dept.location='台北'
            ) {
                BigDecimal money=new BigDecimal(add);
                while (rs.next()) {
                    int empno = rs.getInt("empno");
                    String location = rs.getString("location");//取得地點
                    BigDecimal commission = rs.getBigDecimal("commission");
                    if (location.equals(loc)) {//loc

                        commission = commission.add(money);
                        ps.setBigDecimal(1, commission);
                        ps.setInt(2, empno);
                        ps.executeUpdate();
                        ps.clearParameters();
                    }
                }
                System.out.println("完成更新");


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            System.out.println("地點不存在");
        }

    }

    private static boolean checkLocation(String s){
        int count=0;
        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");
//        PreparedStatement ps=conn.prepareStatement("select count(location) from dept where location=?");
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select*from dept where location='"+s+"'")
        )

        {while(rs.next()){
            count++;
        }


        }

        catch (SQLException throwables) {throwables.printStackTrace();}
        finally {
            return (count>0)?true:false;

        }



    }
}
