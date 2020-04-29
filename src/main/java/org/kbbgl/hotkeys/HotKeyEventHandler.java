package org.kbbgl.hotkeys;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.kbbgl.layout.RootLayout;

public class HotKeyEventHandler implements EventHandler<KeyEvent> {

    final KeyCombination findCombination = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
    final KeyCombination saveCombination = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    final KeyCombination closeTabCombination = new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN);
    final KeyCombination chooseAllText = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
    final KeyCombination copySelectedText = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
    final KeyCombination pasteClipboard = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
    final KeyCombination deleteText = new KeyCodeCombination(KeyCode.BACK_SPACE);
    final KeyCombination insertNewLine = new KeyCodeCombination(KeyCode.ENTER);
    final KeyCombination newTab = new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN);
    final KeyCombination newFile = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    // TODO add shift + ctrl + <-> to highlight word

    public HotKeyEventHandler() {

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
        } else if (insertNewLine.match(event)){
            RootLayout.getInstance().insertNewLine();
        } else if (newTab.match(event)){
            RootLayout.getInstance().createNewTab();
        }
        // TODO create a new window
        // need to modify editortabpane and rootlayout from singleton
//        else if (newFile.match(event)){
//            RootLayout.getInstance().createNewWindow();
//        }
        event.consume();

    }
}
