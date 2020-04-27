package org.kbbgl.tabs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.kbbgl.editor.PadTextArea;

public class EditorTabPane extends TabPane implements ChangeListener<Tab>, EventHandler<MouseEvent> {

    private static EditorTabPane instance;

    public static EditorTabPane getInstance() {

        if (instance == null){
            instance = new EditorTabPane();
        }
        return instance;
    }

    private EditorTabPane(){

        this.getSelectionModel().selectedItemProperty().addListener(this);
        this.setOnMouseClicked(this);


        // Create initial tab
        EditorTab initialTab = new EditorTab("New Tab");
        PadTextArea newTextArea = new PadTextArea();
        initialTab.setContent(newTextArea);
        addTab(initialTab);

    }

    public void setScene(Scene scene) {
        this.prefHeightProperty().bind(scene.widthProperty());
        this.prefWidthProperty().bind(scene.widthProperty());
    }

    public void addTab(EditorTab tab){
        this.getTabs().add(tab);
    }

    @Override
    public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {

        try {

            System.out.println("Tab changed: " + oldValue.getText() + " => " + newValue.getText());
            System.out.println("Currently selected tab in " + this.getSelectionModel().getSelectedIndex() + " position");

        } catch (NullPointerException exception){

            System.out.println("All tabs removed");

        }

    }

    @Override
    public void handle(MouseEvent event) {

        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                System.out.println("Double clicked. Creating new tab...");
                EditorTab newTab = new EditorTab("New Tab");
                PadTextArea newTextArea = new PadTextArea();
                newTab.setContent(newTextArea);
                addTab(newTab);
                System.out.println("New tab created. Number of tabs: " + this.getTabs().size());

            }
        }

    }
}
