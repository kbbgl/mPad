package org.kbbgl.layout;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

import java.security.Key;

public class MainScene extends Scene implements EventHandler<KeyEvent> {

    final KeyCombination findCombination = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
    final KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    final KeyCombination closeTabCombination = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
    final KeyCombination chooseAllText = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
    final KeyCombination copySelectedText = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
    final KeyCombination pasteClipboard = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
    final KeyCombination deleteText = new KeyCodeCombination(KeyCode.BACK_SPACE);

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);
        this.addEventFilter(KeyEvent.KEY_PRESSED, this);
    }

    @Override
    public void handle(KeyEvent event) {

        System.out.println(event.getCode().getName() + " event detected");

        if (findCombination.match(event)){

            // TODO implement search

        } else if (saveCombination.match(event)){
            RootLayout.getInstance().saveTextToFile();
        } else if (closeTabCombination.match(event)){
            RootLayout.getInstance().closeTab();
        } else if (chooseAllText.match(event)) {
            RootLayout.getInstance().chooseAllText();
        } else if (copySelectedText.match(event)){
            RootLayout.getInstance().copySelectedText();
        } else if (pasteClipboard.match(event)){
            RootLayout.getInstance().pasteClipboardToEditor();
        } else if (deleteText.match(event)){
            RootLayout.getInstance().deleteText();
        }
        event.consume();

    }
}
