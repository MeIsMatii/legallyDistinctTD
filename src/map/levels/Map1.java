package map.levels;

import entities.enemy.RedBloon;
import map.menu.PauseMenu;
import core.Player;
import entities.tower.TestTower;
import greenfoot.GreenfootImage;
import map.util.MapCoordinatesUtilGuy;
import ui.common.HomeButton;
import ui.settings.PlayOnButton;
import ui.settings.SettingsButton;


public class Map1 extends Map {


    public Map1() {
        //setBackground("Map1.jpg");
        setPaintOrder(SettingsButton.class,HomeButton.class, PlayOnButton.class,PauseMenu.class);

        /// TODO BETTER MAP
        GreenfootImage img = new GreenfootImage("Map1.jpg");

        img.scale(1620, 1080);
        setBackground(img);

        //addObject(new UpgradeMenu(),800, 1000); <- exact placment



        //addObject(new RedBloon(2, 100), 1,138);
        //addObject(new MapCoordinatesUtilGuy(), 0,0);
        //addObject(new TestTower(, 400), 5, 20);
        addObject(new TestTower(false, 2000,4,5), 500, 300);
        int[][] pathLocations = {{1,138},{666,138},{666,355},{1407,355},{1407,614},{429,614},{429,991},{1620,991}};
        //int[][] pathLocations = {{0,140},{800,140},{800,350},{1680,350},{1680,600},{470,600},{470,970},{1920,970}};
        super.addPath(pathLocations);


        //addObject(new Path(null), 0 ,0);


    }


}
