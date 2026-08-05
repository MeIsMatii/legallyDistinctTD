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
        setPaintOrder(RetryButton.class, MuteButton.class, SongButton.class, WaveResetButton.class,CloseButton.class, SongDropDown.class, VolumeSlider.class,SettingsPopup.class, SettingsButton.class,BackButton.class, PlayOnButton.class,PauseMenu.class);

        /// TODO BETTER MAP
        GreenfootImage img = new GreenfootImage("Map1.png");

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
