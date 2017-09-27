import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.ParsePosition;

class SimpleDateFormatTest{
  public static void main(String[] args) throws ParseException{
    String dateTimeString = "07/May/2017:21:02:10 -0400";
    System.out.println(dateTimeString);
    SimpleDateFormat simp = new SimpleDateFormat("dd/MMM/yyyy:kk:mm:ss Z");
    Date current = new Date();
    String dateString = simp.format(current);
    System.out.println(dateString);
    // ParsePosition position = new ParsePosition(1);
    Date dateTime = simp.parse(dateTimeString);
    System.out.println(simp.format(dateTime));

  }
}
