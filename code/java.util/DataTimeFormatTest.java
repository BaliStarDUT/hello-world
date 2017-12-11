
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.ParsePosition;

public class DataTimeFormatTest{
    public static void main(String[] args){

    }
    public Date parseData(String string)throws Exception{
        // String string = "28/Jun/2017:04:45:38 -0400";
        SimpleDateFormat simp = new SimpleDateFormat("dd/MMM/yyyy:kk:mm:ss Z");
        // Date current = new Date();
        // ParsePosition position = new ParsePosition(1);
        Date dateTime = simp.parse(string);
        return dateTime;
        // System.out.println(simp.format(dateTime));
    }

}
