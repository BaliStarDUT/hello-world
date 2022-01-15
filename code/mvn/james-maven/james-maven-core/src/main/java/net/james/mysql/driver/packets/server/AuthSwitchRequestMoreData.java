package net.james.mysql.driver.packets.server;

import net.james.mysql.driver.packets.CommandPacket;
import net.james.mysql.driver.utils.ByteHelper;

import java.io.IOException;

public class AuthSwitchRequestMoreData extends CommandPacket {

    public int    status;
    public byte[] authData;

    public void fromBytes(byte[] data) {
        int index = 0;
        // 1. read status
        status = data[index];
        index += 1;
        authData = ByteHelper.readNullTerminatedBytes(data, index);
    }

    public byte[] toBytes() throws IOException {
        return null;
    }

}
