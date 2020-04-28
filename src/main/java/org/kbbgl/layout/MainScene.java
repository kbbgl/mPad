package org.kbbgl.layout;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class MainScene extends Scene implements EventHandler<KeyEvent> {

    final KeyCombination findCombination = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_ANY);
    final KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY);
    final KeyCombination closeTabCombination = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_ANY);

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);
        this.addEventFilter(KeyEvent.KEY_PRESSED, this);
    }

    @Override
    public void handle(KeyEvent event) {

        if (findCombination.match(event)){
            System.out.println("Search enabled");
        } else if (saveCombination.match(event)){
            System.out.println("Save hotkey");
        } else if (closeTabCombination.match(event)){
            System.out.println("Close tab hotkey pressed");
            RootLayout.getInstance().closeTab();
        }
        event.consume();

    }
}
