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
    public void onClick() {

    }

    public void allTheUpgrades() {
    }

    @Override
    protected void addedToWorld(World world) {
        ImageDisplay imageDisplay = new ImageDisplay("TestTower2_2.png",30,40);
        getWorld().addObject(imageDisplay,200,200);
    }

    @Override
    public void act() {
        checkClick();
        allTheUpgrades();

        super.act();
    }
}
