package top.hunaner.tio.common;

import java.nio.ByteBuffer;
import org.tio.server.intf.ServerAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.intf.Packet;

import org.tio.core.exception.AioDecodeException;
import top.hunaner.tio.common.HelloPacket;

public class HelloServerAioHandler implements ServerAioHandler{

  @Override
  public HelloPacket decode(ByteBuffer buffer, ChannelContext channelContext) throws AioDecodeException{
      HelloPacket packet = new HelloPacket();
      return packet;
  }
  @Override
  public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext){
    ByteBuffer buffer = new ByteBuffer();
    return buffer;
  }

  @Override
  public Object handler(Packet packet, ChannelContext channelContext) throws Exception{
      HelloPacket helloPacket = (HelloPacket) packet;
  		byte[] body = helloPacket.getBody();
  		if (body != null) {
  			String str = new String(body, HelloPacket.CHARSET);
  			System.out.println("收到消息：" + str);

  			HelloPacket resppacket = new HelloPacket();
  			resppacket.setBody(("收到了你的消息，你的消息是:" + str).getBytes(HelloPacket.CHARSET));
  			// Aio.send(channelContext, resppacket);
  		}
  		return "";
  }

}
