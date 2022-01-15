package net.james.mysql.driver.utils;

import java.io.IOException;

public class LengthCodedStringReader {

    public static final String CODE_PAGE_1252 = "UTF-8";

    private String             encoding;
    private int                index          = 0;      // 数组下标

    public LengthCodedStringReader(String encoding, int startIndex){
        this.encoding = encoding;
        this.index = startIndex;
    }

    public String readLengthCodedString(byte[] data) throws IOException {
        byte[] lengthBytes = ByteHelper.readBinaryCodedLengthBytes(data, getIndex());
        long length = ByteHelper.readLengthCodedBinary(data, getIndex());
        setIndex(getIndex() + lengthBytes.length);
        if (ByteHelper.NULL_LENGTH == length) {
            return null;
        }

        try {
//            ArrayUtils.subarray(data, getIndex(), (int) (getIndex() + length))
            return new String(data,
                encoding == null ? CODE_PAGE_1252 : encoding);
        } finally {
            setIndex((int) (getIndex() + length));
        }

    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
