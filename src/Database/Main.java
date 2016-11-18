package Database;

import Matching.GetFingerprint;
import Matching.Score;
import Fingerprint.*;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MYC on 2016/11/8.
 */
public class Main {
    static String sql = null;
    static String sql2 = null;
    static OpenDB openDB = null;
    static ResultSet rs = null;
    static String url = "jdbc:mysql://127.0.0.1:330/homework_3?useSSL=false";

    //建库
    public static void setDB()throws IOException{
        openDB = new OpenDB(url);
             File file = new File("C:\\Users\\MYC\\IdeaProjects\\homework_3\\MusicSelect");
             String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                openDB.insertToSong(filelist[i]);
                Fingerprint fingerprint = new Fingerprint();
              fingerprint.setFinger_Id("MusicSelect/"+filelist[i],filelist[i]);
          }

          openDB.close();
    }

    //程序入口
    public static void main(String[] args)throws SQLException,IOException {
        setDB();
        openDB = new OpenDB(url);
        GetFingerprint getFingerprint = new GetFingerprint();
        getFingerprint.Hashes("周杰伦 - 烟花易冷_10秒.wav");
        getFingerprint.getMatchingArray();
        Score score = new Score();
        int [][]scores  =score.getHighest(getFingerprint.getMatchingArray());

        for(int i =0;i<5;i++){
            System.out.printf((i+1)+"\t"+openDB.getSongName(scores[i][0])+"\t"+scores[i][1]+"\n");
        }

    }
}
