package retrogdx.games.earth2140.readers;

import retrogdx.utils.SmartByteBuffer;

import java.nio.ByteOrder;

public class DatImage {
    public byte[] pixels;
    public int width;
    public int height;

    public DatImage(SmartByteBuffer buffer) {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.position(0);

        this.width = buffer.readUShort();
        this.height = buffer.readUShort();
        int unk = buffer.readUShort(); // TODO This contains some information
        this.pixels = buffer.readBytes(this.width * this.height);
    }
}
