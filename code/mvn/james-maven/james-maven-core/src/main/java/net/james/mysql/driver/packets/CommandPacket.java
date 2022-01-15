package net.james.mysql.driver.packets;

public abstract class CommandPacket implements IPacket {

    private byte command;

    // arg

    public void setCommand(byte command) {
        this.command = command;
    }

    public byte getCommand() {
        return command;
    }

    public String toString() {
        return this.toString();
    }
}
