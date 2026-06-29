package ui.hud.buttons;

import core.Player;
import entities.tower.Tower;
import ui.hud.UpgradePath;

public class SellButton extends Button{

    private Tower TOWER;
    private Player PLAYER;

    public SellButton(Tower tower, Player player) {
        this.TOWER = tower;
        this.PLAYER = player;

        setImage("comingSoon.png");
        getImage().scale(30, 30);
    }


    @Override
    public void checkClick() {
        super.checkClick();
    }

    @Override
    public void onClick() {
        getWorld().removeObject(TOWER);
        PLAYER.setCoins(PLAYER.getCoins()+TOWER.getPRICE()/2);
        getWorld().removeObject(this);
    }
}
