
import java.util.ArrayList;


public class Notifier {
    private static ArrayList listeners = null;
    private static Notifier instance = null;
    private Notifier() {
        listeners = new ArrayList<Serverlistener>();
    }
    public static synchronized Notifier getNotifier() {
        if (instance == null) {
            instance = new Notifier();
            return instance;
        }
        else return instance;
    }
    public void addlistener(Serverlistener l) {
        synchronized (listeners) {
            if (!listeners.contains(l))
                listeners.add(l);
        }
    }
    public void fireOnAccept() throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (Serverlistener) listeners.get(i)).onAccept();
    }
    public void fireOnAccepted(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (Serverlistener) listeners.get(i)).onAccepted(request);
    }
    public void fireOnRead(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ((Serverlistener) listeners.get(i)).onRead(request);
    }
    public void fireOnWrite(Request request, Response response) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (Serverlistener) listeners.get(i)).onWrite(request, response);
    }
    public void fireOnClosed(Request request) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (Serverlistener) listeners.get(i)).onClosed(request);
    }
    public void fireOnError(String msg) throws Exception {
        for (int i = listeners.size() - 1; i >= 0; i--)
            ( (Serverlistener) listeners.get(i)).onError(msg);
    }


}
