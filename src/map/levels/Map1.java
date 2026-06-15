package map.levels;

import entities.enemy.EnemyLevel1;
import entities.enemy.EnemyLevel2;
import entities.tower.TrapTower;
import map.menu.PauseMenu;
import entities.tower.TestTower;
import greenfoot.GreenfootImage;
import ui.common.BackButton;
import ui.settings.PlayOnButton;
import ui.settings.SettingsButton;


public class Map1 extends Map {


    public Map1() {
        //setBackground("Map1.jpg");
        setPaintOrder(SettingsButton.class, BackButton.class, PlayOnButton.class,PauseMenu.class);

        /// TODO BETTER MAP
        GreenfootImage img = new GreenfootImage("Map1.png");

        img.scale(1620, 1080);
        setBackground(img);

        //addObject(new UpgradeMenu(),800, 1000); <- exact placment


        addObject(new TrapTower(), 500,200);
        addObject(new EnemyLevel1(), 0,233);
        addObject(new EnemyLevel2(), 0,233);

        //addObject(new MapCoordinatesUtilGuy(), 0,0);
        //addObject(new TestTower(, 400), 5, 20);
        //addObject(new TestTower(false, 2000,4,5), 500, 300);
        int[][] pathLocations = {{0,233},{752,233},{752,531},{1414,531},{1414,984}};
        //int[][] pathLocations = {{0,140},{800,140},{800,350},{1680,350},{1680,600},{470,600},{470,970},{1920,970}};
        super.addPath(pathLocations);


        //addObject(new Path(null), 0 ,0);


    }


}
