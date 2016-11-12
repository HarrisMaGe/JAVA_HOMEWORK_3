package Database;

import java.sql.*;

/**
 * Created by MYC on 2016/11/1.
 */
public class OpenDB {
    String driver = "com.mysql.jdbc.Driver";
    //String url = "jdbc:mysql://127.0.0.1:330/students?useSSL=false";
   // String url1 = "jdbc:mysql://127.0.0.1:330/homework_3?useSSL=false";
    //String url2 = "jdbc:mysql://127.0.0.1:330/finger?useSSL=false";
    //String url3 = "jdbc:mysql://127.0.0.1:330/songfinger?useSSL=false";
    String user = "root";
    String password = "19950228MAGE";
    Connection conn = null;
    Statement statement = null;

    public OpenDB(String url) {
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 连接数据库
            Connection conn = DriverManager.getConnection(url, this.user, this.password);
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
    //插入接口(测试 插入时间)
    public void insertToTime(String time){
        try {
            String insql = "insert into date_time(time)values(?)";
            PreparedStatement pStmt = conn.prepareStatement(insql);
            pStmt.setString(1,time);
            pStmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //插入歌曲名
    public void insertToSong(String name){
        try {
            String insql = "insert into song(name)values(?)";
            PreparedStatement pStmt = conn.prepareStatement(insql);
            pStmt.setString(1,name);
            pStmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void insertToFinger(int f1,int f2,int dt){
        try {
            String insql = "insert into finger(f1,f2,dt)values(?,?,?)";
            PreparedStatement pStmt = conn.prepareStatement(insql);
            pStmt.setInt(1,f1);
            pStmt.setInt(2,f2);
            pStmt.setInt(3,dt);
            pStmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //查询接口
    public ResultSet find(String findsql){
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(findsql);
            return rs;
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



