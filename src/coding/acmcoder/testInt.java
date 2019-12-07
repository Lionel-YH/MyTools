package coding.acmcoder;

import java.util.ArrayList;
import java.util.Scanner;

public class testInt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> input = new ArrayList<Integer>();
		while(in.hasNextInt())
		{
			input.add(in.nextInt());
		}
		System.out.println(in.next());
		for(Integer each:input) System.out.println(each);

	}

}
