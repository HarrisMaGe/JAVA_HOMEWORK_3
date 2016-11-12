package Database;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MYC on 2016/11/8.
 */
public class ReadSongName {
    static String sql = null;
    static String sql2 = null;
    static OpenDB openDB = null;
    static ResultSet rs = null;
    static String url = "jdbc:mysql://127.0.0.1:330/homework_3?useSSL=false";


    public static void main(String[] args) {
        openDB = new OpenDB(url);
        String sql = "select * from song";
        try {
            File file = new File("C:\\Users\\MYC\\IdeaProjects\\homework_3\\MusicDatabase");
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                openDB.insertToSong(filelist[i]);
            }
            rs=openDB.find(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("-----------------");
            // String name = null;
            while(rs.next()) {
                System.out.println(rs.getString("id")+"\t"+rs.getString("name"));
            }
            rs.close();
            openDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
