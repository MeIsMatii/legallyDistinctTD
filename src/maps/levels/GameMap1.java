package maps.levels;

import maps.levels.util.MapCoordinatesUtilGuy;
import maps.menu.PauseMenu;
import greenfoot.GreenfootImage;
import ui.common.BackButton;
import ui.hud.buttons.*;
import ui.settings.*;
import ui.settings.sound.SongButton;
import ui.settings.sound.SongDropDown;
import ui.settings.sound.VolumeSlider;


public class GameMap1 extends GameMap {
    public GameMap1() {
        GreenfootImage img = new GreenfootImage("Maps/Map1.png");

        img.scale(1620, 1080);
        setBackground(img);

        addObject(new MapCoordinatesUtilGuy(),0,0);
        int[][] pathLocations = {{0,233},{752,233},{752,531},{1414,531},{1414,getHeight()}};
        super.addPath(pathLocations);


    }

    public int getMapNumber() {
        return 1;
    }


}
