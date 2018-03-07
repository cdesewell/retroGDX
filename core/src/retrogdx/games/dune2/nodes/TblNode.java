package retrogdx.games.dune2.nodes;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import retrogdx.games.dune2.readers.Tbl;
import retrogdx.ui.AssetFileNode;
import retrogdx.ui.previews.ImagePreview;
import retrogdx.utils.SmartByteBuffer;

public class TblNode extends AssetFileNode {
    public TblNode(Table previewArea, String name, SmartByteBuffer buffer) {
        super(previewArea, name, buffer);
    }

    protected void showPreview() {
        Tbl tbl = new Tbl(this.buffer);

        Pixmap pixmap = new Pixmap(16, 16, Pixmap.Format.RGBA8888);

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                int grey = tbl.map[x + y * 16] & 0xff;
                pixmap.drawPixel(x, y, (grey << 24) | (grey << 16) | (grey << 8) | 0xff);
            }
        }

        this.previewArea.add(new ImagePreview(pixmap));
    }
}
