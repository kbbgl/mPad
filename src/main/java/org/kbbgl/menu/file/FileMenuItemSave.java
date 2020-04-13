package org.kbbgl.menu.file;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class FileMenuItemSave extends MenuItem implements EventHandler<ActionEvent> {

    public FileMenuItemSave(){
        this.setText("Save");
        this.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Saving to file...");
    }
}

