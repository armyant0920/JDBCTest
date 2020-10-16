package Blob;

import Lab.PrepareStatementTest;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.sql.DriverManager.getConnection;

public class BlobWriter {

    public static void main(String[] args) {
//        writeTo();
//        read();
//        clobWriter();
        clobRead();

    }

    private static void writeTo() {
        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");
             Statement st = conn.createStatement();
             PreparedStatement ps = conn.prepareStatement("insert into files(filename,data) values(?,?)");
        ) {

            st.executeUpdate("delete from Files");
            File file = new File("C:\\Users\\Student\\Desktop\\Picture\\image.jpg");
            FileInputStream fis = new FileInputStream(file);//"C:\\Users\\Student\\Desktop\\Picture\\image.jpg"
            ps.setString(1, "image.png");
            ps.setBinaryStream(2, fis);
            ps.executeUpdate();
            System.out.println("完成");
            fis.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void read() {
        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("select*from files")

        ) {
            while (rs.next()) {

                String fileName = rs.getString("filename");
                SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-DD-HH-MM-SS");
                Date date = new Date();

                File file = new File("C:\\Users\\Student\\Desktop\\Picture\\" + sd.format(date) + fileName);
                if (!file.getParentFile().exists()) {
                    file.mkdirs();
                }
                try (
                        InputStream is = rs.getBinaryStream("data");
                        FileOutputStream fos = new FileOutputStream(file);) {

                    byte[] bytes = new byte[1024];
                    int len = 0;
                    while ((len = is.read(bytes)) != -1) {
                        fos.write(bytes, 0, len);

                    }
//                fos.close();
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("完成");

    }

    private static void clobWriter() {

        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");
             PreparedStatement ps = conn.prepareStatement("insert into files(filename,contents) values(?,?)");
             Statement st = conn.createStatement();
        ) {
            st.executeUpdate("delete from files");
            File file = new File("C:\\Users\\Student\\Desktop\\Picture\\code.txt");
            try (FileInputStream fis = new FileInputStream(file);
                 InputStreamReader isr = new InputStreamReader(fis, "UTF-8");) {

                ps.setString(1, file.getName());
                ps.setCharacterStream(2, isr);
                ps.executeUpdate();

//                isr.close();
//                fis.close();
            }
            System.out.println("寫入文字資料完成");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void clobRead() {

        try (Connection conn = getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBCDB", "scott", "tiger");

             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("select*from files")

        ) {

            while (rs.next()) {
                String filename = rs.getString("filename");
                Reader reader = rs.getCharacterStream("contents");

                SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-DD-HH-MM-SS");
                Date date = new Date();

                File file = new File("C:\\Users\\Student\\Desktop\\Picture\\" + sd.format(date) + filename);
                if (!file.getParentFile().exists()) {
                    file.mkdirs();
                }
                FileOutputStream fs = new FileOutputStream(file);
                OutputStreamWriter fos = new OutputStreamWriter(fs, "MS950");
                char[] buffer=new char[1024];
                int len=0;
                while((len=reader.read(buffer))!=-1){
                    fos.write(buffer,0,len);

                }
                fos.close();
                System.out.println("從資料庫寫出");


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
