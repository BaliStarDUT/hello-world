package top.hunaner.tio.common;

import java.io.IOException;

import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.server.ServerGroupContext;
import org.tio.server.AioServer;
import org.tio.core.intf.Packet;
import org.tio.core.Aio;
import org.tio.core.exception.AioDecodeException;
import top.hunaner.tio.common.HelloPacket;

public class HelloServerStarter {
    public static ServerAioHandler aioHandler = new HelloServerAioHandler();
	public static ServerAioListener aioListener = null;
	public static ServerGroupContext serverGroupContext = new ServerGroupContext(aioHandler, aioListener);
	public static AioServer aioServer = new AioServer(serverGroupContext);
	public static String serverIp = Const.SERVER;
	public static int serverPort = Const.PORT;
	public static void main(String[] args) throws IOException {
		serverGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
		aioServer.start(serverIp, serverPort);
  }
}
