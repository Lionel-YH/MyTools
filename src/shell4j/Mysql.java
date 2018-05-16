package shell4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Mysql {

    /**
     * 入口函数
     * @param arg
     */
    public static void main(String arg[]) {
        try {
            Connection con = null; //定义一个MYSQL链接对象
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL驱动
            con = DriverManager.getConnection("jdbc:mysql://172.16.21.29/test", "root", "Root123."); //链接本地MYSQL

            Statement stmt; //创建声明
            stmt = con.createStatement();

            //新增一条数据
            stmt.executeUpdate("INSERT INTO test VALUES (2,'wjb', 32)");
            ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
            int ret_id;
            if (res.next()) {
                ret_id = res.getInt(1);
                System.out.print(ret_id);
            }
            String selectSql = "SELECT * FROM test";
            ResultSet selectRes = stmt.executeQuery(selectSql);
            while (selectRes.next()) { //循环输出结果集
                String id = selectRes.getString("id");
                String name = selectRes.getString("name");
                String age = selectRes.getString("age");
                System.out.print("\r\n\r\n");
                System.out.print("id:"+id+ "username:" + name + "age:" + age);
            }

            //删除一条数据
            String sql = "DELETE FROM test WHERE id = 2";
            long deleteRes = stmt.executeUpdate(sql); //如果为0则没有进行删除操作，如果大于0，则记录删除的条数
            System.out.print("DELETE:" + deleteRes);

            //更新一条数据
            String updateSql = "UPDATE test SET name = 'xxxx' WHERE id = 1";
            long updateRes = stmt.executeUpdate(updateSql);
            System.out.print("UPDATE:" + updateRes);

            //查询数据并输出
            String selectSql1 = "SELECT * FROM test";
            ResultSet selectRes1 = stmt.executeQuery(selectSql);
            while (selectRes1.next()) { //循环输出结果集
                String id = selectRes1.getString("id");
                String name = selectRes1.getString("name");
                String age = selectRes1.getString("age");
                System.out.print("\r\n\r\n");
                System.out.print("id:"+id+ "username:" + name + "age:" + age);
            }

        } catch (Exception e) {
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }

    }
}