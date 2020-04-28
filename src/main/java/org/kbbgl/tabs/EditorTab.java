package org.kbbgl.tabs;

import javafx.scene.control.Tab;

public class EditorTab extends Tab {

    public EditorTab(String filename){

        super(filename);
    }

    public EditorTab() {

        setText("New Tab");

    }



}
