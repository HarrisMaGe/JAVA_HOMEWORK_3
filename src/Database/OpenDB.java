package Database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MYC on 2016/11/1.
 */
public class OpenDB {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:330/students?useSSL=false";
    String user = "root";
    String password = "19950228MAGE";
    Connection conn = null;
    Statement statement = null;

    public OpenDB() {
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 连接数据库
            Connection conn = DriverManager.getConnection(url, user, password);
            this.conn = conn;
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            statement = conn.createStatement();
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insert(int id){
        try {
            Date current_date = new Date();
            String time = String.valueOf(current_date);
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            SimpleDateFormat.format(current_date.getTime());
            String insql = "insert into data_time(id,time)values(?,?)";
           // PreparedStatement pStmt = new PreparedStatement() ;
            PreparedStatement pStmt = conn.prepareStatement(insql);
            pStmt.setString(1,Integer.toString(id));
            pStmt.setString(2,time);
            pStmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet find(String findsql){
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(findsql);
            return rs;
       // PreparedStatement pStmt = conn.prepareStatement(findsql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void close(){
        try {
            if (statement != null)
                statement.close();
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



