package map.map;

import greenfoot.World;
import map.util.ComingSoon;

public class Map1 extends World {

    public Map1() {
        super(14, 9, 60);
        setBackground("map1placeholder.jpeg");
        addObject(new ComingSoon(), 6, 6);

    }


}
