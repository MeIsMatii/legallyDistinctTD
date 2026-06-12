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
        int maxPath = 5;


        switch (this.path) {
            case 1:
                if (tower.getUpgrade2() > 0 && tower.getUpgrade3() > 0) {
                    System.out.println("locked case 1");
                    break;
                }

                //max. 1 bought
                maxUpgrade = Math.max(tower.getUpgrade2(), tower.getUpgrade3());
                if (maxUpgrade >= 3) {
                    maxPath = 2;
                }
                if (tower.getUpgrade1() < maxPath) {
                    player1.setCoins(player1.getCoins() - 500);
                    tower.upgrade1();
                }

                break;
            case 2:
                if (tower.getUpgrade1() > 0 && tower.getUpgrade3() > 0) {
                    System.out.println("locked case 2");
                    break;
                }
                maxUpgrade = Math.max(tower.getUpgrade1(), tower.getUpgrade3());
                if (maxUpgrade >= 3) {
                    maxPath = 2;
                }
                if (tower.getUpgrade2() < maxPath) {
                    player1.setCoins(player1.getCoins() - 500);
                    tower.upgrade2();
                }
                break;
            case 3:
                if (tower.getUpgrade1() > 0 && tower.getUpgrade2() > 0) {
                    System.out.println("locked case 3");
                    break;
                }
                maxUpgrade = Math.max(tower.getUpgrade1(), tower.getUpgrade2());
                if (maxUpgrade >= 3) {
                    maxPath = 2;
                }
                if (tower.getUpgrade3() < maxPath) {
                    player1.setCoins(player1.getCoins() - 500);
                    tower.upgrade3();
                }

                break;
            default:
                System.out.println("upgrade path must be 0<x<=3");
                break;
        }
    }
    public void act() {
        checkClick();
    }
}