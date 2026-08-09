package ui.hud.buttons;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import ui.settings.DevNotesMap;

/**
 * @author Colin
 */
public class DeveloperNotesButton extends Button {
    public DeveloperNotesButton() {
        GreenfootImage img = new GreenfootImage("DeveloperNotesButton.png");
        img.scale(250, 150);
        setImage(img);
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        Greenfoot.setWorld(new DevNotesMap());
    }
}
