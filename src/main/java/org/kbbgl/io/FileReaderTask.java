package org.kbbgl.io;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.File;

public class FileReaderTask extends Task<String> {

    private final File file;

    public FileReaderTask(File file) {

        this.file = file;

    }

    @Override
    protected String call() {

        System.out.println("File chosen: " + this.file.getAbsolutePath());
        if (file.length() % 1024 == 0){
            System.out.println("File size in bytes: " + file.length());
        } else {
            System.out.println("File size: " + file.length()/1024 + "KB");
        }

        Platform.runLater(() -> {



        });

        return "test";

    }
}
