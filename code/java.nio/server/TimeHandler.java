import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeHandler extends EventAdapter{
    public TimeHandler() {
    }
    public void onWrite(Request request, Response response) throws Exception {
        String command = new String(request.getDataInput());
        String time = null;
        Date date = new Date();
        // 判断查询命令
        if (command.equals("GB")) {
            // 中文格式
            DateFormat cnDate = DateFormat.getDateTimeInstance(DateFormat.LONG,
                    DateFormat.LONG, Locale.CHINA);
            time = cnDate.format(date);
        }
        else {
            // 英文格式
            DateFormat enDate = DateFormat.getDateTimeInstance(DateFormat.LONG,
                    DateFormat.LONG, Locale.US);
            time = enDate.format(date);
        }
        response.send((time+Thread.currentThread().getName()).getBytes());
    }
}
