package net.james.mysql.driver.utils;

import net.james.mysql.driver.packets.HeaderPacket;
import net.james.mysql.driver.packets.client.BinlogDumpCommandPacket;

import java.util.Objects;

public class BinlogDumpCommandBuilder {

    public BinlogDumpCommandPacket build(String binglogFile, long position, long slaveId) {
        BinlogDumpCommandPacket command = new BinlogDumpCommandPacket();
        command.binlogPosition = position;
        if (!Objects.isNull(binglogFile)) {
            command.binlogFileName = binglogFile;
        }
        command.slaveServerId = slaveId;
        // end settings.
        return command;
    }

//    public ChannelBuffer toChannelBuffer(BinlogDumpCommandPacket command) throws IOException {
//        byte[] commandBytes = command.toBytes();
//        byte[] headerBytes = assembleHeaderBytes(commandBytes.length);
//        ChannelBuffer buffer = ChannelBuffers.wrappedBuffer(headerBytes, commandBytes);
//        return buffer;
//    }

    private byte[] assembleHeaderBytes(int length) {
        HeaderPacket header = new HeaderPacket();
        header.setPacketBodyLength(length);
        header.setPacketSequenceNumber((byte) 0x00);
        return header.toBytes();
    }
}
