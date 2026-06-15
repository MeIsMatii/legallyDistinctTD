package ui.settings;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;

public class SettingsButtonMenu extends MainClass implements Clickable {


    public SettingsButtonMenu() {
        setImage("settingsIcon.png");
        getImage().scale(80, 80);
    }

    public void onClick() {
        Greenfoot.setWorld(new MapSettings());
    }

    public void act(){
        checkClick();
}
}