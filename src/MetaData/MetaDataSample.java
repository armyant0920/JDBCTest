package MetaData;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class MetaDataSample {

    public static void main(String[] args) {
//        getDatabase();
        getResultSetDataMeta();
    }

    private static void getDatabase() {

        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");) {
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("資料庫名稱:" + metaData.getDatabaseProductName());
            System.out.println("版本:" + metaData.getDatabaseMajorVersion());
            System.out.println("DB-Major:" + metaData.getDatabaseMajorVersion());
            System.out.println("DB-minor:" + metaData.getDatabaseMinorVersion());
            System.out.println("JDBC-Major:" + metaData.getJDBCMajorVersion());
            System.out.println("JDBC-minor:" + metaData.getJDBCMinorVersion());
            System.out.println("Driver:" + metaData.getDriverName());
            System.out.println("Driver:" + metaData.getDriverVersion());
            System.out.println("Driver:" + metaData.getDriverMajorVersion());
            System.out.println("Driver:" + metaData.getDriverMinorVersion());

            System.out.println("===============print tables==================");
            ResultSet rs = metaData.getTables("JDBCDB", null, null, new String[]{"TABLE"});

            while (rs.next()) {
                String table = rs.getString("TABLE_NAME");
                System.out.println(table);
                ResultSet columns = metaData.getColumns("JDBCDB", null, table, null);
                while (columns.next()) {

                    String column_name = columns.getString("COLUMN_NAME");
                    String type = columns.getString("type_name");
                    System.out.println(column_name + "(" + type + ")");

                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void getResultSetDataMeta() {
        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("select*from emp1");


        ) {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("column name:%s,type:%s,size:%d,nullabele:", rsmd.getColumnName(i), rsmd.getColumnType(i), rsmd.getColumnDisplaySize(i) );
                int nullable=rsmd.isNullable(i);
                if(nullable==ResultSetMetaData.columnNoNulls){
                    System.out.println("不允許空值");
                }else if(nullable==ResultSetMetaData.columnNullable){
                    System.out.println("允許空值");
                }else if(nullable==ResultSetMetaData.columnNullableUnknown){
                    System.out.println("未知");
                }else{
                    System.out.println("??????");
                }



            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("message:"+e.getMessage());
            System.err.println("error code:"+e.getErrorCode());
            System.err.println("sql state:"+e.getSQLState());
        }


    }
}
