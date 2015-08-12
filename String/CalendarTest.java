import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    
    public static void main(String[] args) {
		// 创建Canlendar对象
		Calendar c = Calendar.getInstance();
        
		// 将Calendar对象转换为Date对象
		Date date = c.getTime();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		System.out.println("当前时间："+year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second);
		// 创建SimpleDateFormat对象，指定目标格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		// 将日期转换为指定格式的字符串
		String now = sdf.format(date);
		System.out.println("当前时间：" + now);
	}
}
