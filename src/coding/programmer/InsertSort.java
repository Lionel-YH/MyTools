package coding.programmer;
import java.util.ArrayList;
/**
 * 
 * @author lionel
 *
 * @source 167题
 * 
 * 1.在Math.random()取值全是小于1的，转化int型则全是0，需要注意使用小括号把后面的放大系数包括进去
 * 
 * 2.这里写的方法和书上不一样，在进行数组插入时，因为凭空多了一个元素，故需要在相应的地方删除重复项
 * 
 * 3.插入法内部循环应从后往前
 *
 */
public class InsertSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list;
		list = createList(20);
		System.out.println("排序前：");
		for(Integer number:list){
			System.out.println(number);
		}
		insertSort(list);
		System.out.println("排序后：");
		for(Integer number:list){
			System.out.println(number);
		}
	}
	
	private static ArrayList<Integer> createList(int num){
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<num;i++){
			list.add((int)(Math.random()*num));
		}
		return list;
	}
	
	//插入法从小到大排序
	private static void insertSort(ArrayList<Integer> list){
		for(int i=0;i<list.size();i++){
			for(int j=0;j<i;j++){
				if(list.get(i)<list.get(j)){
					list.add(j, list.get(i));
					list.remove(i+1);
				}
			}
		}
	}
}
