package coding.programmer;
import java.util.ArrayList;
import java.util.Date;
/**
 * 
 * @author Michael
 *
 * @source 164题
 *
 *1.主要是注意数组循环的首尾连接
 *
 *2.数组每次删除操作时，索引会向前跃进一步，所以需要作出处理
 *
 *3.！！自己写的双循环似乎效率要高于书上的单循环！！
 *
 */
public class RemainFromLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Date().getTime());
		System.out.println("高效率版本："+toLoop(50000,9));
		System.out.println(new Date().getTime());
		System.out.println("自己写的低效率："+toLoopMine(50000,9));
		System.out.println(new Date().getTime());
	}
	
	
	//自己写的，双循环低效率版本
	private static int toLoopMine(int sumNum,int flag){
		ArrayList<Integer> loop = new ArrayList<Integer>();
		int triple=1;
		for(int num=1;num<=sumNum;num++){
			loop.add(num);
		}
		while(loop.size()>1){
			for(int i =0;i<loop.size();i++){
				if(triple%flag==0){
					loop.remove(i);
					i--;
				}
				triple++;
			}
		}
		return loop.get(0);
	}
	
	//书中借鉴，单循环提高效率
	private static int toLoop(int sumNum,int flag){
		ArrayList<Integer> loop = new ArrayList<Integer>();
		for(int num=1;num<=sumNum;num++){
			loop.add(num);
		}
		int triple=-1;	//因为下标从0开始，所以这里从-1开始
		while(loop.size()>1){
			triple = (triple+flag)%loop.size();	//取模是关键的一步，用于循环到末尾时从开头继续
			loop.remove(triple--);		//triple自减一，是因为删除元素后，索引向前了一步
		}
		return loop.get(0);
	}

}
