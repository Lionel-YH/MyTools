import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		int length = input.length();
		char ch = input.charAt(length-1);
		int depth = (Integer.valueOf(ch));
		String str = in.next();
		String result = "";
		for(int i=0;i<str.length()-1;i++){
			int index = input.indexOf(str.charAt(i));
			if(-1==index){
				result+="0 ";
			}else{
				char d = input.charAt(index+1);
				int floor = Integer.valueOf(d);
				result+=(depth-floor+1)+" ";
			}	
		}
		int lastIndex = input.indexOf(str.charAt(str.length()-1));
		if(-1==lastIndex){
			result+="0";
		}else{
			char d = input.charAt(lastIndex+1);
			int floor = Integer.valueOf(d);
			result+=(depth-floor+1)+"";
		}
		System.out.println(result);
	}
}
