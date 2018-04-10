package coding.acmcoder;

import java.util.ArrayList;
import java.util.Scanner;

public class ChaJiGe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		ArrayList<String> input = new ArrayList<String>();
		int n=in.nextInt();
		int[] nums=new int[n];
		for(int i=0;i<n;i++){
			nums[i] = in.nextInt();
		}
		quickSort(nums,0,nums.length-1);
		ArrayList<Integer> index=new ArrayList<Integer>();
		for(int i=0;i<n-1;i++){
			if((nums[i+1]-nums[i])>10) index.add(i+1);
		}
		System.out.println(judgeNum(index,nums,n));
	}
	
	private static int judgeNum(ArrayList<Integer> index,int[] nums,int n){
		int sum=n;
		int count=0;
		for(int i=0;i<index.size();i++){
			if((index.get(i)+count)%3==0)
			count+=(nums[index.get(i)]-nums[index.get(i-1)])/10;
		}
		if((count+sum)%3==0) return count;
		int di=(count+sum)%3;
		return di==1?count+2:count+1;
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
}
