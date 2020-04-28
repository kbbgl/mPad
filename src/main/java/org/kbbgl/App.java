package org.kbbgl;

import javafx.application.Application;
import javafx.stage.Stage;
import org.kbbgl.layout.MainScene;
import org.kbbgl.layout.RootLayout;
import org.kbbgl.tabs.EditorTabPane;

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