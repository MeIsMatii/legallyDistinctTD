package ui.hud;

import core.Clickable;
import core.MainClass;
import entities.tower.Flamethrower;
import entities.tower.HomingTower;
import entities.tower.TestTower;
import entities.tower.TrapTower;
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
    }

    public void onClick() {

    }


    @Override
    public void act() {
        checkClick();

        super.act();
    }
}
