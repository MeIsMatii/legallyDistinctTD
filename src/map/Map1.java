package map;

import greenfoot.World;

public class Map1 extends World {

    public Map1() {
        super(9, 9, 60);
        setBackground("cell.jpg");
        addObject(new ComingSoon(), 6, 6);

    }


}
