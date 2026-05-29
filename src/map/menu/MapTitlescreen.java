package map.menu;

import core.Player;
import greenfoot.GreenfootImage;
import greenfoot.World;
import map.levels.Map;
import ui.common.HomeButton;
import ui.common.ImageDisplay;
import ui.hud.StartingButton;

public class MapTitlescreen extends World {
    public MapTitlescreen() {
        super(1920,1080,1);
        addObject(new Player(100,100),100,100);
        GreenfootImage background = new GreenfootImage("StartingScreen.png");
        background.scale(1920, 1080);
        setBackground(background);
        addObject(new StartingButton(),1000,800);
    }
}
