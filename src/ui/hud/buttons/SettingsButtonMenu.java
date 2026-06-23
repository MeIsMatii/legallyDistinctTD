package ui.hud.buttons;

import ui.settings.MapSettings;
import util.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;

public class SettingsButtonMenu extends Button{


    public SettingsButtonMenu() {
        setImage("settingsIcon.png");
        getImage().scale(80, 80);
    }

    public void onClick() {
        Greenfoot.setWorld(new MapSettings());
    }

}