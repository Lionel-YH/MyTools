package Shell4Java;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import javax.swing.*;
import java.io.InputStream;
import java.io.OutputStream;

public class Shell{

    public static void main(String[] arg){
        StringBuilder stringBuilder = new StringBuilder();

        try{
            JSch jsch=new JSch();

            //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");

            String host=null;
            if(arg.length>0){
                host=arg[0];
            }
            else{
                host="root@172.16.21.69";
            }
            String user=host.substring(0, host.indexOf('@'));
            host=host.substring(host.indexOf('@')+1);

            Session session=jsch.getSession(user, host, 22);

            String passwd = "root@123.";
            session.setPassword(passwd);

            UserInfo ui = new MyUserInfo(){
                public void showMessage(String message){
                    JOptionPane.showMessageDialog(null, message);
                }
                public boolean promptYesNo(String message){
                    Object[] options={ "yes", "no" };
                    int foo=JOptionPane.showOptionDialog(null,
                            message,
                            "Warning",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    return foo==0;
                }

                // If password is not given before the invocation of Session#connect(),
                // implement also following methods,
                //   * UserInfo#getPassword(),
                //   * UserInfo#promptPassword(String message) and
                //   * UIKeyboardInteractive#promptKeyboardInteractive()

            };

            session.setUserInfo(ui);

            // It must not be recommended, but if you want to skip host-key check,
            // invoke following,
            session.setConfig("StrictHostKeyChecking", "no");

            //session.connect();
            session.connect();   // making a connection with timeout.

            Channel channel=session.openChannel("shell");


            channel.connect(30*1000);
            //获取输入流和输出流
            InputStream instream = channel.getInputStream();
            OutputStream outstream = channel.getOutputStream();

            //发送需要执行的SHELL命令，需要用\n结尾，表示回车
            String shellCommand = "/usr/sbin/kadmin.local -q 'delprinc test'  \n";
            outstream.write(shellCommand.getBytes());
            outstream.flush();


            //获取命令执行的结果
            if (instream.available() > 0) {
                byte[] data = new byte[instream.available()];
                int nLen = instream.read(data);

                if (nLen < 0) {
                    throw new Exception("network error.");
                }

                //转换输出结果并打印出来
                String temp = new String(data, 0, nLen,"UTF-8");
                System.out.println(temp);
                stringBuilder.append(temp+'\n');
            }


            shellCommand = "yes\n";
            outstream.write(shellCommand.getBytes());
            outstream.flush();


            //获取命令执行的结果
            if (instream.available() > 0) {
                byte[] data = new byte[instream.available()];
                int nLen = instream.read(data);

                if (nLen < 0) {
                    throw new Exception("network error.");
                }

                //转换输出结果并打印出来
                String temp = new String(data, 0, nLen,"UTF-8");
                System.out.println(temp);
                stringBuilder.append(temp+'\n');
            }
            shellCommand = "/usr/sbin/kadmin.local -q 'addprinc test'  \n";
            outstream.write(shellCommand.getBytes());
            outstream.flush();


            //获取命令执行的结果
            if (instream.available() > 0) {
                byte[] data = new byte[instream.available()];
                int nLen = instream.read(data);

                if (nLen < 0) {
                    throw new Exception("network error.");
                }

                //转换输出结果并打印出来
                String temp = new String(data, 0, nLen,"UTF-8");
                System.out.println(temp);
                stringBuilder.append(temp+'\n');
            }


            shellCommand = "aisino\n";
            outstream.write(shellCommand.getBytes());
            outstream.flush();
            if (instream.available() > 0) {
                byte[] data = new byte[instream.available()];
                int nLen = instream.read(data);

                if (nLen < 0) {
                    throw new Exception("network error.");
                }

                //转换输出结果并打印出来
                String temp = new String(data, 0, nLen,"UTF-8");
                System.out.println(temp);
            }
            shellCommand = "aisino\n";
            outstream.write(shellCommand.getBytes());
            outstream.flush();
            if (instream.available() > 0) {
                byte[] data = new byte[instream.available()];
                int nLen = instream.read(data);

                if (nLen < 0) {
                    throw new Exception("network error.");
                }

                //转换输出结果并打印出来
                String temp = new String(data, 0, nLen,"UTF-8");
                System.out.println(temp);
                stringBuilder.append(temp+'\n');
                int i=1;
            }
            outstream.close();
            instream.close();
            session.disconnect();
            channel.disconnect();
            int i=1;
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println(stringBuilder.toString());
    }
}
