import com.pty4j.PtyProcess;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * author: yang
 * datetime: 2019/5/8 10:29
 */

public class PtyMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        // The command to run in a PTY...
        String[] cmd = { "/bin/sh", "-l" };
// The initial environment to pass to the PTY child process...
        String[] env = { "TERM=xterm" };

        PtyProcess pty = PtyProcess.exec(cmd, env);

        OutputStream os = pty.getOutputStream();
        InputStream is = pty.getInputStream();

// ... work with the streams ...

// wait until the PTY child process terminates...
        int result = pty.waitFor();

// free up resources.
//        pty.close();
    }
}
