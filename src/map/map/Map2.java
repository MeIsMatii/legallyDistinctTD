package map.map;

import hud.Player;
import greenfoot.World;
import util.HomeButton;
import util.ImageDisplay;

public class Map2 extends World {
    public Map2() {
        super(9, 9, 60);
        setBackground("cell.jpg");
        addObject(new ImageDisplay("comingSoon.png",120,100), 6, 6);
        addObject(new Player(),6,6);
        addObject(new HomeButton(),0,0);

    }
}
