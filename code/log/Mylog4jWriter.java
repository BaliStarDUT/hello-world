

import java.io.IOException;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;

import org.apache.log4j.DailyRollingFileAppender;

public class Mylog4jWriter extends DailyRollingFileAppender{
  @Override
  public synchronized void setFile(String fileName,boolean append,boolean bufferedIO,int bufferSize) throws IOException{
    super.setFile(fileName,append,bufferedIO,bufferSize);
    File f = new File(fileName);
        Set<PosixFilePermission> set = new HashSet<PosixFilePermission>();
        set.add(PosixFilePermission.OWNER_READ);
        set.add(PosixFilePermission.OTHERS_WRITE);
        set.add(PosixFilePermission.GROUP_READ);
        set.add(PosixFilePermission.OTHERS_READ);
        if(f.exists()){
            Files.setPosixFilePermissions(f.toPath(), set);
        }
  }
}
