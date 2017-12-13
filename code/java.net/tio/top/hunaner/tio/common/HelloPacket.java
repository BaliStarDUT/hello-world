package top.hunaner.tio.common;

import org.tio.core.intf.Packet;

/**
*javac -cp .:/Users/aliyun/Documents/GitHub/t-io/dist/examples/helloworld/server/lib/tio-core-1.7.0.1.v20170601-RELEASE.jar top/hunaner/tio/common/HelloPacket.java
*/
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
