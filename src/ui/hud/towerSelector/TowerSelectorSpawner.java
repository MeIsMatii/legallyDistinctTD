package ui.hud.towerSelector;

import core.MainClass;
import entities.tower.*;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class TowerSelectorSpawner extends MainClass {
    /**
     * @author Mathilo
     */
    public TowerSelectorSpawner() {
        GreenfootImage img = new GreenfootImage("towerSelectorMenu.png");
        setImage(img);


    }

    @Override
    protected void addedToWorld(World world) {
        getWorld().addObject(new TowerSelector(TestTower::new), 1700, 460);
        getWorld().addObject(new TowerSelector(HomingTower::new), 1700, 660);
        getWorld().addObject(new TowerSelector(TrapTower::new), 1900, 460);
        getWorld().addObject(new TowerSelector(Flamethrower::new), 1900, 660);
        getWorld().addObject(new TowerSelector(Sniper::new), 1700, 860);
        getWorld().addObject(new TowerSelector(Rocketlauncher::new), 1700, 60); // temp,TODO needs to be changed later
        getWorld().addObject(new TowerSelector(HelicopterPad::new), 1900, 860);
        getWorld().addObject(new TowerSelector(IceTower::new), 1900, 900);
    }
}
