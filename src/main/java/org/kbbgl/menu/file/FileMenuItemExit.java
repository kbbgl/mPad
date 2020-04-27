package org.kbbgl.menu.file;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class FileMenuItemExit extends MenuItem{

    private Stage stage;

    public FileMenuItemExit(Stage stage){
        this.stage = stage;
        this.setText("Exit");
        this.setOnAction(new OnClickMenuListener(stage));
    }

}