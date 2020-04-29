package org.kbbgl.io;

import javafx.application.Platform;
import javafx.concurrent.Task;
import org.kbbgl.editor.PadTextArea;
import org.kbbgl.tabs.EditorTab;
import org.kbbgl.tabs.EditorTabPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderTask extends Task<Void> {

    private final File file;

    public FileReaderTask(File file) {

        this.file = file;

    }

    @Override
    protected Void call() {

        System.out.println("File chosen: " + this.file.getAbsolutePath());
        if (file.length() % 1024 == 0){
            System.out.println("File size in bytes: " + file.length());
        } else {
            System.out.println("File size: " + file.length()/1024 + "KB");
        }

        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(file.toURI()));
            Stream<String> lines = reader.lines();
            createNewTab(lines);

            System.out.println("Closing reader...");
            reader.close();
            System.out.println("Closed reader");

            lines = null;

        } catch (IOException e) {
            System.out.println("Error reading file " + file.getAbsolutePath());
            e.printStackTrace();
        }

        return null;
    }

    private void createNewTab(Stream<String> content){

        PadTextArea textArea = new PadTextArea();
        textArea.setText(content.collect(Collectors.joining("\n")));
        EditorTab newOpenFileTab = new EditorTab(file.getName(), textArea);

        System.out.println("Added lines to text area.");


        // Create a new tab and move to it
        Platform.runLater(() -> {

            EditorTabPane.getInstance().addTab(newOpenFileTab);

        });

        content = null;

    }
}
