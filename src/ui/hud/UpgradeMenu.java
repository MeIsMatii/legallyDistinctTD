package ui.hud;

import entities.tower.TestTower;
import entities.tower.Tower;
import greenfoot.Actor;
import greenfoot.World;

public class UpgradeMenu extends Actor {
    private final Tower TOWER;
    private UpgradePath path1;
    private UpgradePath path2;
    private UpgradePath path3;

    public UpgradeMenu(Tower tower) {
        setImage("comingSoon.png");
        getImage().scale(1620, 216);
        this.TOWER = tower;
        path1 = new UpgradePath(TOWER, 1);
        path2 = new UpgradePath(TOWER, 2);
        path3 = new UpgradePath(TOWER, 3);
    }

    protected void addedToWorld(World world) {
        getWorld().addObject(path1, getX(), getY());
        getWorld().addObject(path2, getX() - 500, getY());
        getWorld().addObject(path3, getX() + 500, getY());
    }

    public Tower getTower() {
        return this.TOWER;
    }

    public void delete(){
        getWorld().removeObject(path1);
        getWorld().removeObject(path2);
        getWorld().removeObject(path3);
        getWorld().removeObject(this);
    }
}