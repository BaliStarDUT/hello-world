import java.util.Date;

public class LogHandler extends EventAdapter {
    public LogHandler() {
    }
    public void onClosed(Request request) throws Exception {
        String log = new Date().toString() + " from " + request.getAddress()
                .toString()+Thread.currentThread().getName();
        System.out.println(log);
    }
    public void onError(String error) {
        System.out.println("Error: " + error);
    }
}
