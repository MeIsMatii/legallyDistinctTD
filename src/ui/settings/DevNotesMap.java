package ui.settings;

import greenfoot.GreenfootImage;
import greenfoot.World;
import maps.menu.MapSelector;
import ui.common.BackButton;

public class DevNotesMap extends World {
    /**
     * @Author Colin
     */
    public DevNotesMap() {
        super(1920, 1080, 1);
        GreenfootImage img = new GreenfootImage("Maps/DeveloperNotes.png");
        img.scale(1920,1080);
        setBackground(img);
        addObject(new BackButton(new MapSelector()),50,50);
    }
}
