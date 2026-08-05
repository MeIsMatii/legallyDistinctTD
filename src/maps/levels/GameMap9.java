package maps.levels;

import core.Player;
import greenfoot.GreenfootImage;
import maps.levels.util.MapCoordinatesUtilGuy;
import maps.menu.MapSelector;
import ui.common.BackButton;
import ui.common.ImageDisplay;

public class GameMap9 extends GameMap {
    public GameMap9() {
        GreenfootImage img = new GreenfootImage("Map9.png");

        img.scale(1620, 1080);
        setBackground(img);

        addObject(new MapCoordinatesUtilGuy(),0,0);
        int[][] pathLocations = {{0,233},{752,233},{752,531},{1414,531},{1414,984}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 9;
    }
}
