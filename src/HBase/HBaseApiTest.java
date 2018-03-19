package HBase;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.security.User;
import org.apache.hadoop.hbase.security.access.AccessControlClient;
import org.apache.hadoop.hbase.security.access.Permission;
import org.apache.hadoop.hbase.security.access.UserPermission;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;

import javax.security.auth.Subject;
import java.io.IOException;
import java.util.*;

/**
 * HBase实现类
 * Created by shiyufeng on 2017/4/25.
 */
public class HBaseApiTest {
    private static final Logger logger = Logger.getLogger(HBaseApiTest.class);
    static Configuration configuration = null;
    static String ip = "172.26.21.101,172.26.21.102,172.26.21.103";
    static String port = "2181";
    static boolean krbOpen = false;

    static {
        if(krbOpen){
            Configuration conf = new Configuration();
            conf.set("hbase.zookeeper.property.maxclientcnxns", "300");
            System.setProperty("hadoop.home.dir", "D:\\Develop\\hadoop-2.7.4");
            conf.set("hbase.ipc.client.socket.timeout.connect","1000");
            conf.set("zookeeper.session.timeout", "500");
            conf.set("hbase.regionserver.handler.count", "500");
            System.setProperty("java.security.krb5.conf","D:/krb5.conf");
            conf.set("hadoop.security.authentication","kerberos");
            conf.set("hbase.master.kerberos.principal","hbase/_HOST@AISINO.COM");//从Hbase-site.xml文件中获取配置信息
            conf.set("hbase.regionserver.kerberos.principal","hbase/_HOST@AISINO.COM");//从Hbase-site.xml文件中获取配置信息
            conf.set("hbase.zookeeper.property.clientPort",port);
            conf.set("hbase.security.authentication","kerberos");
            conf.set("hbase.zookeeper.quorum",ip);
            UserGroupInformation.setConfiguration(conf);

            //kerberos认证用户部分,必须使用此方法登录，不然会连接不上
            try {
                UserGroupInformation.loginUserFromKeytab("hbase/bin01.novalocal@AISINO.COM","D:/hbase.keytab");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            configuration = HBaseConfiguration.create(conf);
        }else{
            Configuration conf = new Configuration();
            conf.set("hbase.zookeeper.property.maxclientcnxns", "300");
            System.setProperty("hadoop.home.dir", "D:\\Develop\\hadoop-2.7.4");
            conf.set("hbase.ipc.client.socket.timeout.connect","1000");
            conf.set("zookeeper.session.timeout", "500");
            conf.set("hbase.regionserver.handler.count", "500");
            conf.set("hbase.zookeeper.property.clientPort",port);
            conf.set("hbase.zookeeper.quorum",ip);
            UserGroupInformation.setConfiguration(conf);
            configuration = HBaseConfiguration.create(conf);
        }

    }

    /**
     * 根据表名，查询列族
     *
     * @param hbaseConn
     * @param tableName
     * @return
     */

    public List<String> queryFamilies(Connection hbaseConn, String tableName) throws IOException {
        if (logger.isInfoEnabled()) {
            logger.info("HBase.HBaseApiTest queryFamilies begin");
        }
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = hbaseConn.getTable(tableNameObj);
//        HTable table = new HTable(configuration, tableName);
        HTableDescriptor desc = table.getTableDescriptor();
        Collection<HColumnDescriptor> collection = desc.getFamilies();
        List<String> list = new ArrayList<>();
        for (HColumnDescriptor hColumnDescriptor : collection) {
            System.out.println(hColumnDescriptor.getNameAsString());
            list.add(hColumnDescriptor.getNameAsString());
        }
        if (logger.isInfoEnabled()) {
            logger.info("HBase.HBaseApiTest queryFamilies end");
        }
        return list;
    }

    /**
     * 查询所有表
     *
     * @param hbaseConn
     */

    public List<String> queryTable(Connection hbaseConn) throws IOException {
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl queryTable begin");
        }
        Admin admin = hbaseConn.getAdmin();
//        HBaseAdmin admin = new HBaseAdmin(hbaseConn);
        TableName[] names = admin.listTableNames();
        List<String> list = new ArrayList<>();
        for (TableName name : names) {
            System.out.println(name.getNameAsString());
            list.add(name.getNameAsString());
        }
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl queryTable end");
        }
        return list;
    }

    /**
     * 查询权限
     *
     * @param hbaseConn 配置
     * @param tableRegex  表名
     */

    public List<UserPermission> getPermissions(Connection hbaseConn, String tableRegex) throws Throwable {
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl grant begin");
        }
        System.out.println("HBaseServiceImpl getPerm begin1111");
        return AccessControlClient.getUserPermissions(hbaseConn, tableRegex );
    }

    /**
     * 授予用户权限
     *
     * @param userName 用户
     * @param actions  权限
     */

    public void grant(Connection hbaseConn, String tableName,String userName, String family,String qual,Permission.Action... actions) throws Throwable {
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl grant begin");
        }
        TableName tableNameObj = TableName.valueOf(tableName);
        System.out.println("HBaseServiceImpl grant begin1111");
        System.out.println("HBaseServiceImpl grant begin2222");
        AccessControlClient.grant(hbaseConn, tableNameObj, userName, Bytes.toBytes(family), Bytes.toBytes(qual), actions);
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl grant end");
        }
    }

    public void grant(Connection hbaseConn, String namespace,String userName, Permission.Action... actions) throws Throwable {
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl grant begin");
        }
        System.out.println("HBaseServiceImpl grant begin1111");
        System.out.println("HBaseServiceImpl grant begin2222");
        AccessControlClient.grant(hbaseConn, namespace, userName, actions);
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl grant end");
        }
    }

    /**
     * 回收用户权限
     *
     * @param userName 用户
     * @param actions  权限
     */

    public void revoke(Connection hbaseConn, String tableName,String userName, String family,String qual,Permission.Action... actions) throws Throwable {
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl revoke begin");
        }
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = hbaseConn.getTable(tableNameObj);
        TableName tableName1 = table.getName();
        AccessControlClient.revoke(hbaseConn, tableName1, userName, Bytes.toBytes(family), Bytes.toBytes(qual), actions);
        if (logger.isInfoEnabled()) {
            logger.info("HBaseServiceImpl revoke end");
        }
    }

    /**
     * 添加hbase配置
     *
     * @param ip
     */

    public void addHbaseConfig(String ip) {

    }

    /**
     * 修改hbase配置
     *
     * @param ip
     */

    public void updateHbaseConfig(String ip) {

    }

    /**
     * 添加/更新数据
     *
     * @param tableName 表名称
     * @param rowKey    唯一标识
     * @param family    列族
     * @param qualifier 列
     * @param value     值
     */

    public void add(Connection hbaseConn, String tableName, String rowKey, String family, String qualifier, String value) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = hbaseConn.getTable(tableNameObj);
        // 需要插入数据库的数据集合
        List<Put> putList = new ArrayList<>();
        Put put = new Put(Bytes.toBytes(rowKey));
        // 列族、列、值
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
        putList.add(put);
        // 将数据集合插入到数据库
        table.put(putList);
        System.out.println("---------------插入数据 END-----------------");
    }

    /**
     * 删除指定列的所有值
     *
     * @param tableName 表名称
     * @param rowKey    唯一标识
     * @throws java.io.IOException
     */

    public void delete(Connection hbaseConn, String tableName, String rowKey) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = hbaseConn.getTable(tableNameObj);
        Delete deleteAll = new Delete(Bytes.toBytes(rowKey));
        table.delete(deleteAll);
        System.out.println("all columns are deleted!");
    }

    /**
     * 查询列
     *
     * @param tableName 表名称
     * @param rowKey    唯一标识
     * @param family    列族
     * @param qualifier 列
     * @throws java.io.IOException
     */

    public Result find(Connection hbaseConn, String tableName, String rowKey, String family, String qualifier) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = hbaseConn.getTable(tableNameObj);
        // 根据tableName查询
        if (StringUtils.isBlank(rowKey) && StringUtils.isBlank(family) && StringUtils.isBlank(qualifier)) {
            ResultScanner rs = table.getScanner(new Scan());
            for (Result r : rs) {
                System.out.println("获得到rowkey:" + Bytes.toString(r.getRow()));
                for (Cell cell : r.rawCells()) {
                    System.out.println("列族：" + Bytes.toString(cell.getFamilyArray())
                            + "====" + Bytes.toString(cell.getQualifierArray())
                            + "====值:" + Bytes.toString(cell.getValueArray()));
                }
            }
        }
        //根据tableName，rowKey查询
        if (!StringUtils.isBlank(rowKey) && StringUtils.isBlank(family) && StringUtils.isBlank(qualifier)) {
            Get scan = new Get(rowKey.getBytes());
            Result r = table.get(scan);
            System.out.println("获得到rowkey:" + new String(r.getRow()));
            for (Cell cell : r.rawCells()) {
                System.out.println("列族：" + new String(cell.getFamilyArray())
                        + "====" + new String(cell.getQualifierArray())
                        + "====值:" + new String(cell.getValueArray()));
            }
        }
        return null;
    }

    /**
     * 查询列
     *
     * @param tableName 表名称
     * @param rowKey    唯一标识
     * @param family    列族
     * @param qualifier 列
     * @throws java.io.IOException
     */

    public Result findByQualifier(Connection hbaseConn, String tableName, String rowKey, String family, String qualifier) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = hbaseConn.getTable(tableNameObj);
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier)); // 获取指定列族和列修饰符对应的列
        Result result = table.get(get);
        for (Cell cell : result.listCells()) {
            System.out.println("family:" + Bytes.toString(cell.getFamilyArray()));
            System.out.println("qualifier:" + Bytes.toString(cell.getQualifierArray()));
            System.out.println("value:" + Bytes.toString(cell.getValueArray()));
            System.out.println("Timestamp:" + cell.getTimestamp());
            System.out.println("-------------------------------------------");
        }
        return result;
    }


    public Result findByFamily(Connection hbaseConn,String tableName, String rowKey, String family) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = hbaseConn.getTable(tableNameObj);
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addFamily(Bytes.toBytes(family)); // 获取指定列族和列修饰符对应的列
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.println("family:" + Bytes.toString(kv.getFamily()));
            System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
            System.out.println("value:" + Bytes.toString(kv.getValue()));
            System.out.println("Timestamp:" + kv.getTimestamp());
            System.out.println("-------------------------------------------");
        }
        return result;
    }

    /**
     * 根据rwokey查询
     *
     * @param tableName
     * @param rowKey
     */

    public Result findByRowKey(Connection hbaseConn, String tableName, String rowKey) throws IOException {
        TableName tableNameObj = TableName.valueOf(tableName);
        Table table = hbaseConn.getTable(tableNameObj);
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        for (KeyValue kv : result.list()) {
            System.out.println("family:" + Bytes.toString(kv.getFamily()));
            System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
            System.out.println("value:" + Bytes.toString(kv.getValue()));
            System.out.println("Timestamp:" + kv.getTimestamp());
            System.out.println("-------------------------------------------");
        }
        return result;
    }

    /**
     * 创建表
     *
     * @param tableName 表名称
     * @param family    列族
     */

    public boolean creatTable(Connection hbaseConn, String tableName, List<String> family) throws IOException {
        boolean flag = false;
        Admin admin = hbaseConn.getAdmin();
        TableName tableNameObj = TableName.valueOf(tableName);
        HTableDescriptor desc = new HTableDescriptor(tableNameObj);
        for (int i = 0; i < family.size(); i++) {
            desc.addFamily(new HColumnDescriptor(family.get(i)));
        }
        if (admin.tableExists(tableNameObj)) {
            System.out.println("table Exists!");
            flag = false;
            return flag;
        } else {
            admin.createTable(desc);
            System.out.println("create table Success!");
            flag = true;
            return flag;
        }
    }

    public static void main(String[] args) {
        HBaseApiTest ht = new HBaseApiTest();
        List<UserPermission> upList = new ArrayList<>();
        Subject sb = new Subject();
        String[] groups = {"hbase"};
        User user = new User.SecureHadoopUser(UserGroupInformation.createUserForTesting("hbase", groups));
        try{
           /*
           result = ht.queryFamilies(HBase.HBaseApiTest.configuration,"/*unsensitive");
            ht.grant(HBase.HBaseApiTest.configuration,"unsensitive","WangJiebin","info","*", Permission.Action.CREATE);
            ht.grant(HBase.HBaseApiTest.configuration,"default","WangJiebin",Permission.Action.READ);
            Permission.Action actions = Permission.Action.READ;
            ht.revoke(HBaseApiTest.configuration,"unsensitive","WangJiebin","", "", actions);
            */
            /*
            //未开启kerberos时，使用此方法切换用户，不然程序会默认使用运行程序的系统当前用户
            String[] groups = {"hbase"};
            User user = new User.SecureHadoopUser(UserGroupInformation.createUserForTesting("hbase", groups));
            Connection hbaseConn  = ConnectionFactory.createConnection(HBaseApiTest.configuration,user);
            */

            //krbOpen == false
            Connection hbaseConn  = ConnectionFactory.createConnection(HBaseApiTest.configuration,user);
            //krbOpen == true
//            Connection hbaseConn  = ConnectionFactory.createConnection(HBaseApiTest.configuration);
//            upList = ht.getPermissions(hbaseConn,"dw_hbase_nsr");
//            List<String> tables = ht.queryTable(hbaseConn);
            List<String> familys = ht.queryFamilies(hbaseConn,"dw_hbase_nsr");
//            Result result = ht.find(hbaseConn,"dw_hbase_fp","","","");
            System.out.println("=============");
            System.out.println("执行成功！");
        }catch (Throwable throwable){
            throwable.printStackTrace();
            System.out.println("执行失败");
        }
        for(UserPermission up:upList){
            System.out.println("User:" + new String(up.getUser()));
            if(up.hasNamespace()){
                System.out.println("Namespace:" + up.getNamespace());
            }
            if(up.hasTable()){
                System.out.println("Table:" + up.getTableName().toString());
            }
            if(up.hasFamily()){
                System.out.println("Family:" + new String(up.getFamily()));
            }
            if(up.hasQualifier()){
                System.out.println("Qualifier:" + new String(up.getQualifier()));
            }
            Permission.Action[] actions = up.getActions();
            for(Permission.Action action:actions){
                System.out.print(action.toString());
                System.out.print("--");
            }
            System.out.println();
        }
        System.out.println("执行成功！1111111111111");
    }
}
