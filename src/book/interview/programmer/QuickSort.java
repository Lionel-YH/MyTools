package book.interview.programmer;
import java.util.ArrayList;
/**
 * 
 * @author lionel
 *
 * @source 168题
 *
 *1.数字一多，就栈溢出，有时间研究一下这个问题；
 *
 *2.在判断循环边界的时候要格外注意取不取等；
 *
 *3.注意要有递归退出条件；
 *
 *4.!!!千万要注意，每次快排后一定有一个Middle元素被固定，递归的范围不包含Middle元素，
 *	不然会出现堆栈溢出情况，具体为什么有时间研究一下！！！
 *
 */
public class QuickSort {
	static int sortCount=0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] list;
		list = createArray(100,100);
		System.out.println("排序前：");
		for(Integer number:list){
			System.out.println(number);
		}
		quickSort(list,0,list.length-1);
		System.out.println("排序后：");
		for(Integer number:list){
			System.out.println(number);
		}
	}
	
	private static int[] createArray(int num,int range){
		int[] list = new int[num];
		for(int i=0;i<num;i++){
			list[i]=(int)(Math.random()*range);
		}
		return list;
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
