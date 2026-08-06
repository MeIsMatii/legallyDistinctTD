package maps.levels;

import greenfoot.GreenfootImage;
import maps.levels.util.MapCoordinatesUtilGuy;

public class GameMap7 extends GameMap {
    public GameMap7() {
        GreenfootImage img = new GreenfootImage("Maps/Map7.png");

        img.scale(1620, 1080);
        setBackground(img);

        addObject(new MapCoordinatesUtilGuy(),0,0);
        int[][] pathLocations = {{0,233},{752,233},{752,531},{1414,531},{1414,984}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 7;
    }
}
