package coding.acmcoder;

import java.util.ArrayList;
import java.util.Scanner;

public class HuoQiuShu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> resultCount = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
		while(sc.hasNext()){
			int count=0;
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			int n = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			int[] array = new int[n];
			for(int i=0;i<n;i++){
				array[i] = sc.nextInt();
			}
			
			for(int j=1;j<n-1;j++){
				int pre=array[j-1];
				if(pre>0){
					int preTmp=(int)Math.ceil(((double)pre)/b);
					count+=preTmp;
					for(int c=0;c<preTmp;c++) list.add(j+1);
					array[j-1]=pre-b*preTmp;
					array[j]-=a*preTmp;
					array[j+1]-=b*preTmp;
				}
				if(array[j-1]==0){
					count++;
					list.add(j+1);
					array[j-1]-=b;
					array[j]-=a;
					array[j+1]-=b;
				}
			}
			int last=array[n-1];
			if(last>0){
				int lastTmp=(int)Math.ceil(((double)last)/b);
				count+=lastTmp;
				for(int c=0;c<lastTmp;c++) list.add(n-1);
				array[n-1]=last-b*lastTmp;
			}
			if(array[n-1]==0){
				count++;
				list.add(n-1);
				array[n-3]-=-b;
				array[n-2]-=a;
				array[n-1]-=b;
			}
			resultCount.add(count);
			resultList.add(list);
		}
		int size = resultCount.size();
		for(int i=0;i<size;i++){
			System.out.println(resultCount.get(i));
			ArrayList<Integer> list = resultList.get(i);
			for(int j=0;j<list.size()-1;j++){
				System.out.print(list.get(j));
				System.out.print(" ");
			}
			System.out.println(list.get(list.size()-1));
		}
		
	}
}
