package maps.menu;

import greenfoot.GreenfootImage;
import greenfoot.World;
import maps.util.CustomWorld;
import ui.hud.buttons.StartingButton;
/**
 * @author Colin
 * @author Mathilo
 */
public class MapTitlescreen extends CustomWorld {
    public MapTitlescreen() {
        super();
        //addObject(new Player(100,100),100,100);
        GreenfootImage background = new GreenfootImage("LegallyDistinctTD.png");
        background.scale(1920, 1080);
        setBackground(background);
        addObject(new StartingButton(), 1000, 800);
    }
}
