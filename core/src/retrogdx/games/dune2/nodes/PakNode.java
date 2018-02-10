package retrogdx.games.dune2.nodes;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.widget.VisLabel;
import retrogdx.games.dune2.readers.Pak;
import retrogdx.generic.nodes.PlainTextNode;
import retrogdx.ui.AssetFolderNode;
import retrogdx.utils.SmartByteBuffer;
import retrogdx.utils.SliceInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PakNode extends AssetFolderNode {
    private FileHandle file;
    private Map<String, SliceInfo> palettes = new HashMap<>();
    private Map<String, SliceInfo> animations = new HashMap<>();

    public PakNode(Table previewArea, FileHandle file) {
        super(previewArea, file.name());
        this.file = file;
    }

    protected Array<Node> populate() {
        Pak pak = new Pak(SmartByteBuffer.wrap(this.file.readBytes()));
        Array<Node> nodes = new Array<>();

        for (Entry<String, SliceInfo> file : pak.getFiles().entrySet()) {
            if (file.getKey().endsWith(".INI")) {
                nodes.add(new PlainTextNode(this.previewArea, file.getKey(), file.getValue()));
            } else if (file.getKey().endsWith(".SHP")) {
                nodes.add(new ShpNode(this.previewArea, file.getKey(), file.getValue()));
            } else if (file.getKey().endsWith(".WSA")) {
                nodes.add(new WsaNode(this.previewArea, file.getKey(), file.getValue(), this.palettes, this.animations));
                this.animations.put(file.getKey(), file.getValue());
            } else if (file.getKey().endsWith(".VOC")) {
                // TODO soundfile
            } else if (file.getKey().endsWith(".PAL")) {
                nodes.add(new PalNode(this.previewArea, file.getKey(), file.getValue()));
                this.palettes.put(file.getKey(), file.getValue());
            } else if (file.getKey().endsWith(".ICN")) {
                nodes.add(new IcnNode(this.previewArea, file.getKey(), file.getValue()));
            } else if (file.getKey().endsWith(".MAP")) {
                // TODO Use in conjuction with ICN?
            } else if (file.getKey().endsWith(".FNT")) {
                // TODO font
            } else if (file.getKey().endsWith(".CPS")) {
                // TODO image

            } else if (file.getKey().endsWith(".ADL")) {
                // TODO music
            } else if (file.getKey().endsWith(".C55")) {
                // TODO music
            } else if (file.getKey().endsWith(".XMI")) {
                // TODO music
            } else if (file.getKey().endsWith(".PCS")) {
                // TODO music
            } else if (file.getKey().endsWith(".TAN")) {
                // TODO music
            } else if (file.getKey().endsWith(".DRV")) {
                // TODO UNK - pc speaker related?
            } else if (file.getKey().endsWith(".ADV")) {
                // TODO UNK - Soundcard driver?

            } else if (file.getKey().endsWith(".TBL")) {
                nodes.add(new TblNode(this.previewArea, file.getKey(), file.getValue()));
            } else if (file.getKey().endsWith(".EMC")) {
                // TODO scripts

            } else if (file.getKey().endsWith(".ENG") || file.getKey().endsWith(".FRE") || file.getKey().endsWith(".GER")) {
                // TODO strings (compressed)
            }
        }

        return nodes;
    }

    protected void showPreview() {
        this.previewArea.add(new VisLabel("Preview..."));
    }
}
