package map.levels;

import map.menu.PauseMenu;
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


        /*
        addObject(new EnemyLevel1(), 0,233);
        addObject(new EnemyLevel2(), 0,233);
        addObject(new EnemyLevel3(), 0,233);
        addObject(new EnemyLevel4(), 0,233);
        addObject(new EnemyLevel5(), 0,233);
        addObject(new EnemyLevel6(), 0,233);
         */
        int[][] pathLocations = {{0,233},{752,233},{752,531},{1414,531},{1414,984}};
        super.addPath(pathLocations);


    }

    public int getMapNumber() {
        return 1;
    }


}
