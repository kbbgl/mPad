package org.kbbgl.layout;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class MainScene extends Scene implements EventHandler<KeyEvent> {

    final KeyCombination findCombination = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
    final KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    final KeyCombination closeTabCombination = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
    final KeyCombination chooseAllText = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
    final KeyCombination copySelectedText = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
    final KeyCombination pasteClipboard = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);
        this.addEventFilter(KeyEvent.KEY_PRESSED, this);
    }

    @Override
    public void handle(KeyEvent event) {

        System.out.println(event.getCode().getName() + " event detected");

        if (findCombination.match(event)){
            System.out.println("Search enabled");
        } else if (saveCombination.match(event)){
            System.out.println("Save hotkey");
            RootLayout.getInstance().saveTextToFile();
        } else if (closeTabCombination.match(event)){
            System.out.println("Close tab hotkey pressed");
            RootLayout.getInstance().closeTab();
        } else if (chooseAllText.match(event)) {
            System.out.println("CTRL + A pressed");
            RootLayout.getInstance().chooseAllText();
        } else if (copySelectedText.match(event)){
            System.out.println("CTRL + C pressed");
            RootLayout.getInstance().copySelectedText();
        } else if (pasteClipboard.match(event)){
            System.out.println("CTRL + V");
            RootLayout.getInstance().pasteClipboardToEditor();
        }
        event.consume();

    }
}
