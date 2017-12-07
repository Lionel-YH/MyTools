import com.jcraft.jsch.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class JdbcTest {
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
		String url = "jdbc:hive2://192.168.24.221:10000/fapiao;principal=hive/htxx1@AISINOYJY.COM";
		//String url = "jdbc:hive2://192.168.24.223:10000/fapiao;principal=Fapiao/htxx3@AISINOYJY.COM";
		Configuration conf = new Configuration();
		conf.setBoolean("hadoop.security.authorization", true);
		conf.set("hadoop.security.authentication", "Kerberos");
		conf.set("keytab.file", "/home/Fapiao/fapiao.keytab");// C:/fapiao.keytab
		System.setProperty("java.security.krb5.conf", "/etc/krb5.conf"); // "C:/ProgramData/MIT/Kerberos5/krb5.ini"
																			// /etc/krb5.conf
		conf.set("kerberos.principal", "Fapiao@AISINOYJY.COM");

		try {
			UserGroupInformation.setConfiguration(conf);
			UserGroupInformation.loginUserFromKeytab("Fapiao@AISINOYJY.COM", "/home/Fapiao/fapiao.keytab"); /// home/Fapiao/fapiao.keytab
																											/// C:/fapiao.keytab

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 获取上次最大id
		int lastvalue = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select id from enterprise order by id desc limit 1";
			String sql1 = "show roles";
			ResultSet rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				lastvalue = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		// 导入数据
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time1 = new Date();
		String starttime = formatter.format(time1);
		try {
			String shpath = "sqoop import --connect jdbc:mysql://172.16.20.105:3306/mutation --username mutation --password mutation123 --table enterprise --fields-terminated-by ',' --lines-terminated-by '\n' --hive-import --hive-database fapiao --incremental append  --check-column id --last-value "
					+ lastvalue;
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date time2 = new Date();

		String endtime = formatter.format(time2);

		// //查询本次导入数量
		int count = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select count(*) from enterprise where id>" + lastvalue;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// //写log文件
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("/home/Fapiao/fapiaolog.log");// C:/fapiaolog.log
															// /home/Fapiao/fapiaolog.log
			fw = new FileWriter(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(starttime + "," + endtime + ",mutation.enterprise" + ",fapiao.enterprise" + "," + lastvalue
				+ "," + count);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 第二个表
		lastvalue = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select id from invoice order by id desc limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				lastvalue = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		// 导入数据

		time1 = new Date();
		starttime = formatter.format(time1);
		try {
			String shpath = "sqoop import --connect jdbc:mysql://172.16.20.105:3306/mutation --username mutation --password mutation123 --table invoice --fields-terminated-by ',' --lines-terminated-by '\n' --hive-import --hive-database fapiao --incremental append  --check-column id --last-value "
					+ lastvalue;
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		time2 = new Date();

		endtime = formatter.format(time2);

		// //查询本次导入数量
		count = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select count(*) from invoice where id>" + lastvalue;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// //写log文件
		fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("/home/Fapiao/fapiaolog.log");// C:/fapiaolog.log
															// /home/Fapiao/fapiaolog.log
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		pw.println(starttime + "," + endtime + ",mutation.invoice" + ",fapiao.invoice" + "," + lastvalue + ","
				+ count);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 第三个表
		lastvalue = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select id from invoicedetail order by id desc limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				lastvalue = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		// 导入数据

		time1 = new Date();
		starttime = formatter.format(time1);
		try {
			String shpath = "sqoop import --connect jdbc:mysql://172.16.20.105:3306/mutation --username mutation --password mutation123 --table invoicedetail --fields-terminated-by ',' --lines-terminated-by '\n' --hive-import --hive-database fapiao --incremental append  --check-column id --last-value "
					+ lastvalue;
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		time2 = new Date();

		endtime = formatter.format(time2);

		// //查询本次导入数量
		count = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select count(*) from invoicedetail where id>" + lastvalue;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// //写log文件
		fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("/home/Fapiao/fapiaolog.log");// C:/fapiaolog.log
															// /home/Fapiao/fapiaolog.log
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		pw.println(starttime + "," + endtime + ",mutation.invoicedetail" + ",fapiao.invoicedetail" + ","
				+ lastvalue + "," + count);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 第四个表
		lastvalue = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select id from product order by id desc limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				lastvalue = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		// 导入数据

		time1 = new Date();
		starttime = formatter.format(time1);
		try {
			String shpath = "sqoop import --connect jdbc:mysql://172.16.20.105:3306/mutation --username mutation --password mutation123 --table product --fields-terminated-by ',' --lines-terminated-by '\n' --hive-import --hive-database fapiao  --incremental append  --check-column id --last-value "
					+ lastvalue;
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		time2 = new Date();

		endtime = formatter.format(time2);

		// //查询本次导入数量
		count = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select count(*) from product where id>" + lastvalue;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// //写log文件
		fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("/home/Fapiao/fapiaolog.log");// C:/fapiaolog.log
															// /home/Fapiao/fapiaolog.log
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		pw.println(starttime + "," + endtime + ",mutation.product" + ",fapiao.product" + "," + lastvalue + ","
				+ count);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 第五个表
		lastvalue = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select id from productextend order by id desc limit 1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				lastvalue = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		// 导入数据

		time1 = new Date();
		starttime = formatter.format(time1);
		try {
			String shpath = "sqoop import --connect jdbc:mysql://172.16.20.105:3306/mutation --username mutation --password mutation123 --table productextend --fields-terminated-by ',' --lines-terminated-by '\n' --hive-import --hive-database fapiao  --incremental append  --check-column id --last-value "
					+ lastvalue;
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			String result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		time2 = new Date();

		endtime = formatter.format(time2);

		// //查询本次导入数量
		count = 0;
		try {
			// Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			String sql = "select count(*) from productextend where id>" + lastvalue;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// //写log文件
		fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("/home/Fapiao/fapiaolog.log");// C:/fapiaolog.log
															// /home/Fapiao/fapiaolog.log
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		pw.println(starttime + "," + endtime + ",mutation.productextend" + ",fapiao.productextend" + ","
				+ lastvalue + "," + count);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 导入hive
		int returnCode = 0;
		JSch jsch = new JSch();
//		MyUserInfo userInfo = new MyUserInfo();

		try {
			formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time3 = new Date();
			String filetime = formatter.format(time3);
			// Create and connect session.
			Session session = jsch.getSession("Fapiao", "192.168.24.223", 22);
			session.setPassword("fapiaoyjy2017");
//			session.setUserInfo(userInfo);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			// Create and connect channel.
			Channel channel = session.openChannel("exec");
			//((ChannelExec) channel).setCommand(
			//		"kinit -kt /home/Fapiao/fapiao.keytab Fapiao@AISINOYJY.COM;hadoop fs -put /home/Fapiao/fapiaolog.log /user/Fapiao/logs/log_"
			//				+ filetime);
			((ChannelExec) channel).setCommand(
					"kinit -kt /home/Fapiao/fapiao.keytab Fapiao@AISINOYJY.COM;hive -e 'load data local inpath \" /home/Fapiao/fapiaolog.log\" into table fapiao.importlog'"
							+ filetime);

			channel.setInputStream(null);
			BufferedReader input = new BufferedReader(new InputStreamReader(channel.getInputStream()));

			channel.connect();
			System.out.println("The remote command is: ");

			// Get the output of remote command.
			Vector<String> stdout = new Vector<String>();
			String line;
			while ((line = input.readLine()) != null) {
				stdout.add(line);
			}
			input.close();

			// Get the return code only after the channel is closed.
			if (channel.isClosed()) {
				returnCode = channel.getExitStatus();
			}

			// Disconnect the channel and session.
			channel.disconnect();
			session.disconnect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * try { // Class.forName(driverName); SimpleDateFormat formatter = new
		 * SimpleDateFormat ("yyyyMMddHHmmss"); Date time3 =new Date(); String
		 * filetime = formatter.format(time3); String [] shpath={
		 * "hadoop fs -put /home/Fapiao/fapiaolog.log /user/Fapiao/logs/log_"
		 * +filetime}; Process ps = Runtime.getRuntime().exec(shpath);
		 * ps.waitFor();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); // TODO: handle
		 * exception }
		 */

	}

}