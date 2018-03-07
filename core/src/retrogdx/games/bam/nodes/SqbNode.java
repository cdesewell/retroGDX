package retrogdx.games.bam.nodes;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import retrogdx.games.bam.readers.Sqb;
import retrogdx.ui.AssetFileNode;
import retrogdx.ui.previews.TextPreview;
import retrogdx.utils.SmartByteBuffer;

import java.util.Map;

public class SqbNode extends AssetFileNode {
    public SqbNode(Table previewArea, String name, SmartByteBuffer buffer) {
        super(previewArea, name, buffer);
    }

    protected void showPreview() {
        Sqb sqb = new Sqb(this.buffer);

        String text = "";

        for (Map.Entry<Integer, String> entry : sqb.texts.entrySet()) {
            text += entry.getKey() + " :: " + entry.getValue() + "\n";
        }

        this.previewArea.add(new TextPreview(text.substring(0, text.length() - 1)));
    }
}
