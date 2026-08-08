package maps.menu;

import greenfoot.Greenfoot;
import greenfoot.World;
import maps.util.CustomWorld;

/**
 * @author Colin
 * @author Mathilo
 */
public class LoadingScreen extends CustomWorld {

    private World nextWorld;

    public World getNextWorld() {
        return nextWorld;
    }

    public void setNextWorld(World netxWorld) {
        this.nextWorld = netxWorld;
    }

    public LoadingScreen() {
        super(1920, 1080, 1);
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

}
