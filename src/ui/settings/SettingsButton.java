package ui.settings;

import core.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;
import map.levels.Map;
import map.levels.Map1;
import map.levels.Map2;
import map.menu.MapPreview;
import map.menu.MapSelector;

public class SettingsButton extends MainClass implements Clickable {

    public SettingsButton() {
        setImage("settingsIcon.png");
        getImage().scale(60, 60);
    }

    @Override
    public void onClick() {
        if (getWorld() instanceof Map){
            getWorld().addObject(new SettingsPopup(),getWorld().getWidth()/2,getWorld().getHeight()/2);
    } else if (getWorld() instanceof MapSelector) {
            Greenfoot.setWorld(new MapSettings());
        }

        }

        public void act() {
        checkClick();
    }

}
