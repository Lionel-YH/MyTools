package coding.programmer;

import java.util.Date;

/**
 * @source 163题
 */

public class TheNextDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date today = new Date();
		System.out.println(toNextXDate(today, 2));
	}
	
	private static Date toNextXDate(Date fromDate,Integer days){
		long addMills=1;
		addMills*=days;
		addMills*=24;
		addMills*=60;
		addMills*=60;
		addMills*=1000;
		Date toDate = new Date(fromDate.getTime()+addMills);
		return toDate;
	}
}
