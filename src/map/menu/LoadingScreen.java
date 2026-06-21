package map.menu;

import greenfoot.Greenfoot;
import greenfoot.World;

public class LoadingScreen extends World {

    private World nextWorld;

    public World getNextWorld() {
        return nextWorld;
    }

    public void setNextWorld(World netxWorld) {
        this.nextWorld = netxWorld;
    }

    public LoadingScreen() {
        super(9, 9, 60);
        setBackground("LoadingScreen.png");
        //setNextWorld(nextWorld);
    }

    @Override
    public void act() {
        loadNewMap();
    }

    private void loadNewMap() {
        World nextworld = getNextWorld();
        Greenfoot.setWorld(nextworld);
    }
    /// TODO finish loading screens --Colin
}
