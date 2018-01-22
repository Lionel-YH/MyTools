package Shell4Java;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo {

    static String sql = null;
    static DbHelper db1 = null;
    static ResultSet ret = null;

    public static void main(String[] args) {
        sql = "select *from test";//SQL语句  
        String sqldel = "insert into test values(2,'wjb',32)";
        db1 = new DbHelper(sql);//创建DBHelper对象  

        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {
                String uid = ret.getString(1);
                int id=ret.getInt("id");
                String ufname = ret.getString(2);
                String age = ret.getString(3);
                System.out.println(uid + "\t" + ufname + "\t" + age + "\t"  );
            }//显示数据  
            ret.close();
            db1.close();//关闭连接  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}  