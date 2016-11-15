package Database;

import Fingerprint.Fingerprint;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Created by MYC on 2016/11/8.
 */
public class Main {
    static String sql = null;
    static String sql2 = null;
    static OpenDB openDB = null;
    static ResultSet rs = null;
    static String url = "jdbc:mysql://127.0.0.1:330/homework_3?useSSL=false";
    public static void main(String[] args)throws IOException {
        openDB = new OpenDB(url);
               File file = new File("C:\\Users\\MYC\\IdeaProjects\\homework_3\\MusicSelect");
               String[] filelist = file.list();
               for (int i = 6; i < filelist.length; i++) {
                   openDB.insertToSong(filelist[i]);
                   Fingerprint fingerprint = new Fingerprint();
                   fingerprint.setFinger_Id("MusicSelect/"+filelist[i],filelist[i]);
               }
       // Fingerprint fingerprint = new Fingerprint();
        //fingerprint.setFinger_Id("MusicSelect/周杰伦 - 告白气球.wav","周杰伦 - 告白气球.wav");
        //openDB.getSongId("五月天 - 突然好想你.wav");
            openDB.close();


    }
}
