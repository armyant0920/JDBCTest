import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.TreeSet;

public class Meta {


    public static HashSet<String> getColumns(ResultSet rs) throws SQLException {
        HashSet<String> columns = new HashSet<String>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        for (int x = 1; x <= count; x++) {
            columns.add(rsmd.getColumnName(x));
        }
        return columns;
    }

    public static TreeSet<String> getTColumns(ResultSet rs) throws SQLException {
        TreeSet<String> columns = new TreeSet<String>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        for (int x = 1; x <= count; x++) {
            columns.add(rsmd.getColumnName(x));
        }
        return columns;
    }
}
