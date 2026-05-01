package map.map;

import entity.Player;
import greenfoot.World;
import util.HomeButton;

public class Map1 extends World {

    public Map1() {
        super(14, 9, 60);
        setBackground("map1placeholder.jpeg");
        addObject(new HomeButton(),1,1);
        addObject(new Player(),6,6);
    }


}
