package map.map;

import greenfoot.World;
import map.util.ComingSoon;

public class Map8 extends World {
    public Map8() {
        super(9, 9, 60);
        setBackground("cell.jpg");
        addObject(new ComingSoon(), 6, 6);

    }
}
