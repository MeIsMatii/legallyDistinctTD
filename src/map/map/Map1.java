package map.map;

import entity.Entity;
import entity.tower.TestTower;
import hud.Player;
import greenfoot.World;
import map.util.Path;
import util.HomeButton;



public class Map1 extends Map {



    public Map1() {
        setBackground("Map1.jpg");
        addObject(new HomeButton(),1,1);
        addObject(new Player(),6,6);

        addObject(new TestTower(true, 200), 5, 20);
        addObject(new TestTower(false, 200), 500, 200);
        int[][] pathLocations = {{500,100},{200,100}, {200, 400}, {100,400}};
        super.addPath(pathLocations);



        //addObject(new Path(null), 0 ,0);



    }


}
