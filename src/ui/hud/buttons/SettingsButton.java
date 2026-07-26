package ui.hud.buttons;

import ui.settings.SettingsPopup;

public class SettingsButton extends Button{

    public SettingsButton() {
        setImage("settingsIcon.png");
        getImage().scale(60, 60);
    }

    @Override
    public void onClick() {
        getWorld().addObject(new SettingsPopup(),getWorld().getWidth()/2,getWorld().getHeight()/2);
        }

}
