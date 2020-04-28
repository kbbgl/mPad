package org.kbbgl.menu.file;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kbbgl.io.FileReaderTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Log", "*.log"),
                new FileChooser.ExtensionFilter("Text", "*.txt")
        );
        File file = fileChooser.showOpenDialog(this.stage);

        // Check if no file chosen
        if (file != null){
            Path filePath = Paths.get(file.toURI());

            FileReaderTask readFile = new FileReaderTask(file);
            Thread thread = new Thread(readFile);
            thread.setDaemon(true);
            thread.start();


            try {
                BufferedReader reader = Files.newBufferedReader(filePath);
                Stream<String> lines = reader.lines();

                System.out.println("Adding lines to text area...");

                // TODO improve content loading into UI
//                lines.forEach(line -> {
//
//                    Platform.runLater(() -> {
//                        textArea.appendText(line + "\n");
//                    });
//
//                });

                System.out.println("Added lines to text area.");

                System.out.println("Closing reader...");
                reader.close();
                System.out.println("Closed reader");

            } catch (IOException e) {
                System.out.println("Error reading file " + file.toPath() + ", " + e.getMessage());
            }


        }

    }
}
