
public abstract class EventAdapter implements Serverlistener{
    public EventAdapter() {
    }
    public void onError(String error) {}
    public void onAccept() throws Exception {}
    public void onAccepted(Request request)  throws Exception {}
    public void onRead(Request request)  throws Exception {}
    public void onWrite(Request request, Response response)  throws Exception {}
    public void onClosed(Request request)  throws Exception {}
}
