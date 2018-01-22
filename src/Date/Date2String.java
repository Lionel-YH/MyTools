package Date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date2String {

    public static void main(String[] args) {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Hello World!");
        System.out.println(dateFormat.format(today));
    }
}
