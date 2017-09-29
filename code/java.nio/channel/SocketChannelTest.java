import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

import java.nio.channels.SocketChannel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Date;
import java.nio.ByteBuffer;


class SocketChannelTest{
  public static void main(String[] args) throws FileNotFoundException,IOException{
    SocketChannel socketChannel = SocketChannel.open();
    socketChannel.connect(new InetSocketAddress("localhost", 9999));
    String newData = "New String to write to file..." + System.currentTimeMillis();

    ByteBuffer buf = ByteBuffer.allocate(48);
    buf.clear();
    buf.put(newData.getBytes());

    buf.flip();

    while(buf.hasRemaining()) {
        socketChannel.write(buf);
    }
    /**
    Selector selector = Selector.open();

    fromChannel.configureBlocking(false);

    SelectionKey key = fromChannel.register(selector, SelectionKey.OP_READ);

    while(true) {

      int readyChannels = selector.select();

      if(readyChannels == 0) continue;

      Set<SelectionKey> selectedKeys = selector.selectedKeys();

      Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

      while(keyIterator.hasNext()) {

        SelectionKey key = keyIterator.next();

        if(key.isAcceptable()) {
            // a connection was accepted by a ServerSocketChannel.
            System.out.println(new Date());
        } else if (key.isConnectable()) {
            // a connection was established with a remote server.
            System.out.println(new Date());

        } else if (key.isReadable()) {
            // a channel is ready for reading
            System.out.println(new Date());

        } else if (key.isWritable()) {
            // a channel is ready for writing
            System.out.println(new Date());
        }

        keyIterator.remove();
      }
    }**/
  }
}
