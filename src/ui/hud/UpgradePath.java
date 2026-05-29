package ui.hud;

import entities.tower.TestTower;
import entities.tower.Tower;
import greenfoot.Actor;
import entities.tower.upgrades.Upgrade;
import greenfoot.World;

public class UpgradePath extends Actor {
    private Tower tower;
    private int path;

    public UpgradePath(Tower TOWER, int path) {
        setImage("comingSoon.png");
        this.path = path;
        this.tower = TOWER;
    }

    @Override
    protected void addedToWorld(World world) {
        if (path == 1){
            tower.upgrade1();
        } else if (path == 2) {
            tower.upgrade2();
        } else if (path == 3) {
            tower.upgrade3();
        }else{

        }
    }
}