package org.kbbgl.layout;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
import java.util.Optional;

public class RootLayout extends BorderPane {

    private static RootLayout instance;
    private final VBox menuEditorSeparator;

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
        menuEditorSeparator = new VBox(1,editorTabPane);

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
        File file = null;

            String initialFilename = textToWrite.toLowerCase().trim();
            if (initialFilename.length() >= 10){
                fileChooser.setInitialFileName(initialFilename.substring(0, 10));
            } else {
                fileChooser.setInitialFileName(initialFilename);
            }

            file = fileChooser.showSaveDialog(App.getStage());

            if (file != null){

                if (textToWrite.equals("")) {

                    Alert alert = new Alert(Alert.AlertType.WARNING, "This will clear file " + file.getName() + ".\nContinue?", ButtonType.OK, ButtonType.CANCEL);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (!result.isPresent() || result.get() == ButtonType.CANCEL){

                        System.out.println("Writing to file '" + file.getAbsolutePath() + "' cancelled.");

                    } else if (result.get() == ButtonType.OK){
                        FileWriterTask fileWriterTask = new FileWriterTask(file, textToWrite);
                        Thread thread = new Thread(fileWriterTask);
                        thread.setDaemon(true);
                        thread.start();
                    }
                } else {
                    FileWriterTask fileWriterTask = new FileWriterTask(file, textToWrite);
                    Thread thread = new Thread(fileWriterTask);
                    thread.setDaemon(true);
                    thread.start();
                }
            }
    }

    public void chooseAllText() {

        EditorTab currentTab = EditorTabPane.getInstance().getCurrentTab();
        PadTextArea textArea = (PadTextArea) currentTab.getContent();
        textArea.selectAll();

    }

    public void copySelectedText() {

        EditorTab currentTab = EditorTabPane.getInstance().getCurrentTab();
        PadTextArea padTextArea = (PadTextArea) currentTab.getContent();
        String selectedText = padTextArea.getSelectedText();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(selectedText);
        clipboard.setContent(content);
        System.out.println("Selected text '" + selectedText + "' copied to clipboard");

    }

    public void pasteClipboardToEditor() {

        EditorTab currentTab = EditorTabPane.getInstance().getCurrentTab();
        PadTextArea padTextArea = (PadTextArea) currentTab.getContent();

        final Clipboard clipboard = Clipboard.getSystemClipboard();
        String textInClipboard = clipboard.getString();

        padTextArea.appendText(textInClipboard);
        System.out.println("Text of length " + textInClipboard.length() + " in clipboard appended");

    }

    public void deleteText() {

        EditorTab currentTab = EditorTabPane.getInstance().getCurrentTab();
        PadTextArea padTextArea = (PadTextArea) currentTab.getContent();

        System.out.println("Deleting the previous character...");
        padTextArea.deletePreviousChar();

    }

    public void insertNewLine() {

        EditorTab currentTab = EditorTabPane.getInstance().getCurrentTab();
        PadTextArea padTextArea = (PadTextArea) currentTab.getContent();

        System.out.println("Inserting new line...");
        padTextArea.appendText("\n");

    }

    // TODO fix case when tab created
    // reproduced when creating 'new tab', 'new tab 1' and then deleting 'new tab'
    public void createNewTab() {
        System.out.println("Creating new tab...");

        if (EditorTabPane.getInstance().getTabs().size() > 0) {

            EditorTabPane.getInstance().addTab(new EditorTab("New Tab " + EditorTabPane.getInstance().getTabs().size()));
        } else {
            EditorTabPane.getInstance().addTab(new EditorTab("New Tab"));
        }
    }

    public void createNewWindow() {

        System.out.println("Creating new window...");
        Stage newStage = new Stage();
        MainScene newScene = new MainScene(RootLayout.getInstance(), 300, 300);
        newStage.setScene(newScene);
        newStage.show();

    }
}
