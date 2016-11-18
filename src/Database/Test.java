package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by MYC on 2016/11/1.
 */
public class Test {
    static String sql = null;
    static String sql2 = null;
    static OpenDB openDB = null;
    static ResultSet rs = null;
    static String url = "jdbc:mysql://127.0.0.1:330/time?useSSL=false";

    public static void main(String[] args){
        Date current_date = new Date();
        String time = String.valueOf(current_date);
        java.text.SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat.format(current_date.getTime());
            // 要执行的SQL语句
            sql = "select * from date_time";
            openDB = new OpenDB(url);
            openDB.insertToTime(time);

        try{
            rs=openDB.find(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("-----------------");
        // String name = null;
            while(rs.next()) {
                System.out.println(rs.getString("id")+"\t"+rs.getString("time"));
            }
            rs.close();
            openDB.close();
        } catch (SQLException e) {
        e.printStackTrace();
    }
  }
}
