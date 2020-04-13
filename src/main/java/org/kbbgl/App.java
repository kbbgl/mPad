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
import javafx.scene.input.MouseButton;
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
import org.kbbgl.layout.RootLayout;
import org.kbbgl.menu.AppMenuBar;
import org.kbbgl.menu.file.FileMenuItemExit;
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

    private static final String BROWSER = "Browser";
    private static final String EDITOR = "new editor";
    private static int browserCnt = 1;

    private Stage primaryStage;
    private TabPane tabPane;
    private Vector<PadTextArea> editors = new Vector<>();
    private PadTextArea currentEditor = null;

    public static void main(String[] args) {

        // On main thread
        Application.launch(args);

        // To get list of parameters later:
        // App.getParameters()
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        // Add an empty editor to the tab pane
        tabPane = new TabPane();
        Tab newTab = new Tab("New Tab");
        PadTextArea newTabTextArea = new PadTextArea();
        newTab.setContent(newTabTextArea);

        tabPane.getTabs().add(newTab);
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Tab changed");
            currentEditor = null;
        });
        tabPane.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)){
                if (event.getClickCount() == 2){
                    System.out.println("Double clicked");
                    Tab nTab = new Tab("New Tab");
                    PadTextArea newTextArea = new PadTextArea();
                    nTab.setContent(newTextArea);

                    tabPane.getTabs().add(nTab);
                }
            }
        });

        // File menu and subitems
        Menu menuFile = new Menu("File");
        MenuItem menuItemNew = new MenuItem("New");
        menuItemNew.setOnAction(event -> {

//            createNew(EDITOR);
        });

        MenuItem menuFileOpen = new MenuItem("Open");
        menuFileOpen.setOnAction(event -> {
//            chooseAndLoadFile();
        });

        MenuItem menuFileSave = new MenuItem("Save");
        menuFileSave.setOnAction(event -> {
//            saveFileRev();
        });

        MenuItem menuFileExit = new MenuItem("Exit");
        menuFileExit.setOnAction(event -> {
            getStage().close();
        });


//        menuFile.getItems().addAll(
//          menuItemNew,
//          menuFileOpen,
//          menuFileSave,
//          new SeparatorMenuItem(),
//          menuFileExit
//        );

        FileMenuItemExit itemExit = new FileMenuItemExit(getStage());
        menuFile.getItems().addAll(menuFileExit);


        AppMenuBar menuBar = new AppMenuBar(menuFile);


        // on JavaFX application thread
        primaryStage.setTitle("mPad");

        VBox layout = new VBox(1, tabPane);
        RootLayout root = new RootLayout(menuBar, layout);

        Scene scene = new Scene(root, 300, 250);
        tabPane.prefWidthProperty().bind(scene.widthProperty());
        tabPane.prefHeightProperty().bind(scene.heightProperty());


//        Button button = new Button("Open file");
//        button.setOnAction(event -> {
//            System.out.println("Button pressed");
//
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text files", "*.log"));
//            File file = fileChooser.showOpenDialog(primaryStage);
//
//            // Check if no file chosen
//            if (file != null){
//                Path filePath = Paths.get(file.toURI());
//
//                System.out.println("File chosen: " + file.getAbsolutePath());
//                if (file.length() % 1024 == 0){
//                    System.out.println("File size in bytes: " + file.length());
//                } else {
//                    System.out.println("File size: " + file.length()/1024 + "KB");
//                }
//
//
//                try {
//                    BufferedReader reader = Files.newBufferedReader(filePath);
//                    Stream<String> lines = reader.lines();
//
//                    System.out.println("Adding lines to text area...");
//
//                    // TODO improve content loading into UI
//                    lines.forEach(line -> {
//
//                        Platform.runLater(() -> {
//                            textArea.appendText(line + "\n");
//                        });
//
//                    });
//
//                    System.out.println("Added lines to text area.");
//
//                    System.out.println("Closing reader...");
//                    reader.close();
//                    System.out.println("Closed reader");
//
//                } catch (IOException e) {
//                    System.out.println("Error reading file " + file.toPath() + ", " + e.getMessage());
//                }
//
//
//            }
//        });

//        root.setTop(button);
//        root.setCenter(textArea);
        primaryStage.setScene(scene);
        primaryStage.show();

        Region region = (Region) newTabTextArea.lookup(".content");
        region.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private Stage getStage(){
        return primaryStage;
    }
}