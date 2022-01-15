package net.james.mysql.driver.packets.client;

import net.james.mysql.driver.packets.CommandPacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * quit cmd
 * 
 * @author agapple 2016年3月1日 下午8:33:02
 * @since 1.0.22
 */
public class QuitCommandPacket extends CommandPacket {

    public QuitCommandPacket(){
        setCommand((byte) 0x01);
    }

    @Override
    public void fromBytes(byte[] data) throws IOException {

    }

    @Override
    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(getCommand());
        return out.toByteArray();
    }

}
