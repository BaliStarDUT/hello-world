package net.yang;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.jcraft.jsch.Channel;
import net.yang.util.ThreadHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.*;
import java.nio.channels.Channels;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * author: yang
 * datetime: 2021/3/14 0:39
 */


@Component
@Scope("prototype")
public class NodeShell {

    private static Logger logger = LoggerFactory.getLogger(NodeShell.class);

/**
 * This program enables you to connect to sshd server and get the shell prompt.
 * You will be asked username, hostname and passwd.
 * If everything works fine, you will get the shell prompt. Output may
 * be ugly because of lacks of terminal-emulation, but you can issue commands.
 */


    public ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[1]);

    public ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1);

    private BufferedReader inputReader;

    private BufferedWriter outputWriter;

    private LinkedBlockingQueue<String> cmd_queue = new LinkedBlockingQueue<String>();

    private WebSocketSession webSocketSession;

    private Channel channel;

    private  Session session;

    private static final String USER="root";
    private static final String PASSWORD="yang";
    private static final String HOST="192.168.198.129";
    private static final int DEFAULT_SSH_PORT=22;

    public  void init_ssh(){
        try{
            JSch jsch=new JSch();
            session = jsch.getSession(USER,HOST,DEFAULT_SSH_PORT);
            session.setPassword(PASSWORD);

            UserInfo userInfo = new UserInfo() {
                @Override
                public String getPassphrase() {
                    System.out.println("getPassphrase");
                    return null;
                }
                @Override
                public String getPassword() {
                    System.out.println("getPassword");
                    return null;
                }
                @Override
                public boolean promptPassword(String s) {
                    System.out.println("promptPassword:"+s);
                    return false;
                }
                @Override
                public boolean promptPassphrase(String s) {
                    System.out.println("promptPassphrase:"+s);
                    return false;
                }
                @Override
                public boolean promptYesNo(String s) {
                    System.out.println("promptYesNo:"+s);
                    return true;//notice here!
                }
                @Override
                public void showMessage(String s) {
                    System.out.println("showMessage:"+s);
                }
            };

            session.setUserInfo(userInfo);

            // It must not be recommended, but if you want to skip host-key check,
            // invoke following,
            // session.setConfig("StrictHostKeyChecking", "no");

            //session.connect();
            session.connect(30000);   // making a connection with timeout.

            this.channel=session.openChannel("shell");

            // Enable agent-forwarding.
            //((ChannelShell)channel).setAgentForwarding(true);

//                channel.setInputStream(System.in);
//            channel.setInputStream(byteArrayInputStream);
//            channel.getInputStream();
//            channel.getOutputStream();
            this.inputReader = new BufferedReader(new InputStreamReader(
                    channel.getInputStream(),"UTF-8"
            ));

            this.outputWriter = new BufferedWriter(new OutputStreamWriter(
                    channel.getOutputStream(),"UTF-8"
            ));

            ThreadHelper.start(new Runnable() {
                @Override
                public void run() {
                    printReader(inputReader);
                }
            });


//            channel.setOutputStream(byteArrayOutputStream);

  /*
  // Choose the pty-type "vt102".
  ((ChannelShell)channel).setPtyType("vt102");
  */

  /*
  // Set environment variable "LANG" as "ja_JP.eucJP".
  ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
  */

            //channel.connect();
            channel.connect(3*1000);
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void cmd_to_node(String cmd){
        try {
            if(Objects.isNull(cmd)){
                return;
            }
            cmd_queue.put(cmd);
            ThreadHelper.start(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(outputWriter==null){
                            outputWriter =  new BufferedWriter(new OutputStreamWriter(
                                    channel.getOutputStream(),"UTF-8"
                            ));
                        }
                        outputWriter.write(cmd_queue.poll());
                        outputWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void close_node_shell() throws IOException {
        this.channel.disconnect();
        session.disconnect();
        this.inputReader.close();
        this.outputWriter.close();
    }

    private void printReader(BufferedReader bufferedReader){
        int nRead;
        char[] data = new char[1024];
        try {
            while ((nRead = bufferedReader.read(data,0,data.length))!= -1){
                StringBuilder builder = new StringBuilder(nRead);
                builder.append(data,0,nRead);
                webSocketSession.sendMessage(new TextMessage(builder.toString()));
            }
        }catch (IOException e){
            logger.error(e.getMessage(),e);
        }
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }
}
