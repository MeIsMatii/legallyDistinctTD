package ui.hud.buttons;

import ui.settings.MapSettings;
import ui.settings.SettingsPopup;
import greenfoot.Greenfoot;
import maps.levels.GameMap;
import maps.menu.MapSelector;

public class SettingsButton extends Button{

    public SettingsButton() {
        setImage("settingsIcon.png");
        getImage().scale(60, 60);
    }

    @Override
    public void onClick() {
        if (getWorld() instanceof GameMap){
            getWorld().addObject(new SettingsPopup(),getWorld().getWidth()/2,getWorld().getHeight()/2);
    } else if (getWorld() instanceof MapSelector) {
            Greenfoot.setWorld(new MapSettings());
        }

        }

}
