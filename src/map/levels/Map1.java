package map.levels;

import core.Player;
import entities.tower.TestTower;
import greenfoot.GreenfootImage;
import ui.common.HomeButton;


public class Map1 extends Map {


    public Map1() {
        //setBackground("Map1.jpg");

        /// TODO BETTER MAP
        GreenfootImage img = new GreenfootImage("Map1.jpg");

        img.scale(1620, 1080);
        setBackground(img);

        //addObject(new UpgradeMenu(),800, 1000); <- exact placment

        addObject(new HomeButton(), 1, 1);
        addObject(new Player(), 6, 6);

        addObject(new TestTower(true, 400), 5, 20);
        addObject(new TestTower(false, 200), 500, 300);
        int[][] pathLocations = {{0,140},{800,140},{800,350},{1680,350},{1680,600},{470,600},{470,970},{1920,970}};
        super.addPath(pathLocations);


        //addObject(new Path(null), 0 ,0);


    }


}
