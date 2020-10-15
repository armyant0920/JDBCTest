package JDBC;

import java.net.URL;
import java.sql.*;
import java.util.Arrays;

public class Connect {
    private String jdbc;
    private String ip;
    private String port;
    private String databaseName;
    private String user;
    private String password;
    private URL url;
    private Statement st;
    private ResultSet rs;
    private Connection conn;

    public static Connection connect(String port, String databaseName, String user, String password) {
//        System.out.println(Arrays.stream(params).toArray());
//        System.out.println(params);
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" + databaseName, user, password);
            return conn;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
    public static boolean closeConnect(Connection conn){
        try {
            conn.close();
            return conn.isClosed();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }


}
