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
        getWorld().addObject(new TowerSelector(TestTower::new), getWorld().getWidth() - 140, getWorld().getHeight() - 695);
        getWorld().addObject(new TowerSelector(HomingTower::new), getWorld().getWidth() - 210, getWorld().getHeight() - 505);
        getWorld().addObject(new TowerSelector(TrapTower::new), getWorld().getWidth() - 70, getWorld().getHeight() - 505);
        getWorld().addObject(new TowerSelector(Flamethrower::new), getWorld().getWidth() - 210, getWorld().getHeight() - 315);
        getWorld().addObject(new TowerSelector(Sniper::new), getWorld().getWidth() - 70, getWorld().getHeight() - 315);
        getWorld().addObject(new TowerSelector(Rocketlauncher::new), getWorld().getWidth() - 70, getWorld().getHeight() - 125); // temp,TODO needs to be changed later
        getWorld().addObject(new TowerSelector(IceTower::new), getWorld().getWidth() - 210, getWorld().getHeight() - 125);

    }
}
