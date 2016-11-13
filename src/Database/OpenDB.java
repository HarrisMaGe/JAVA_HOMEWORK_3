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
//向songfinger表里插入song_id、finger_id、offset
    public void insertToSongfinger(int id,int finger_id,int offset){
        try {
            String insql = "insert into songfinger(song_id,finger_id,offset)values(?,?,?)";
            PreparedStatement pStmt = conn.prepareStatement(insql);
            pStmt.setInt(1,id);
            pStmt.setInt(2,finger_id);
            pStmt.setInt(3,offset);
            pStmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
//获取对应id的歌曲名字
public String getSongName(int id){
    try {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from song where id="+String.valueOf(id));
        String name =null;
        while(rs.next()) {
            name = rs.getString("name");
        }
        rs.close();
        return rs.getString("name");
    }catch(Exception e){
        e.printStackTrace();
        return null;
    }
}
//获取对应歌曲名字的id
    public int getSongId(String name){
        try {
            String str = "select * from song where name="+"\""+name+"\"";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(str);
            int id = -1;
            while(rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            return id;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    //查询接口
    public ResultSet find(String findsql){
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(findsql);
            rs.close();
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



