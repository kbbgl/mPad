package org.kbbgl.layout;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class RootLayout extends BorderPane {

    private RootLayout instance;
    private MenuBar menu;
    private VBox centerLayout;

    public RootLayout getInstance() {
        if (instance == null){
            instance = new RootLayout();
        }
        return instance;
    }

    private RootLayout(){

    }

    public RootLayout(MenuBar menu, VBox centerLayout){
        this.menu = menu;
        this.centerLayout = centerLayout;

        this.setTop(menu);
        this.setCenter(centerLayout);

    }

    public VBox getCenterLayout() {
        return centerLayout;
    }
}
