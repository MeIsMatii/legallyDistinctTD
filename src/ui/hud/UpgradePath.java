package ui.hud;

import core.Clickable;
import entities.tower.Tower;
import greenfoot.Actor;
import core.Player;
import greenfoot.World;
import java.util.List;

public class UpgradePath extends Actor implements Clickable {
    private Tower tower;
    private int path;

    public UpgradePath(Tower TOWER, int path) {
        setImage("comingSoon.png");
        this.path = path;
        this.tower = TOWER;
    }



    @Override
    public void onClick() {
        List<Player> player = getWorld().getObjects(Player.class);
        Player player1 = player.get(0);
        if(player1.getCoins() < 500){
            return;
        }
        player1.setCoins(player1.getCoins() - 500);
        if (path == 1){
            tower.upgrade1();
        } else if (path == 2) {
            tower.upgrade2();
        } else if (path == 3) {
            tower.upgrade3();
        }
    }

    @Override
    public void act() {
        checkClick();
    }
}