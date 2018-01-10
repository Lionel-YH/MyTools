import java.util.Scanner;

public class Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
        String str=null;
        while(cin.hasNextLine())
        {
            str = cin.nextLine();
            char[] array = str.toCharArray();
            String result = sortByAscii(array);
            System.out.println(compact(result));
        }        
	}
	
	public static String compact(String result){
		char[] reChar = result.toCharArray();
		int count = 0;
		int index = 0;
		char ch = reChar[0];
		String compact = new String();
		while(index<result.length()){
			if(ch==reChar[index]){
				count++;
			}
			else{
				compact+=ch;
				ch=reChar[index];
				compact+=count;
				count=1;
			}
			index++;
		}
		compact+=ch;
		compact+=count;
		return compact;
	}
	
	public static String sortByAscii(char[] array){
		int len = array.length;
		int[] ascii = new int[len];
		String result = new String();
		for(int i=0;i<len;i++){
			ascii[i] = (int)array[i];
		}
		quickSort(ascii,0,len-1);
		for(int j=0;j<len;j++){
			int asc = ascii[j];
			if((asc>=65&&asc<=90)||(asc<=122&&asc>=97)) result+=(char)asc;
		}
		return result;
	}
	
	private static void quickSort(int[] list,int start,int end){
		if(start>=end){
			return;
		}
		if(end-start==1){
			if(list[start]>list[end]) swap(list,start,end);
			return;
		}
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
			while(rear>=head&&rear>start){
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
