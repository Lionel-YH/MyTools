package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * HDFS
 * Created by Leo on 2018/4/25.
 */
public class HdfsApiTest {
    private static final Logger logger = Logger.getLogger(HdfsApiTest.class);

    static {
//        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) throws Exception{
        String uri = args[0];
        Configuration conf = new Configuration();
        //不加这一句会提示找不到FileSystem实例
        conf.set("fs.hdfs.impl",org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("hadoop.security.authentication", "kerberos");

        //If you are using Hadoop's older version less than 2.6.2, default pattern property is not available in hdfs-site.xml file then you need to specify pattern property manually.
        conf.set("dfs.namenode.kerberos.principal.pattern", "hdfs/*@AISINO.COM");
        InputStream in = null;
        System.setProperty("hadoop.home.dir", "D:\\Environment\\hadoop-2.7.4");
        System.setProperty("java.security.krb5.conf","D:\\Config\\keytab_68\\krb5.conf");
        UserGroupInformation.setConfiguration(conf);

        //kerberos认证用户部分,必须使用此方法登录，不然会连接不上
        try {
            UserGroupInformation.loginUserFromKeytab("hdfs/bin01.novalocal@AISINO.COM","D:\\Config\\keytab_68\\hdfs.keytab");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        FileSystem fileSystem = FileSystem.get(URI.create(uri),conf);
        fileSystem.setOwner(new Path("hdfs://172.16.21.68/user/hive/warehouse/test_db1.db"),"LaiXinming","LaiXinming");
//        try{
//            in = fileSystem.open(new Path(uri));
//            IOUtils.copyBytes(in,System.out,4096,false);
//        }finally {
//            IOUtils.closeStream(in);
//        }
        /*FileStatus[] status = fileSystem.listStatus(new Path("hdfs://172.16.21.68/tmp/"));
        Path[] listedPaths = FileUtil.stat2Paths(status);
        for (Path path : listedPaths) {
            System.out.println(path);
        }*/


        //释放资源
        fileSystem.close();
        System.out.println("执行成功！1111111111111");
    }
}
