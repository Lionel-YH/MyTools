package coding.acmcoder;

import java.util.ArrayList;
import java.util.Scanner;

public class ZhuanNjinzhi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] character = {'A','B','C','D','E','F'};
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		ArrayList<Character> result =  new ArrayList<Character>();
		int num = Math.abs(M);
		while(num>0){
			int rm = num%N;
		    if(10<=rm){
		    	result.add(character[rm-10]);        
		    }
		    else{
		    	result.add((char)rm);
		    }
		    num=(num-rm)/N;   
		}
	    for(int i=result.size()-1;i>=0;i--){
	    	if((int)result.get(i)>=65) System.out.print(result.get(i));
	    	else
	    		System.out.print((int)result.get(i));
	    }
	    

	}

}

