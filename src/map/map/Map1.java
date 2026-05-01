package map.map;

import entity.Player;
import entity.tower.TestTower;
import greenfoot.World;

public class Map1 extends World {

    public Map1() {
        super(14, 9, 60);
        setBackground("map1placeholder.jpeg");
        addObject(new TestTower(false, 2), 1, 1);


        addObject(new TestTower(true, 4), 1,1);
    }


}
