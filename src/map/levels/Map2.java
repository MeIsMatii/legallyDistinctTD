package map.levels;

import core.Player;
import greenfoot.GreenfootImage;
import map.menu.MapSelector;
import ui.common.BackButton;
import ui.common.ImageDisplay;

public class Map2 extends Map {
    public Map2() {
        GreenfootImage img = new GreenfootImage("Map2.png");

        img.scale(1620, 1080);
        setBackground(img);
        addObject(new Player(), 6, 6);

    }

    @Override
    public int getMapNumber() {
        return 2;
    }
}
