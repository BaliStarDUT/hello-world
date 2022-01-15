package net.james.mysql.driver.packets.server;

import net.james.mysql.driver.packets.PacketWithHeaderPacket;
import net.james.mysql.driver.utils.LengthCodedStringReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RowDataPacket extends PacketWithHeaderPacket {

    private List<String> columns = new ArrayList<>();

    public void fromBytes(byte[] data) throws IOException {
        int index = 0;
        LengthCodedStringReader reader = new LengthCodedStringReader(null, index);
        do {
            getColumns().add(reader.readLengthCodedString(data));
        } while (reader.getIndex() < data.length);
    }

    public byte[] toBytes() throws IOException {
        return null;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getColumns() {
        return columns;
    }

    public String toString() {
        return "RowDataPacket [columns=" + columns + "]";
    }

}
