package org.kbbgl.menu.file;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class FileMenuItemOpen extends MenuItem implements EventHandler<ActionEvent> {

    public FileMenuItemOpen(){
        this.setText("Open");
        this.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Opening new file...");
    }
}
