package coding.programmer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Michael
 *
 * @source 165题
 *
 *1.问题主要考察SimpeDateFormat的使用，看你是不是会
 *
 */
public class DateFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date));;
		System.out.println(date);
		
	}

}
