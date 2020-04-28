package org.kbbgl;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kbbgl.editor.PadTextArea;
import org.kbbgl.layout.MainScene;
import org.kbbgl.layout.RootLayout;
import org.kbbgl.menu.AppMenuBar;
import org.kbbgl.menu.file.FileMenuItemExit;
import org.kbbgl.menu.file.FileMenuItemNew;
import org.kbbgl.menu.file.FileMenuItemOpen;
import org.kbbgl.menu.file.FileMenuItemSave;
import org.kbbgl.tabs.EditorTab;
import org.kbbgl.tabs.EditorTabPane;
import sun.font.FontFamily;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Stream;

public class App extends Application{

    public static Stage primaryStage;

    public static void main(String[] args) {

        // On main thread
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        App.primaryStage = primaryStage;

        // Add an empty editor to the tab pane
        EditorTabPane editorTabPane = EditorTabPane.getInstance();

        // File menu and subitems
        Menu menuFile = new Menu("File");
        FileMenuItemExit itemExit = new FileMenuItemExit(getStage());
        FileMenuItemNew itemNew = new FileMenuItemNew();
        FileMenuItemOpen itemOpen = new FileMenuItemOpen(getStage());
        FileMenuItemSave itemSave = new FileMenuItemSave();
        menuFile.getItems().addAll(
                itemNew,
                itemOpen,
                itemSave,
                itemExit
        );

        AppMenuBar menuBar = new AppMenuBar(menuFile);
        VBox menuEditorSeparator = new VBox(1, editorTabPane);
//        RootLayout root = new RootLayout(menuBar, menuEditorSeparator);
        RootLayout root = RootLayout.getInstance();
        MainScene scene = new MainScene(root, 400, 500);

        editorTabPane.setScene(scene);
        primaryStage.setTitle("mPad");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static Stage getStage(){
        return primaryStage;
    }


}