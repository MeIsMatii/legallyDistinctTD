package map.levels;

import entities.enemy.EnemyLevel1;
import entities.enemy.EnemyLevel2;
import entities.tower.TrapTower;
import map.menu.PauseMenu;
import entities.tower.TestTower;
import greenfoot.GreenfootImage;
import map.util.MapCoordinatesUtilGuy;
import ui.common.BackButton;
import ui.settings.PlayOnButton;
import ui.settings.SettingsButton;


public class Map1 extends Map {


    public Map1() {
        setPaintOrder(SettingsButton.class, BackButton.class, PlayOnButton.class,PauseMenu.class);

        /// TODO BETTER MAP
        GreenfootImage img = new GreenfootImage("Map1.png");

        img.scale(1620, 1080);
        setBackground(img);



        addObject(new TrapTower(), 500,200);
        addObject(new EnemyLevel1(), 0,233);
        addObject(new EnemyLevel2(), 0,233);

        int[][] pathLocations = {{0,233},{752,233},{752,531},{1414,531},{1414,984}};
        super.addPath(pathLocations);


    }


}
