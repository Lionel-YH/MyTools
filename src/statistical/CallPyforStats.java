package statistical;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CallPyforStats {
	public double getFAlphaQuantile(String filepath, double alpha, double f1, double f2) {
		double result = 0.000000;
		try {
			String a = String.valueOf(alpha);
			String b = String.valueOf(f1);
			String c = String.valueOf(f2);
			String[] args = new String[] { "python", filepath, a, b, c };
			Process pr = Runtime.getRuntime().exec(args);
			//取得命令结果的输出流
			InputStream fis=pr.getInputStream();
			//用一个读输出流类去读
			InputStreamReader isr=new InputStreamReader(fis);
			//用缓冲器读行
			BufferedReader br=new BufferedReader(isr);
			String line=null;
			//直到读完为止
			while((line=br.readLine())!=null)
			{
				result = Double.parseDouble(line);
			}
		}catch(Exception e) {
			return -99999;
		}
		return result;
	}

	public double getChi2AlphaQuantile(String filepath, double alpha, double f1) {
		double result = 0.000000;
		try {
			String a = String.valueOf(alpha);
			String b = String.valueOf(f1);
			String[] args = new String[] { "python", filepath, a, b };
			Process pr = Runtime.getRuntime().exec(args);
			//取得命令结果的输出流
			InputStream fis=pr.getInputStream();
			//用一个读输出流类去读
			InputStreamReader isr=new InputStreamReader(fis);
			//用缓冲器读行
			BufferedReader br=new BufferedReader(isr);
			String line=null;
			//直到读完为止
			while((line=br.readLine())!=null)
			{
				result = Double.parseDouble(line);
			}
		}catch(Exception e) {
			return -99999;
		}
		return result;
	}

	public double getTAlphaQuantile(String filepath, double alpha, double f1) {
		double result = 0.000000;
		try {
			String a = String.valueOf(alpha);
			String b = String.valueOf(f1);
			String[] args = new String[] { "python", filepath, a, b };
			Process pr = Runtime.getRuntime().exec(args);
			//取得命令结果的输出流
			InputStream fis=pr.getInputStream();
			//用一个读输出流类去读
			InputStreamReader isr=new InputStreamReader(fis);
			//用缓冲器读行
			BufferedReader br=new BufferedReader(isr);
			String line=null;
			//直到读完为止
			while((line=br.readLine())!=null)
			{
				result = Double.parseDouble(line);
			}
		}catch(Exception e) {
			return -99999;
		}
		return result;
	}
}
