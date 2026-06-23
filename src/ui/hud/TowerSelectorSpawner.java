package ui.hud;

import util.Clickable;
import core.MainClass;
import entities.tower.*;
import greenfoot.World;

public class TowerSelectorSpawner extends MainClass implements Clickable {

    private boolean isPlacing;
    private boolean canPlace;

    public TowerSelectorSpawner() {
        setImage("upgradesPrototype.png");


    }

    @Override
    protected void addedToWorld(World world) {
        getWorld().addObject(new TowerSelector(new TestTower()),1700,460);
        getWorld().addObject(new TowerSelector(new HomingTower()),1700,660);
        getWorld().addObject(new TowerSelector(new TrapTower()),1900,460);
        getWorld().addObject(new TowerSelector(new Flamethrower()),1900,660);
        getWorld().addObject(new TowerSelector(new Sniper()),1700,860);
        getWorld().addObject(new TowerSelector(new Rocketlauncher()),1700,60); // temp,TODO needs to be changed later
        getWorld().addObject(new TowerSelector(new HelicopterPad()),1900, 860);
    }

    public void onClick() {

    }


    @Override
    public void act() {
        checkClick();

        super.act();
    }
}
