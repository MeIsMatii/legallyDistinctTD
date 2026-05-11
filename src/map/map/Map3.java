package map.map;

import hud.Player;
import greenfoot.World;
import util.HomeButton;
import util.ImageDisplay;

public class Map3 extends Map {
    public Map3() {
        setBackground("cell.jpg");
        addObject(new ImageDisplay("comingSoon.png",120,100), 6, 6);
        addObject(new Player(),6,6);
        addObject(new HomeButton(),0,0);

    }
}
