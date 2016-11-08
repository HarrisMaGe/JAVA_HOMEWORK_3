package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by MYC on 2016/11/1.
 */
public class Test {
    static String sql = null;
    static String sql2 = null;
    static OpenDB openDB = null;
    static ResultSet rs = null;
    public static void main(String[] args){
            // 要执行的SQL语句
            sql = "select * from data_time";
            openDB = new OpenDB();
            openDB.insert(2);
        try{

            rs = openDB.statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("-----------------");
        // String name = null;
            while(rs.next()) {
            //  name = rs.getString("name");
            //name = new String(name.getBytes("utf8"),"utf8");
            //URLEncoder.encode(name,"utf-8");
            //System.out.println(rs.getString("stuid") + "\t" + URLEncoder.encode(name,"utf-8"));
            //System.out.println(rs.getString("id") + "\t" + URLDecoder.decode(name,"utf-8")+"\t"+rs.getString("data_time"));
            //URLDecoder.decode(name,"utf-8");
                System.out.println(rs.getString("id")+"\t"+rs.getString("time"));
            }
            rs.close();
            openDB.close();
        } catch (SQLException e) {
        e.printStackTrace();
    }
  }
}
