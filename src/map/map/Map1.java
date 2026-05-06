package map.map;

import hud.Player;
import greenfoot.World;
import util.HomeButton;

public class Map1 extends World {

    Player player = new Player();                 //Jannis

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map1() {
        super(70, 45, 60);
        setBackground("Map1.jpg");
        addObject(new HomeButton(),1,1);
        addObject(new Player(),6,6);

    }



}
