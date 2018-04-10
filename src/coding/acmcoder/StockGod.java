package coding.acmcoder;

import java.util.ArrayList;
import java.util.Scanner;

public class StockGod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner in = new Scanner(System.in);
		while(in.hasNext())
		{
			String b=in.nextLine();
			list.add(Integer.parseInt(b));
		}
		for(Integer days:list){
			System.out.println(getStock(days));
		}
	}
	
	public static int getStock(int days){
		
		if(days<=0) return 0;
		int count=1;
		while(sum(count)<=days-1) count++;
		return days-2*count+2;
	}
	
	public static int sum(int n){
		int sum=0;
		for(int i=2;i<=n+1;i++){
			sum+=i;
		}
		return sum;
	}
}
