package map.map;

import entity.tower.TestTower;
import hud.Player;
import util.HomeButton;


public class Map1 extends Map {


    public Map1() {
        setBackground("Map1.jpg");
        addObject(new HomeButton(), 1, 1);
        addObject(new Player(), 6, 6);

        addObject(new TestTower(true, 200), 5, 20);
        addObject(new TestTower(false, 200), 500, 200);
        int[][] pathLocations = {{0, 200}, {300, 200}, {300, 600}, {700, 600}, {700, 300}, {1200, 300}, {1200, 800}, {500, 800}, {500, 500}, {900, 500}, {900, 1080}};
        super.addPath(pathLocations);


        //addObject(new Path(null), 0 ,0);


    }


}
