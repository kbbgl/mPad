package org.kbbgl.io;

import javafx.application.Platform;
import javafx.concurrent.Task;
import org.kbbgl.tabs.EditorTabPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriterTask extends Task<Void> {

    private final File file;
    private final String text;

    public FileWriterTask(File file, String text) {
        this.file = file;
        this.text = text;
    }

    @Override
    protected Void call() {


        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(text);
            writer.close();

            Platform.runLater(() -> {
                EditorTabPane.getInstance().getCurrentTab().setText(file.getName());
            });

        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file " + file.getName());
            e.printStackTrace();
        }

        return null;
    }
}
