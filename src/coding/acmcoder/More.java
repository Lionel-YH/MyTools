package coding.acmcoder;

import java.util.ArrayList;
import java.util.Scanner;

public class More {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		ArrayList<String> input = new ArrayList<String>();
		while(in.hasNext())
		{
			input.add(in.nextLine());
		}

		for(String each:input){
			char[] chars=each.toCharArray();
			System.out.println(numOf(chars));
		}
	}
	private static int numOf(char[] chars){
		int len=chars.length;
		int max=0;
		int count=0;
		for(int i=0;i<len;i++){
			char c=chars[i];
			if(c!='a'&&c!='b'&&c!='c'&&c!='d'&&c!='e'&&c!='A'&&c!='B'&&c!='C'&&c!='D'&&c!='E'){
				count++;
				if(count>max) max=count;
			}else{
				count=0;
			}
		}
		int firstIndex=-1;
		int endIndex=len;
		for(int i=0;i<len;i++){
			char c=chars[i];
			if(c!='a'&&c!='b'&&c!='c'&&c!='d'&&c!='e'&&c!='A'&&c!='B'&&c!='C'&&c!='D'&&c!='E'){
				firstIndex++;
			}else{
				break;
			}
		}
		for(int i=len-1;i>=0;i--){
			char c=chars[i];
			if(c!='a'&&c!='b'&&c!='c'&&c!='d'&&c!='e'&&c!='A'&&c!='B'&&c!='C'&&c!='D'&&c!='E'){
				endIndex--;
			}else{
				break;
			}
		}
		int max2=len-endIndex+firstIndex+1;
		return max2>max?max2:max;
	}

}
