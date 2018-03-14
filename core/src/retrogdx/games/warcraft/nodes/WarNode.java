package retrogdx.games.warcraft.nodes;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.utils.Array;
import retrogdx.games.warcraft.readers.Pal;
import retrogdx.games.warcraft.readers.War;
import retrogdx.generic.nodes.CreativeVocNode;
import retrogdx.generic.nodes.PlainTextNode;
import retrogdx.generic.nodes.RiffWaveNode;
import retrogdx.ui.AssetFolderNode;
import retrogdx.utils.SmartByteBuffer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WarNode extends AssetFolderNode {
    private Map<String, Pal> palettes = new HashMap<>();

    public WarNode(Table previewArea, FileHandle file) {
        super(previewArea, file);
    }

    protected Array<Node> populate() {
        War war = new War(SmartByteBuffer.wrap(this.file.readBytes()));
        Array<Node> nodes = new Array<>();

        for (Entry<String, SmartByteBuffer> file : war.getFiles().entrySet()) {
            if (file.getKey().endsWith(".WAV")) {
                nodes.add(new RiffWaveNode(this.previewArea, file.getKey(), file.getValue()));
            } else if (file.getKey().endsWith(".VOC")) {
                nodes.add(new CreativeVocNode(this.previewArea, file.getKey(), file.getValue()));
            } else if (file.getKey().endsWith(".XMI")) {
                // TODO music

            } else if (file.getKey().endsWith(".PAL")) {
                nodes.add(new PalNode(this.previewArea, file.getKey(), file.getValue()));
                this.palettes.put(file.getKey(), new Pal(file.getValue()));
            } else if (file.getKey().endsWith(".IMG")) {
                nodes.add(new ImgNode(this.previewArea, file.getKey(), file.getValue(), this.palettes));
            } else if (file.getKey().endsWith(".CUR")) {
                nodes.add(new CurNode(this.previewArea, file.getKey(), file.getValue(), this.palettes));
            } else if (file.getKey().endsWith(".SPR")) {
                nodes.add(new SprNode(this.previewArea, file.getKey(), file.getValue(), this.palettes));

            } else if (file.getKey().endsWith(".TILE")) {
                // TODO tileset
            } else if (file.getKey().endsWith(".TILEPART")) {
                // TODO tileset 1/4 tiles

            } else if (file.getKey().endsWith(".TXT")) {
                nodes.add(new PlainTextNode(this.previewArea, file.getKey(), file.getValue(), "CP850"));
            } else if (file.getKey().endsWith(".MAP")) {
                // TODO map preview
            } else if (file.getKey().endsWith(".GUI")) {
                // TODO gui setups
            } else {
                System.out.println(file.getKey());
            }
        }

        return nodes;
    }
}