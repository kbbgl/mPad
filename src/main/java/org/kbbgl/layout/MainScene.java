package org.kbbgl.layout;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import org.kbbgl.hotkeys.HotKeyEventHandler;

public class MainScene extends Scene {

    public MainScene(Parent root, double width, double height) {
        super(root, width, height);

        HotKeyEventHandler eventHandler = new HotKeyEventHandler();
        this.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
    }
}
