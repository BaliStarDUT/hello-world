import java.text.NumberFormat;
import java.math.RoundingMode;

class MathFormatTest{
    public static void main(String[] args){
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setGroupingUsed(false);
        String result = format.format(1111111);
        System.out.println(result);
    }
}
