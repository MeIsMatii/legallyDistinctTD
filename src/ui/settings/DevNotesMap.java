package ui.settings;

import greenfoot.GreenfootImage;
import maps.util.CustomWorld;
import ui.common.BackButton;

public class DevNotesMap extends CustomWorld {
    /**
     * @author Colin
     */
    public DevNotesMap() {
        super();
        GreenfootImage img = new GreenfootImage("Maps/DeveloperNotes.png");
        img.scale(1920, 1080);
        setBackground(img);
        addObject(new BackButton(), 50, 50);
    }
}
