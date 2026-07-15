package map.levels;

import core.Player;
import greenfoot.GreenfootImage;

public class GameMap2 extends GameMap {
    public GameMap2() {
        GreenfootImage img = new GreenfootImage("Map2.png");

        img.scale(1620, 1080);
        setBackground(img);
        addObject(new Player(), 6, 6);
        int[][] pathLocations = {{1407,15},{1407,387},{225,387},{225,876},{1585,876}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 2;
    }
}
