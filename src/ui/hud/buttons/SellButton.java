package ui.hud.buttons;

import core.Player;
import entities.tower.Tower;
import greenfoot.GreenfootImage;
import ui.hud.UpgradePath;

public class SellButton extends Button{

    private Tower TOWER;
    private Player PLAYER;

    public SellButton(Tower tower, Player player) {
        this.TOWER = tower;
        this.PLAYER = player;
        GreenfootImage img = new GreenfootImage("sellButton.png");
        img.scale(100, 50);
        setImage(img);
    }

    @Override
    public void onClick() {
        getWorld().removeObject(TOWER);
        PLAYER.setCoins(PLAYER.getCoins()+TOWER.getPRICE()/2);
        getWorld().removeObject(this);
    }
}
