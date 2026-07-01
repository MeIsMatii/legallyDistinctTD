package ui.hud;

import util.Clickable;
import entities.tower.Tower;
import greenfoot.Actor;
import core.Player;

import java.util.List;

public class UpgradePath extends Actor implements Clickable {
    private Tower tower;
    private int path;
    private int price;

    private int maxUpgradedPath = 1;

    public UpgradePath(Tower TOWER, int path) {
        setImage("upgrade.png");
        this.path = path;
        this.tower = TOWER;
    }


    @Override
    public void onClick() {
        List<Player> player = getWorld().getObjects(Player.class);
        Player player1 = player.get(0);

        int maxUpgrade;
        int maxPath = 3;


        switch (this.path) {
            case 1:
                if (tower.getUpgrade1() >= maxPath){
                    return;
                }
                int[] upgrades1 = tower.getUpgrades1();
                price = upgrades1[tower.getUpgrade1()];
                if (player1.getCoins() < price) {
                    return;
                }
                if (tower.getUpgrade2() > 0 && tower.getUpgrade3() > 0) {
                    System.out.println("locked case 1");
                    break;
                }

                //max. 1 bought
                maxUpgrade = Math.max(tower.getUpgrade2(), tower.getUpgrade3());
                if (maxUpgrade >= 2) {
                    maxPath = this.maxUpgradedPath;
                }
                if (tower.getUpgrade1() < maxPath) {
                    player1.setCoins(player1.getCoins() - price);
                    System.out.println(price);
                    tower.upgrade1();
                }

                break;
            case 2:
                if (tower.getUpgrade2() >= maxPath){
                    return;
                }
                int[] upgrades2 = tower.getUpgrades2();
                price = upgrades2[tower.getUpgrade2()];
                if (tower.getUpgrade1() > 0 && tower.getUpgrade3() > 0) {
                    System.out.println("locked case 2");
                    break;
                }
                maxUpgrade = Math.max(tower.getUpgrade1(), tower.getUpgrade3());
                if (maxUpgrade >= 2) {
                    maxPath = maxUpgradedPath;
                }
                if (tower.getUpgrade2() < maxPath) {
                    player1.setCoins(player1.getCoins() - price);
                    System.out.println(price);
                    tower.upgrade2();
                }
                break;
            case 3:
                if (tower.getUpgrade3() >= maxPath){
                    return;
                }
                int[] upgrades3 = tower.getUpgrades3();
                price = upgrades3[tower.getUpgrade3()];
                if (tower.getUpgrade1() > 0 && tower.getUpgrade2() > 0) {
                    System.out.println("locked case 3");
                    break;
                }
                maxUpgrade = Math.max(tower.getUpgrade1(), tower.getUpgrade2());
                if (maxUpgrade >= 2) {
                    maxPath = maxUpgradedPath;
                }
                if (tower.getUpgrade3() < maxPath) {
                    player1.setCoins(player1.getCoins() - price);
                    System.out.println(price);
                    tower.upgrade3();
                }

                break;
            default:
                System.out.println("upgrade path must be 0<x<4");
                break;
        }
    }
    public void act() {
        checkClick();
    }
}