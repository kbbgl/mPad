package org.kbbgl.layout;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.kbbgl.App;
import org.kbbgl.editor.PadTextArea;
import org.kbbgl.io.FileWriterTask;
import org.kbbgl.menu.AppMenuBar;
import org.kbbgl.menu.file.FileMenuItemExit;
import org.kbbgl.menu.file.FileMenuItemNew;
import org.kbbgl.menu.file.FileMenuItemOpen;
import org.kbbgl.menu.file.FileMenuItemSave;
import org.kbbgl.tabs.EditorTab;
import org.kbbgl.tabs.EditorTabPane;

import java.io.File;

public class RootLayout extends BorderPane {

    private static RootLayout instance;

    public static RootLayout getInstance() {
        if (instance == null){
            instance = new RootLayout();
        }
        return instance;
    }

    private RootLayout(){

        EditorTabPane editorTabPane = EditorTabPane.getInstance();

        // File menu and subitems
        Menu menuFile = new Menu("File");
        FileMenuItemExit itemExit = new FileMenuItemExit(App.getStage());
        FileMenuItemNew itemNew = new FileMenuItemNew();
        FileMenuItemOpen itemOpen = new FileMenuItemOpen(App.getStage());
        FileMenuItemSave itemSave = new FileMenuItemSave();
        menuFile.getItems().addAll(
                itemNew,
                itemOpen,
                itemSave,
                itemExit
        );

        AppMenuBar menuBar = new AppMenuBar(menuFile);
        VBox menuEditorSeparator = new VBox(1, editorTabPane);

        this.setTop(menuBar);
        this.setCenter(menuEditorSeparator);
    }

    public void closeTab(){
        EditorTab currentTab = EditorTabPane.getInstance().getCurrentTab();
        EditorTabPane.getInstance().getTabs().remove(currentTab);

    }

    public void saveTextToFile(){

        EditorTab currentTab = EditorTabPane.getInstance().getCurrentTab();
        PadTextArea textArea = (PadTextArea) currentTab.getContent();
        String textToWrite = textArea.getText();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(currentTab.getText());
        File file = fileChooser.showSaveDialog(App.getStage());


        if (file != null){
            FileWriterTask fileWriterTask = new FileWriterTask(file, textToWrite);
            Thread thread = new Thread(fileWriterTask);
            thread.setDaemon(true);
            thread.start();
        }

    }
}
