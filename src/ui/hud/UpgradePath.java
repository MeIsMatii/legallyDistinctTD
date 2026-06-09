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
        if (player1.getCoins() < 500) {
            return;
        }
        int maxUpgrade;
        switch (this.path) {
            case 1:
                if(tower.getUpgrade2() > 0 && tower.getUpgrade3() > 0) {
                    //locked
                    break;
                }
                //max. 1 bought
                maxUpgrade = Math.max(tower.getUpgrade2(), tower.getUpgrade3());


                break;
            case 2:
                if(tower.getUpgrade1() > 0 && tower.getUpgrade3() > 0) {
                    //locked
                    break;
                }
                maxUpgrade = Math.max(tower.getUpgrade1(), tower.getUpgrade3());
                break;
            case 3:
                if(tower.getUpgrade1() > 0 && tower.getUpgrade2() > 0) {
                    //locked
                    break;
                }
                maxUpgrade = Math.max(tower.getUpgrade1(), tower.getUpgrade2());


                break;
            default:
                System.out.println("upgrade path must be 0<x<=3");
                break;
        }




        if (path == 1) {
            if (tower.getUpgrade2() <= 3 && tower.getUpgrade3() == 1 || tower.getUpgrade3() <= 3 && tower.getUpgrade2() == 1) {
                if (tower.getUpgrade1() <= 5) {
                    player1.setCoins(player1.getCoins() - 500);
                    tower.upgrade1();
                }
            }
        } else if (path == 2) {
            if (tower.getUpgrade1() <= 3 && tower.getUpgrade3() == 1 || tower.getUpgrade3() <= 3 && tower.getUpgrade1() == 1) {
                if (tower.getUpgrade2() <= 5) {
                    player1.setCoins(player1.getCoins() - 500);
                    tower.upgrade2();
                }
            }

        } else if (path == 3) {
            if (tower.getUpgrade2() <= 3 && tower.getUpgrade1() == 1 || tower.getUpgrade1() <= 3 && tower.getUpgrade2() == 1) {
                if (tower.getUpgrade3() <= 5) {
                    player1.setCoins(player1.getCoins() - 500);
                    tower.upgrade3();
                }
            }
        }
    }

    @Override
    public void act() {
        checkClick();
    }
}