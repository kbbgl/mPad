package org.kbbgl.tabs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class EditorTabPane extends TabPane {

    private static EditorTabPane instance;
    private static ObservableList<EditorTab> tabs;

    public static EditorTabPane getInstance() {

        if (instance == null){
            instance = new EditorTabPane();
        }
        return instance;
    }

    private EditorTabPane(){
        this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
            }
        });
    }

    public void addTab(EditorTab tab){
        this.getTabs().add(tab);
    }


}
