package map.map;

import greenfoot.World;
import map.util.ComingSoon;

public class Map7 extends World {
    public Map7() {
        super(9, 9, 60);
        setBackground("cell.jpg");
        addObject(new ComingSoon(), 6, 6);

    }
}
