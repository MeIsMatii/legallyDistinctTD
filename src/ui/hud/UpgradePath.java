package ui.hud;

import entities.tower.Tower;
import greenfoot.Actor;
import entities.tower.upgrades.Upgrade;
import greenfoot.World;

public class UpgradePath extends Actor {
    public UpgradePath(Tower TOWER, int path) {
        setImage("comingSoon.png");
        this.path = path;
    }

    private int path;

    @Override
    protected void addedToWorld(World world) {
        if (path == 1){
        } else if (path == 2) {

        } else if (path == 3) {

        }else{
            System.out.println("Fuck you, choose a number between 1 and 3 nex time");
        }
    }
}