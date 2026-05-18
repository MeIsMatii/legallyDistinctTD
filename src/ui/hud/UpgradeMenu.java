package ui.hud;

import entities.tower.Tower;
import greenfoot.Actor;

public class UpgradeMenu extends Actor {
    private final Tower TOWER;
    public UpgradeMenu(Tower tower) {
        setImage("comingSoon.png");
        getImage().scale(1620, 216);

        this.TOWER = tower;
    }

    public Tower getTower() {
        return this.TOWER;
    }
}