package retrogdx.games.dune2.readers;

import retrogdx.utils.SmartByteBuffer;

import java.nio.ByteOrder;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pak {
    private SmartByteBuffer buffer;

    public Pak(SmartByteBuffer buffer) {
        this.buffer = buffer;
    }

    public Map<String, SmartByteBuffer> getFiles() {
        this.buffer.order(ByteOrder.LITTLE_ENDIAN);
        this.buffer.position(0);

        Map<String, SmartByteBuffer> files = new LinkedHashMap<>();

        while (true) {
            int offset = this.buffer.readInt();

            if (offset == 0) {
                break;
            }

            String fileName = this.buffer.readString();

            int endOffset = this.buffer.readInt();
            this.buffer.position(this.buffer.position() - 4);

            if (endOffset == 0) {
                endOffset = this.buffer.capacity();
            }

            files.put(fileName, this.buffer.slice(offset, endOffset - offset));
        }

        return files;
    }
}
