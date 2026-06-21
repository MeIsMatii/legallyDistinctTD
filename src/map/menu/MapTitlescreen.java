package map.menu;

import greenfoot.GreenfootImage;
import greenfoot.World;
import ui.hud.buttons.StartingButton;

public class MapTitlescreen extends World {
    public MapTitlescreen() {
        super(1920, 1080, 1);
        //addObject(new Player(100,100),100,100);
        GreenfootImage background = new GreenfootImage("LegallyDistinctTD.png");
        background.scale(1920, 1080);
        setBackground(background);
        addObject(new StartingButton(), 1000, 800);
    }
}
