package ui.hud;

import core.Clickable;
import core.MainClass;
import entities.tower.*;
import greenfoot.World;
import ui.common.ImageDisplay;

public class TowerSelector extends MainClass implements Clickable {

    private boolean isPlacing;
    private boolean canPlace;

    public TowerSelector() {
        setImage("upgradesPrototype.png");


    }

    @Override
    protected void addedToWorld(World world) {
        getWorld().addObject(new  TowerInHud(new TestTower()),1700,460);
        getWorld().addObject(new  TowerInHud(new HomingTower()),1700,660);
        getWorld().addObject(new  TowerInHud(new TrapTower()),1900,460);
        getWorld().addObject(new  TowerInHud(new Flamethrower()),1900,660);
        getWorld().addObject(new  TowerInHud(new Sniper()),1700,860);
        getWorld().addObject(new  TowerInHud(new Rocketlauncher()),1700,60); // temp,TODO needs to be changed later

    }

    public void onClick() {

    }


    @Override
    public void act() {
        checkClick();

        super.act();
    }
}
