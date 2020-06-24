
public class ServerHandler extends EventAdapter{
    public ServerHandler() {
    }

    public void onAccept() throws Exception {
        System.out.println("#onAccept()");
    }

    public void onAccepted(Request request) throws Exception {
        System.out.println("#onAccepted()");
    }

//    public void onRead(Request request) throws Exception {
//        byte[] rspData = data;
//        if (new String (data).equalsIgnoreCase("query")) {
//            rspData = new java.util.Date().toString().getBytes();
//        }
//        request.attach(rspData);
//        System.out.println("#onRead()");
//    }
    public void onRead(Request request) throws Exception {
        System.out.println("Received: " + new String(request.attachment().toString()));
    }
    public void onWrite(Request request, Response response) throws Exception {
        System.out.println("#onWrite()");
        response.send((byte[])request.attachment());
        response.send("OK".getBytes());
    }
    public void onClosed(Request request) throws Exception {
        //System.out.println("#onClosed()");
    }

    public void onError(String error) {
        System.out.println("#onAError(): " + error);
    }
}
