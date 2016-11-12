package Matching;

import Database.OpenDB;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 冬至节 on 2016/11/12.
 */
public class Score {
    static String sql = null;
    static String sql2 = null;
    static OpenDB openDB = null;
    static ResultSet rs = null;
    static String url = "jdbc:mysql://127.0.0.1:330/time?useSSL=false";

    //需要改进
    public int matchSongs(){
        openDB = new OpenDB(url);
        rs=openDB.find(sql);
        return 1;

    }


}
