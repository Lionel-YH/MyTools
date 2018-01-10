package book.interview.programmer;
/**
 * 
 * @author Lionel
 *
 * @source 160题
 * 
 * 1.提高效率的主要思路是将双循环转换为单循环
 * 
 * 2.判断换行时，列应当置为0而不是1，因为循环条件会使col++；
 *
 */
public class MultiplicationTable {

	
	//最简单的写法：
	public static void main_1(String[] args) {
		// TODO Auto-generated method stub
		for(int row=1;row<=9;row++){
			for(int col=1;col<=row;col++){
				System.out.print(row+"*"+col+"="+row*col);
				System.out.print("\t");
			}
			System.out.println("");
		}
	}
	
	//单循环高效率写法：
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int row=1,col=1;row<=9;col++){
			System.out.print(row+"*"+col+"="+row*col+"\t");
			if(col==row){
				System.out.println("");
				col=0;
				row++;
			}
		}
	}

}
