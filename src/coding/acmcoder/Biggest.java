package coding.acmcoder;

import java.util.ArrayList;
import java.util.Scanner;

public class Biggest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		ArrayList<String> input = new ArrayList<String>();
		while(in.hasNext())
		{
			input.add(in.nextLine());
//			String b=in.nextLine();
//			chars=b.toCharArray();
		}
		char[] chars=input.get(0).toCharArray();
		int arraySize = chars.length;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=0;i<arraySize;i++){
			nums.add(Integer.parseInt(""+chars[i]));
		}
		int deNum = Integer.valueOf(input.get(1));
		System.out.println(maxByDeleted(nums,deNum));

	}
	private static String maxByDeleted(ArrayList<Integer> nums,int deNum){
		for(int i=0;i<deNum;i++){
			int min=nums.get(0);
			int index=0;
			for(int j=0;j<nums.size();j++){
				int num=nums.get(j);
				if(num<min){
					min=num;
					index=j;
				}
			}
			nums.remove(index);
		}
		String str="";
		for(int num:nums){
			str+=String.valueOf(num);
		}
		return str;
	}
}
