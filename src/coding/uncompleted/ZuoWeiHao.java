package coding.uncompleted;

import java.util.ArrayList;
import java.util.Scanner;

public class ZuoWeiHao {
	private static int k=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
		while(sc.hasNext()){
			int n = sc.nextInt();
			k = sc.nextInt();
			int[] array = new int[n];
			for(int i=0;i<n;i++){
				array[i] = sc.nextInt();
			}
			for(int i=0;i<n;i++){
				resultList.add(dealSeat(array[i]));
			}
		
		}
		
	}
	
	private static ArrayList<Integer> dealSeat(int num){
		ArrayList<Integer> result = new ArrayList<Integer>();
		int change=-1;
		if(num>k){
			result.add(-1);
			return result;
		}
		for(int i=k/2;i>0&&i<k;i+=change){
			
			//change
		}
		
		return result;
	}
	
	private static boolean haveSeat(int[][] arrays,int x,int m){
		int[] array=arrays[x-1];
		int count=0;
		for(int i=0;i<k;i++){
			if(array[i]!=0) count++;
			else count=0;
		}
		if(count>=m) return true;
		return false;
	}

}
