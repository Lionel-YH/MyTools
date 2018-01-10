import java.util.ArrayList;
import java.util.Scanner;

public class Main_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		String inStr = null;
		Scanner in = new Scanner(System.in);
        inStr = in.nextLine();
        String[] strList = inStr.split(",");
        for(String each:strList){
        	list.add(Integer.valueOf(each));
        }
        quickSort(list,0,list.size()-1);
        campactArray(list);
        for(int i=0;i<list.size()-1;i++){
        	System.out.print(list.get(i));
			System.out.print(" ");
        }
        System.out.print(list.get(list.size()-1));
	}
	
	private static void campactArray(ArrayList<Integer> list){
		int head = 0;
		int rear = 0;
		int lastIndex = list.size()-1;
		while(head<lastIndex){		
			int preNum = list.get(head);
			for(int i=head+1;i<=lastIndex;i++){
				int tmp = list.get(i);
				if((tmp-preNum)>1){
					delete(list,head,rear);
					lastIndex = list.size()-1;
					head = head+2;
					rear = head;
					break;
				}
				else if(0==(tmp-preNum)&&head==rear){
					head = i;
					rear = i;
					preNum = tmp;
				}
				else if(0==(tmp-preNum)){
					delete(list,head,rear);
					lastIndex = list.size()-1;
					head = head+2;
					rear = head;
					break;
				}
				else{
					rear = i;
					preNum = tmp;
				}
			}
		}
		
	}
	
	private static void delete(ArrayList<Integer> list,int from,int end){
		for(int i=0;i<end-from-1;i++){
			list.remove(from+1);
		}
	}
	
	private static void quickSort(ArrayList<Integer> list,int start,int end){
		if(start>=end){
			return;
		}
		if(end-start==1){
			if(list.get(start)>list.get(end)) swap(list,start,end);
			return;
		}
		int pivot = list.get(start);
		int head=start+1;
		int rear=end;
		while(head<rear){
			while(head<rear){
				if(list.get(head)<pivot){
					head++;
				}else{
					break;
				}
			}
			while(rear>=head){
				if(list.get(rear)>=pivot){
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
	
	private static void swap(ArrayList<Integer> list,int first,int second){
		int tmp=list.get(first);
		list.set(first, list.get(second));
		list.set(second, tmp);
	}

}
