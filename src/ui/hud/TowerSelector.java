package ui.hud;

import core.Clickable;
import core.MainClass;
import entities.tower.TestTower;
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
        getWorld().addObject(new  TowerInHud(),1700,460);
    }

    public void onClick() {

    }


    @Override
    public void act() {
        checkClick();

        super.act();
    }
}
