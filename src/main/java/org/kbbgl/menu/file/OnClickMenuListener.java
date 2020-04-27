package org.kbbgl.menu.file;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class OnClickMenuListener implements EventHandler<ActionEvent> {

    private final Stage stage;

    public OnClickMenuListener(Stage stage){
        this.stage = stage;
    }

    @Override
    public void handle(ActionEvent event) {

        System.out.println("Exit menu clicked. Closing application");

        this.stage.close();

    }
}
