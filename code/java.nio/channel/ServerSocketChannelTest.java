import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Date;
import java.nio.ByteBuffer;

class ServerSocketChannelTest{
  public static void main(String[] args) throws FileNotFoundException,IOException{
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.socket().bind(new InetSocketAddress(9999));
    // serverSocketChannel.configureBlocking(false);
    while(true) {
      SocketChannel socketChannel = serverSocketChannel.accept();
      //do something with socketChannel...
        if(socketChannel != null){
         //do something with socketChannel...
         System.out.println(new Date());

         ByteBuffer buf = ByteBuffer.allocate(48);
         int bytesRead = socketChannel.read(buf); //read into buffer.
         while (bytesRead != -1) {
           buf.flip();  //make buffer ready for read
           while(buf.hasRemaining()){
             System.out.print((char) buf.get()); // read 1 byte at a time
           }
           buf.clear(); //make buffer ready for writing
           bytesRead = socketChannel.read(buf);
         }
        }
        socketChannel.close();
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
    }
    **/
  }
}
