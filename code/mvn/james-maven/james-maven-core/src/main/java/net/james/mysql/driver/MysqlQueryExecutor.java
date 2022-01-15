package net.james.mysql.driver;

import net.james.mysql.driver.packets.HeaderPacket;
import net.james.mysql.driver.packets.client.QueryCommandPacket;
import net.james.mysql.driver.packets.server.*;
import net.james.mysql.driver.socket.SocketChannel;
import net.james.mysql.driver.utils.PacketManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认输出的数据编码为UTF-8，如有需要请正确转码
 * 
 * @author jianghang 2013-9-4 上午11:50:26
 * @since 1.0.0
 */
public class MysqlQueryExecutor {

    private SocketChannel channel;

    public MysqlQueryExecutor(MysqlConnector connector) throws IOException{
        if (!connector.isConnected()) {
            throw new IOException("should execute connector.connect() first");
        }

        this.channel = connector.getChannel();
    }

    public MysqlQueryExecutor(SocketChannel ch){
        this.channel = ch;
    }

    /**
     * (Result Set Header Packet) the number of columns <br>
     * (Field Packets) column descriptors <br>
     * (EOF Packet) marker: end of Field Packets <br>
     * (Row Data Packets) row contents <br>
     * (EOF Packet) marker: end of Data Packets
     * 
     * @param queryString
     * @return
     * @throws IOException
     */
    public ResultSetPacket query(String queryString) throws IOException {
        QueryCommandPacket cmd = new QueryCommandPacket();
        cmd.setQueryString(queryString);
        byte[] bodyBytes = cmd.toBytes();
        PacketManager.writeBody(channel, bodyBytes);
        byte[] body = readNextPacket();

        if (body[0] < 0) {
            ErrorPacket packet = new ErrorPacket();
            packet.fromBytes(body);
            throw new IOException(packet + "\n with command: " + queryString);
        }

        ResultSetHeaderPacket rsHeader = new ResultSetHeaderPacket();
        rsHeader.fromBytes(body);

        List<FieldPacket> fields = new ArrayList<>();
        for (int i = 0; i < rsHeader.getColumnCount(); i++) {
            FieldPacket fp = new FieldPacket();
            fp.fromBytes(readNextPacket());
            fields.add(fp);
        }

        readEofPacket();

        List<RowDataPacket> rowData = new ArrayList<>();
        while (true) {
            body = readNextPacket();
            if (body[0] == -2) {
                break;
            }
            RowDataPacket rowDataPacket = new RowDataPacket();
            rowDataPacket.fromBytes(body);
            rowData.add(rowDataPacket);
        }

        ResultSetPacket resultSet = new ResultSetPacket();
        resultSet.getFieldDescriptors().addAll(fields);
        for (RowDataPacket r : rowData) {
            resultSet.getFieldValues().addAll(r.getColumns());
        }
        resultSet.setSourceAddress(channel.getRemoteSocketAddress());

        return resultSet;
    }

    public List<ResultSetPacket> queryMulti(String queryString) throws IOException {
        QueryCommandPacket cmd = new QueryCommandPacket();
        cmd.setQueryString(queryString);
        byte[] bodyBytes = cmd.toBytes();
        PacketManager.writeBody(channel, bodyBytes);
        List<ResultSetPacket> resultSets = new ArrayList<>();
        boolean moreResult = true;
        while (moreResult) {
            byte[] body = readNextPacket();
            if (body[0] < 0) {
                ErrorPacket packet = new ErrorPacket();
                packet.fromBytes(body);
                throw new IOException(packet + "\n with command: " + queryString);
            }

            ResultSetHeaderPacket rsHeader = new ResultSetHeaderPacket();
            rsHeader.fromBytes(body);

            List<FieldPacket> fields = new ArrayList<>();
            for (int i = 0; i < rsHeader.getColumnCount(); i++) {
                FieldPacket fp = new FieldPacket();
                fp.fromBytes(readNextPacket());
                fields.add(fp);
            }

            moreResult = readEofPacket();

            List<RowDataPacket> rowData = new ArrayList<>();
            while (true) {
                body = readNextPacket();
                if (body[0] == -2) {
                    break;
                }
                RowDataPacket rowDataPacket = new RowDataPacket();
                rowDataPacket.fromBytes(body);
                rowData.add(rowDataPacket);
            }

            ResultSetPacket resultSet = new ResultSetPacket();
            resultSet.getFieldDescriptors().addAll(fields);
            for (RowDataPacket r : rowData) {
                resultSet.getFieldValues().addAll(r.getColumns());
            }
            resultSet.setSourceAddress(channel.getRemoteSocketAddress());
            resultSets.add(resultSet);
        }

        return resultSets;
    }

    private boolean readEofPacket() throws IOException {
        byte[] eofBody = readNextPacket();
        EOFPacket packet = new EOFPacket();
        packet.fromBytes(eofBody);
        if (eofBody[0] != -2) {
            throw new IOException("EOF Packet is expected, but packet with field_count=" + eofBody[0] + " is found.");
        }

        return (packet.statusFlag & 0x0008) != 0;
    }

    protected byte[] readNextPacket() throws IOException {
        HeaderPacket h = PacketManager.readHeader(channel, 4);
        return PacketManager.readBytes(channel, h.getPacketBodyLength());
    }
}
