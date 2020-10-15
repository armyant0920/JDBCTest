package Lab;

import java.sql.*;

public class PrepareStatementTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try (Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB","scott", "tiger");
				PreparedStatement pst=conn.prepareStatement("select*from emp where empno =?");) 
		{
			
			pst.setInt(1,1001);
			//pst.setInt(2,10);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getInt("empno")+","+rs.getString("ename"));
			}
			
			pst.clearParameters();
			pst.setInt(1,1003);
		
			rs=pst.executeQuery();
			
			
			if(rs.next()) {
				System.out.println(rs.getInt("empno")+","+rs.getString("ename"));
				
				
			}
		}

		catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	} 
}
