package book.interview.programmer;
/**
 * 
 * @author Lionel
 *
 * @source 161题
 * 
 * 1.循环没必要从2到头，可以证明，只要循环到平方根，就可保证是素数
 * 
 * 2.取平方根应当提前到循环之前，这样就不会每次循环都计算一次平方根
 *
 */

public class PrimeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int num=2;num<=1000;num++){
			if(isPrimeNumber(num)){
				System.out.println(num);
				count++;
			}
		}
		System.out.println(count);
	}
	
	private static boolean isPrimeNumber(Integer num){
//		for(int i=2;i<=Math.sqrt(num);i++){
		long sqrtNum = (long)Math.sqrt(num);
		for(int i=2;i<=sqrtNum;i++){
			if(num%i==0){
				return false;
			}
		}
		return true;
	}
}