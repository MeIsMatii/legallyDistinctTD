package map.map;

import greenfoot.World;
import map.util.ComingSoon;

public class Map6 extends World {
    public Map6() {
        super(9, 9, 60);
        setBackground("cell.jpg");
        addObject(new ComingSoon(), 6, 6);

    }
}
