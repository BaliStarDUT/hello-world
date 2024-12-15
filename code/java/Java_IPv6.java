import java.net.Socket;
import java.net.InetSocketAddress;
import java.exception.IOException;

public class Java_IPv6{
public static void main(String [] args) {
    try {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("[${ip}]", 1113));
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

