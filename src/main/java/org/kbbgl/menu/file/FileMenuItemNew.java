package org.kbbgl.menu.file;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import org.kbbgl.layout.RootLayout;
import org.kbbgl.tabs.EditorTab;
import org.kbbgl.tabs.EditorTabPane;

public class FileMenuItemNew extends MenuItem implements EventHandler<ActionEvent> {

    public FileMenuItemNew(){
        this.setText("New");
        this.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        RootLayout.getInstance().createNewTab();
    }
}
