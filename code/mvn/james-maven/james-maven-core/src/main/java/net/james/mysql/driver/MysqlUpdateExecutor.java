package net.james.mysql.driver;

import net.james.mysql.driver.packets.client.QueryCommandPacket;
import net.james.mysql.driver.packets.server.ErrorPacket;
import net.james.mysql.driver.packets.server.OKPacket;
import net.james.mysql.driver.utils.PacketManager;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * 默认输出的数据编码为UTF-8，如有需要请正确转码
 * 
 * @author jianghang 2013-9-4 上午11:51:11
 * @since 1.0.0
 */
public class MysqlUpdateExecutor {

    private static final Logger logger = Logger.getLogger(MysqlUpdateExecutor.class.getName());

    private MysqlConnector      connector;

    public MysqlUpdateExecutor(MysqlConnector connector) throws IOException{
        if (!connector.isConnected()) {
            throw new IOException("should execute connector.connect() first");
        }

        this.connector = connector;
    }

    /*
     * public MysqlUpdateExecutor(SocketChannel ch){ this.channel = ch; }
     */

    public OKPacket update(String updateString) throws IOException {
        QueryCommandPacket cmd = new QueryCommandPacket();
        cmd.setQueryString(updateString);
        byte[] bodyBytes = cmd.toBytes();
        PacketManager.writeBody(connector.getChannel(), bodyBytes);

        logger.info("read update result...");
        byte[] body = PacketManager.readBytes(connector.getChannel(),
            PacketManager.readHeader(connector.getChannel(), 4).getPacketBodyLength());
        if (body[0] < 0) {
            ErrorPacket packet = new ErrorPacket();
            packet.fromBytes(body);
            throw new IOException(packet + "\n with command: " + updateString);
        }

        OKPacket packet = new OKPacket();
        packet.fromBytes(body);
        return packet;
    }
}
