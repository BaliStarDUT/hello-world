import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public final class Writer extends Thread {
    private static List pool = new LinkedList();
    private static Notifier notifier = Notifier.getNotifier();

    public Writer() {
    }
    public void run() {
        while (true) {
            try {
                SelectionKey key;
                synchronized (pool) {
                    while (pool.isEmpty()) {
                        pool.wait();
                    }
                    key = (SelectionKey) pool.remove(0);
                }
                // 向客户端发送数据，然后关闭连接，并分别触发 onWrite，onClosed 事件
                write(key);
            } catch (Exception e) {
                continue;
            }
        }
    }
    public void write(SelectionKey key) throws Exception {
        try {
            SocketChannel sc = (SocketChannel) key.channel();
            Response response = new Response(sc);

            notifier.fireOnWrite((Request)key.attachment(), response);

            sc.finishConnect();
            sc.socket().close();
            sc.close();

            notifier.fireOnClosed((Request)key.attachment());
        }
        catch (Exception e) {
            notifier.fireOnError("Error occured in Writer: " + e.getMessage());
        }
    }
    public static void processRequest(SelectionKey key) {
        synchronized (pool) {
            pool.add(pool.size(), key);
            pool.notifyAll();
        }
    }
}
