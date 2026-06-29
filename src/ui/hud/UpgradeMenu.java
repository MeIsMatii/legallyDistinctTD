package ui.hud;

import entities.tower.TestTower;
import entities.tower.Tower;
import greenfoot.Actor;
import greenfoot.World;
import ui.hud.buttons.SellButton;

public class UpgradeMenu extends Actor {
    private final Tower TOWER;
    private UpgradePath path1;
    private UpgradePath path2;
    private UpgradePath path3;
    private SellButton sellButton;

    public UpgradeMenu(Tower tower) {
        setImage("comingSoon.png");
        getImage().scale(1620, 216);
        this.TOWER = tower;
        path1 = new UpgradePath(TOWER, 1);
        path2 = new UpgradePath(TOWER, 2);
        path3 = new UpgradePath(TOWER, 3);
    }

    protected void addedToWorld(World world) {
        getWorld().addObject(path1,  getX() - 500, getY());
        getWorld().addObject(path2, getX(), getY());
        getWorld().addObject(path3, getX() + 500, getY());
        // getWorld().addObject(sellButton, getX() + 400, getY());  //weiß nicht wohin und stürzt irgendwie ab wenn ich dann das dings öffne
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