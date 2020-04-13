package org.kbbgl.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class AppMenuBar extends MenuBar {


    public AppMenuBar(Menu... menus){

        this.getMenus().addAll(menus);

    }

    public Menu getMenuFile(){
        return this.getMenus().get(0);
    }

    public void addToMenuFile(MenuItem... menuItems){
        this.getMenus().get(0).getItems().addAll(menuItems);
    }

    public Menu getMenuView(){
        return this.getMenus().get(1);
    }

}
