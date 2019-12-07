package coding.acmcoder;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int sum = 0;
		int[] array = new int[n];
		for(int i=0;i<n;i++){
			array[i] = sc.nextInt();
		}
		
		for(int x=0;x<n;x++){
			for(int y=x+1;y<n;y++){
				if(m<(array[x]^array[y])){
					sum++;
				}
			}
		}
		System.out.println(sum);
		sc.close();
	}

}
