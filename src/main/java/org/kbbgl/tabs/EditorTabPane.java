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
    private EditorTab currentTab;

    public static EditorTabPane getInstance() {

        if (instance == null){
            instance = new EditorTabPane();
        }
        return instance;
    }

    private EditorTabPane(){

        this.getSelectionModel().selectedItemProperty().addListener(this);
        this.setOnMouseClicked(this);

        addTab(new EditorTab("New Tab"));
    }

    public void setScene(Scene scene) {
        this.prefHeightProperty().bind(scene.widthProperty());
        this.prefWidthProperty().bind(scene.widthProperty());
    }

    public void addTab(EditorTab tab){

        this.getTabs().add(tab);
        setCurrentTab(tab);
    }

    @Override
    public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {

        try {

            if (oldValue != null){
                System.out.println("Tab changed: " + oldValue.getText() + " => " + newValue.getText());
                setCurrentTab((EditorTab) this.getSelectionModel().getSelectedItem());
            }

        } catch (NullPointerException exception){

            System.out.println("All tabs removed");

        }

    }



    private void setCurrentTab(EditorTab tab){
        this.currentTab = tab;
        System.out.println("Currently selected tab in " + this.getSelectionModel().getSelectedIndex() + " position, title: " + tab.getText());
        this.getSelectionModel().select(tab);
    }

    public EditorTab getCurrentTab() {
        return currentTab;
    }

    @Override
    public void handle(MouseEvent event) {

        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                System.out.println("Double clicked. Creating new tab...");

                if (this.getTabs().size() > 0) {
                    addTab(new EditorTab("New Tab " + this.getTabs().size()));
                } else {
                    addTab(new EditorTab("New Tab"));
                }

                System.out.println("New tab created. Number of tabs: " + this.getTabs().size());

            }
        }

    }
}
