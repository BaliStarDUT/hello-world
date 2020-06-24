import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.Date;

class FileChannelTest{
  public static void main(String[] args) throws FileNotFoundException,IOException{
    RandomAccessFile fromFile = new RandomAccessFile("../../kubernetes/deployment.yml", "rw");
    FileChannel      fromChannel = fromFile.getChannel();

    RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
    FileChannel      toChannel = toFile.getChannel();

    long position = 0;
    long count    = fromChannel.size();

    toChannel.transferFrom(fromChannel, position, count);
    fromChannel.close();
    toChannel.close();
  }
}
