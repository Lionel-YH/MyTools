package Hive;

import java.io.IOException;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  
  
import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.security.UserGroupInformation;  
  
public class HiveJdbcTest {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
    	try {
    		Class.forName(driverName);
    		} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    		System.exit(1);
    		}

        //kerberos验证
        String url = "jdbc:hive2://172.16.21.68:10000/default;principal=hive/bin01.novalocal@AISINO.COM";//这里的principal是hive服务使用的，不是用于用户登录的
        Configuration conf = new Configuration();
        conf.setBoolean("hadoop.security.authorization", true);
        conf.set("hadoop.security.authentication", "Kerberos");
        System.setProperty("java.security.krb5.conf","D:/krb5.conf");
        System.setProperty("hadoop.home.dir", "D:\\Develop\\hadoop-2.7.4");
//        conf.set("keytab.file" , "/root/keytabs/hive.keytab" );
//        conf.set("keytab.file" , "D:/hive.keytab" );
//        System.setProperty("java.security.krb5.conf","/etc/krb5.conf");
//        conf.set("kerberos.principal" , "hive/bin01.novalocal@AISINO.COM" );
        try {  
            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab("hive/bin01.novalocal@AISINO.COM","D:/hive.keytab");
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }

        //普通验证
//        String url = "jdbc:hive2://172.16.21.68:10000/default";

        try {  
           // Class.forName(driverName);  
//            Connection conn = DriverManager.getConnection(url,"hive","");
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            String sql1 = "show grant role admin_role";
            String sql = "show roles";
            ResultSet rs1 = stmt.executeQuery(sql);
            while(rs1.next()) {
                System.out.println(rs1.getString(1));
                /*for(int i = 1;;i++){
                    try{
                        System.out.print(rs1.getString(i)+" ,  ");
                    }catch (Exception e){
                        System.out.println();
                        break;
                    }
                }*/
            }
            } catch (Exception e) {
            e.printStackTrace();  
            // TODO: handle exception  
        }  
  
    }  
  
}  