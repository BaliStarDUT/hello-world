package reader;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

public class DataTimeFormatTest{
    public static void main(String[] args) throws Exception{
        String string = "28/Jun/2017:04:45:38 -0400";
        Date date = DataTimeFormatTest.parseData(string);
        System.out.println(date.toString());
    }
    public static Date parseData(String string)throws Exception{
        // System.out.println(string);
        SimpleDateFormat simp = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);
        // Date current = new Date();
        // ParsePosition position = new ParsePosition(1);
        Date dateTime = simp.parse(string);
        return dateTime;
        // System.out.println(simp.format(dateTime));
    }
    public static Date parseDateTime(String string)throws Exception{
        // System.out.println(string);
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-mm-ddHH:mm:ss",Locale.US);
        // Date current = new Date();
        // ParsePosition position = new ParsePosition(1);
        Date dateTime = simp.parse(string);
        return dateTime;
        // System.out.println(simp.format(dateTime));
    }

}
