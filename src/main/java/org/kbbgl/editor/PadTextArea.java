package org.kbbgl.editor;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PadTextArea extends TextArea {

    public  PadTextArea() {
        initialize();
    }

    private void initialize() {

        System.out.println("Pad instance created");
        this.setFont(Font.font("Courier", FontWeight.BOLD, 14));
        this.setEditable(true);

    }


}
