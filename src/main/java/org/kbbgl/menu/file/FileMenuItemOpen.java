package org.kbbgl.menu.file;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kbbgl.App;
import org.kbbgl.io.FileReaderTask;

import java.io.File;

public class FileMenuItemOpen extends MenuItem implements EventHandler<ActionEvent> {

    private final Stage stage;

    public FileMenuItemOpen(Stage stage){
        this.setText("Open");
        this.setOnAction(this);
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Opening new file...");

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(this.stage);

        // Check if no file chosen
        if (file != null){

            FileReaderTask readFile = new FileReaderTask(file);
            Thread thread = new Thread(readFile);
            thread.setDaemon(true);
            thread.start();

        }

    }
}
