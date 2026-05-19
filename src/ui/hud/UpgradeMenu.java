package ui.hud;

import entities.tower.Tower;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

public class UpgradeMenu extends Actor {
    private final Tower TOWER;
    public UpgradeMenu(Tower tower) {
        setImage("comingSoon.png");
        getImage().scale(1620, 216);
        this.TOWER = tower;
    }

    protected void addedToWorld(World world) {
        getWorld().addObject(new UpgradePath(TOWER), getX(), getY());
        getWorld().addObject(new UpgradePath(TOWER), getX() - 500, getY());
        getWorld().addObject(new UpgradePath(TOWER), getX() + 500, getY());
    }

    public Tower getTower() {
        return this.TOWER;
    }
}