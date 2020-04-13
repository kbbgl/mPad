package org.kbbgl.menu.file;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class FileMenuItemNew extends MenuItem implements EventHandler<ActionEvent> {

    public FileMenuItemNew(){
        this.setText("New");
        this.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Create new file...");
    }
}
