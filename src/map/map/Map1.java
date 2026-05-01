package map.map;

import entity.Player;
import greenfoot.World;
import util.HomeButton;

public class Map1 extends World {

    public Map1() {
        super(70, 45, 60);
        setBackground("Map1.jpg");
        addObject(new HomeButton(),1,1);
        addObject(new Player(),6,6);
    }


}
