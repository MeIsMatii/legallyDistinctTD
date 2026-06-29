package ui.hud.buttons;

import core.Player;
import entities.tower.Tower;
import ui.hud.UpgradePath;

public class SellButton extends Button{

    private Tower TOWER;
    private Player PLAYER;

    public SellButton() {
        setImage("comingSoon.png");
        getImage().scale(30, 30);
    }


    @Override
    public void checkClick() {
        super.checkClick();
    }

    @Override
    public void onClick() {
        getWorld().removeObject(this);
        PLAYER.setCoins(TOWER.getPRICE()/2);
    }
}
