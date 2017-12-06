package top.hunaner.tio.common;

import org.tio.core.intf.Packet;

public class HelloPacket extends Packet{
  public static final int Header_Lenghth = 4;
  public static final String CHARSET = "utf-8";
  private byte[] body;

  public byte[] getBody(){
      return body;
  }
  public void setBody(byte[] body){
    this.body = body;
  }
}
