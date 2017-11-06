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
//        String url = "jdbc:hive2://172.16.21.69:10000/default;principal=hive/htxx1@AISINOYJY.COM";
        String url = "jdbc:hive2://172.16.21.68:10000/default";
/*        Configuration conf = new Configuration();
        conf.setBoolean("hadoop.security.authorization", true);
        conf.set("hadoop.security.authentication", "Kerberos"); 
        conf.set("keytab.file" , "C:/chenyimeng.keytab" );
        System.setProperty("java.security.krb5.conf","C:/ProgramData/MIT/Kerberos5/krb5.ini"); 
        conf.set("kerberos.principal" , "ChenYimeng/htxx4@AISINOYJY.COM" ); 
          
        try {  
            UserGroupInformation.setConfiguration(conf);
            UserGroupInformation.loginUserFromKeytab("ChenYimeng/htxx4@AISINOYJY.COM","C:/chenyimeng.keytab");  
              
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  */
        try {  
           // Class.forName(driverName);  
            Connection conn = DriverManager.getConnection(url,"hive","");
            Statement stmt = conn.createStatement();  
            String sql1 = "show grant role lxm_role";
            ResultSet rs1 = stmt.executeQuery(sql1);
            while(rs1.next()) {
                for(int i = 1;;i++){
                    try{
                        System.out.print(rs1.getString(i)+" ,  ");
                    }catch (Exception e){
                        System.out.println();
                        break;
                    }
                }
            }
            } catch (Exception e) {
            e.printStackTrace();  
            // TODO: handle exception  
        }  
  
    }  
  
}  