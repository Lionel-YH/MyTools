package Statistical;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.python.core.*;
import org.python.util.PythonInterpreter;

public class Execute {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException, InterruptedException{
        // TODO Auto-generated method stub
        System.out.printf("aaaaa");
        Process p = Runtime.getRuntime().exec("python  D:\\wugang-documents\\sourcecode\\pt_lab\\testnumpy.py");

        //取得命令结果的输出流
        InputStream fis=p.getInputStream();
        //用一个读输出流类去读
        InputStreamReader isr=new InputStreamReader(fis);
        //用缓冲器读行
        BufferedReader br=new BufferedReader(isr);
        String line=null;
        //直到读完为止
        while((line=br.readLine())!=null)
        {
            System.out.println(line);
        }
    }

}

