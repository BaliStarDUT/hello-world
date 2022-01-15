package net.james.mysql.driver.packets;

public abstract class PacketWithHeaderPacket implements IPacket {

    protected HeaderPacket header;

    protected PacketWithHeaderPacket(){
    }

    protected PacketWithHeaderPacket(HeaderPacket header){
        setHeader(header);
    }

    public void setHeader(HeaderPacket header) {
        this.header = header;
    }

    public HeaderPacket getHeader() {
        return header;
    }

    public String toString() {
        return this.toString();
    }

}
