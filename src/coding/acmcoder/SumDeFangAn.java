package coding.acmcoder;

import java.util.Scanner;

public class SumDeFangAn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int sum = sc.nextInt();
		int[] array = new int[n];
		for(int i=0;i<n;i++){
			array[i] = sc.nextInt();
		}
		quickSort(array,0,n-1);
		System.out.println(findContinuesSequence(array,sum));
	}
	
	private static void quickSort(int[] list,int start,int end){
		if(start>=end){
			return;
		}
		if(end-start==1){
			if(list[start]>list[end]) swap(list,start,end);
			return;
		}
//		int locate = (int)(Math.random()*(end-start));
		int pivot = list[start];
		int head=start+1;
		int rear=end;
		while(head<rear){
			while(head<rear){
				if(list[head]<pivot){
					head++;
				}else{
					break;
				}
			}
			while(rear>=head){
				if(list[rear]>=pivot){
					rear--;
				}else{
					break;
				}
			}
			if(head<rear){
				swap(list,head,rear);
			}
		}
		swap(list,start,rear);
		quickSort(list,start,rear-1);
		quickSort(list,rear+1,end);
	}
	
	private static void swap(int[] list,int first,int second){
		int tmp=list[first];
		list[first]=list[second];
		list[second]=tmp;
	}
	
	public static int findContinuesSequence(int[] array,int sum){
		int[] tmp=array;
		int count=0;
		if(sum<2) return 0;
		int small=0;
		int big=1;
		int len=tmp.length;
		while(small<len-1&&big<len){
			int curSum=0;
			for(int i=small;i<=big;i++) curSum+=tmp[i];
			if(curSum>sum){
				small++;
				continue;
			}
			if(curSum<sum){
				big++;
				continue;
			}
			if(curSum==sum){
				count++;
				big++;
				continue;
			}		
			}
		return count;
	}
		
}
