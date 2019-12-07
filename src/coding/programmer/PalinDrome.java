package coding.programmer;
/**
 * 
 * @author Michael
 *
 * @source 162题
 *
 *1.主要思路是避免通过繁复的字符串转换达到目的，因为转换效率低
 *
 *2.通过对每一个数字的取模计算技巧，同样可以达到翻转数字的效果
 *
 */
public class PalinDrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=11;i<=9999;i++){
			if(isPalinDrome(i)){
				System.out.println(i);
			}
		}
	}
	
	private static boolean isPalinDrome(Integer num){
		Integer old=num;
		Integer tmp = 0;
		while(num>0){
			tmp=tmp*10+num%10;
			num/=10;
		}
		return tmp.equals(old);
	}

}
