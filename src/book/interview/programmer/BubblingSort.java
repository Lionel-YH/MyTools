package book.interview.programmer;

import java.util.ArrayList;

/**
 * 
 * @author Michael
 *
 * @source 166题
 * 
 * 1.注意缕清冒泡的过程，是两个指针同时向后/前走，而不是单个指针（单个指针的好像是选择排序）
 * 
 * 2.两个循环的边界需要考虑清楚，容易出现数组越界异常
 *
 */
public class BubblingSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = createList(100);
		System.out.println("排序前：");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+"\t");
			}
		System.out.println();
		bubblingSort(array);
		System.out.println("排序后：");
		for(int i=0;i<array.length;i++){
		System.out.print(array[i]+"\t");
		}
		System.out.println();
	}
	
	private static int[] createList(int sum){
		int[] array = new int[sum];
		for(int i=0;i<sum;i++){
			array[i]=(int) Math.round(Math.random()*100);
		}
		return array;
	}
	
	private static void bubblingSort(int[] array){
		for(int i=array.length-1;i>0;i--){
			for(int j=0;j<i;j++){
				if(array[j]>array[j+1]) swap(array,j,j+1);
			}
		}
	}
	
	private static void swap(int[] array,int i,int j){
		int tmp=array[i];
		array[i]=array[j];
		array[j]=tmp;
	}

}
