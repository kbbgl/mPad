package org.kbbgl.tabs;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import org.kbbgl.editor.PadTextArea;

public class EditorTab extends Tab {

    private PadTextArea textArea;

    public EditorTab(String filename){

        super(filename);
        textArea = new PadTextArea();
        this.setContent(textArea);

    }

    public EditorTab(String filename, Node content) {
        super(filename, content);
        textArea = new PadTextArea();
        this.setContent(content);
    }
}
