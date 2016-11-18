package Matching;

import Database.OpenDB;
import Fingerprint.Fingerprint;
import ReadFile.Read;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 冬至节 on 2016/11/12.
 */
public class GetFingerprint {
    //get a music file here
    Read read=new Read();
    Fingerprint fp=new Fingerprint();
    int [][]h;
    int song_id;
    static String sql = null;
    static String sql2 = null;
    static OpenDB openDB = null;
    static ResultSet rs = null;
    static String url = "jdbc:mysql://127.0.0.1:330/homework_3?useSSL=false";

    public int[][] Hashes(String path) throws IOException{
        fp.divide(read.getDoubles(path));
        read.deleteArray();
        List finger_id = new ArrayList();
        for (int i = 0; i < fp.combineHash().size(); i++) {
            finger_id.add((fp.combineHash().get(i).dt << 18) |
                    (fp.combineHash().get(i).f1 << 9) | fp.combineHash().get(i).f2);
        }

        this.h = new int[finger_id.size()][2];
        for (int j = 0; j < finger_id.size(); j++) {
            h[j][0] = (int) finger_id.get(j);
            h[j][1] = fp.combineHash().get(j).offset;

        }
        return this.h;
    }

    //获取匹配数组，向数据库中的辅助表matchsong中插入匹配到的song_id和offset差值，便于打分
    public ArrayList<int[]> getMatchingArray() throws SQLException{
        try {
            ArrayList<int[]> matchArray = new ArrayList<>();
            openDB = new OpenDB(url);
            for (int i = 0; i < this.h.length; i++) {
                String sql = "select song_id,offset from songfinger where finger_id = " + String.valueOf(this.h[i][0]);
                PreparedStatement pStmt = openDB.conn.prepareStatement(sql);
                ResultSet rs = pStmt.executeQuery(sql);
                while (rs.next()) {
                    openDB.insertToMatchsong(rs.getInt("song_id"),rs.getInt("offset")-this.h[i][1]);
                }
                rs.close();
            }
            String sql = "select distinct song_id from matchsong order by song_id";
            PreparedStatement pStmt = openDB.conn.prepareStatement(sql);
            ResultSet resultSet = pStmt.executeQuery(sql);
            while (resultSet.next()) {
                int selectId = resultSet.getInt(1);
                int score = 0;
                Random random = new Random();
                String selsql = "select count(*) from (select dif_offset from matchsong where song_id = "
                        +String.valueOf(selectId)+") as num group by dif_offset";
                PreparedStatement pStmt_1 = openDB.conn.prepareStatement(selsql);
                ResultSet rs_1 = pStmt_1.executeQuery(selsql);
                while (rs_1.next()) {
                   if(rs_1.getInt(1)>8){
                        score = score+9 + random.nextInt(10);
                    }
                }
                int [] id_score = new int[2];
                id_score[0] = selectId;
                id_score[1] = score;
               matchArray.add(id_score);
             }
             openDB.close();
            return matchArray;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
