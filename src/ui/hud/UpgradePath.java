package ui.hud;

import greenfoot.World;
import util.Clickable;
import entities.tower.Tower;
import greenfoot.Actor;
import core.Player;

import java.util.List;

public class UpgradePath extends Actor implements Clickable {
    private final Tower tower;
    private final int path;


    public UpgradePath(Tower TOWER, int path) {
        setImage("upgrade.png");
        this.path = path;
        this.tower = TOWER;
    }

    @Override
    protected void addedToWorld(World world) {
        updateText(3);
        getWorld().addObject(new UpgradeDescriptionOverlay(tower, this.path, 3), getX(), getY());


        switch(path) {
            case 1:
                getWorld().showText(tower.getUpgrade1Prices()[0] + "$", getX(), getY() - 65);
                break;
            case 2:
                getWorld().showText(tower.getUpgrade2Prices()[0] + "$", getX(), getY() - 65);
                break;
            case 3:
                getWorld().showText(tower.getUpgrade3Prices()[0] + "$", getX(), getY() - 65);
                break;
            default:
                System.out.println("upgrade path must be 0<x<4");
                return;

        }
    }

    @Override
    public void onClick() {
        List<Player> player = getWorld().getObjects(Player.class);
        if (player.isEmpty()) return;
        Player player1 = player.get(0);

        int maxUpgrade;
        int maxPath = 3;

        int upgradeLevel; //current price
        int[] upgrades; //all the prices
        int otherUpgradeA; //e.g. path = 1; then otherUpgradeA = 2; otherUpgradeB = 3;
        int otherUpgradeB;

        List<UpgradeDescriptionOverlay> upgradedesc = getWorld().getObjects(UpgradeDescriptionOverlay.class);
        UpgradeDescriptionOverlay upgradedesctoremove = null;
        for (UpgradeDescriptionOverlay upgradeDescription : upgradedesc) {
            if(upgradeDescription.getPath() == this.path) {
                upgradedesctoremove = upgradeDescription;
            }
        }
        switch (this.path) {
            case 1:
                upgradeLevel = tower.getUpgrade1();
                upgrades = tower.getUpgrade1Prices();
                otherUpgradeA = tower.getUpgrade2();
                otherUpgradeB = tower.getUpgrade3();
                break;
            case 2:
                upgradeLevel = tower.getUpgrade2();
                upgrades = tower.getUpgrade2Prices();
                otherUpgradeA = tower.getUpgrade1();
                otherUpgradeB = tower.getUpgrade3();
                break;
            case 3:
                upgradeLevel = tower.getUpgrade3();
                upgrades = tower.getUpgrade3Prices();
                otherUpgradeA = tower.getUpgrade1();
                otherUpgradeB = tower.getUpgrade2();
                break;
            default:
                System.out.println("upgrade path must be 0<x<4");
                return;
        }



        int price = upgrades[upgradeLevel];

        if (player1.getCoins() < price) {
            return;
        }

        if (otherUpgradeA > 0 && otherUpgradeB > 0) {
            System.out.println("locked case " + this.path);
            updateText(0);
            return;
        }

        maxUpgrade = Math.max(otherUpgradeA, otherUpgradeB);
        if (maxUpgrade >= 2) {
            maxPath = 1;
        }

        if(upgradeLevel +1 >= maxPath) {
            getWorld().showText("", getX(), getY() - 65);
        } else {
            getWorld().showText(price + "$", getX(), getY() - 65);
        }

        if (upgradeLevel < maxPath) {
            player1.setCoins(player1.getCoins() - price);
            //System.out.println(price);

            switch (this.path) {
                case 1: tower.upgrade1(); break;
                case 2: tower.upgrade2(); break;
                case 3: tower.upgrade3(); break;
            }
            getWorld().removeObject(upgradedesctoremove);
            getWorld().addObject(new UpgradeDescriptionOverlay(tower, this.path, maxPath), getX(),getY());

        }

        updateText(maxPath);
    }

    public void act() {
        checkClick();
        checkText();
    }

    public void checkText() {
        switch (this.path) {
            case 1:
                if(tower.getUpgrade2() > 0 && tower.getUpgrade3() > 0) {
                    updateText(0);
                } else if(tower.getUpgrade2() > 1 ^ tower.getUpgrade3() > 1) { // ^ is XOR
                    updateText(1);
                }
                break;
            case 2:
                if(tower.getUpgrade1() > 0 && tower.getUpgrade3() > 0) {
                    updateText(0);
                } else if(tower.getUpgrade1() > 1 ^ tower.getUpgrade3() > 1) {
                    updateText(1);
                }
                break;
            case 3:
                if(tower.getUpgrade1() > 0 && tower.getUpgrade2() > 0) {
                    updateText(0);
                } else if(tower.getUpgrade1() > 1 ^ tower.getUpgrade2() > 1) {
                    updateText(1);
                }
                break;
        }
    }

    public void updateText(int maxPath) {
        int currentUpgrade;
        switch (this.path) {
            case 1:
                currentUpgrade = tower.getUpgrade1();
                break;
            case 2:
                currentUpgrade = tower.getUpgrade2();
                break;
            case 3:
                currentUpgrade = tower.getUpgrade3();
                break;
            default:
                System.out.println("upgrade path must be 0<x<4");
                return;
        }

        if(maxPath == 0) {
            List<UpgradeDescriptionOverlay> upgradedesc = getWorld().getObjects(UpgradeDescriptionOverlay.class);
            UpgradeDescriptionOverlay upgradedesctoremove = null;
            for (UpgradeDescriptionOverlay upgradeDescription : upgradedesc) {
                if(upgradeDescription.getPath() == this.path) {
                    upgradedesctoremove = upgradeDescription;
                }
            }
            getWorld().removeObject(upgradedesctoremove);
            getWorld().addObject(new UpgradeDescriptionOverlay(tower, this.path, maxPath), getX(),getY());

            getWorld().showText("", getX(), getY() - 65); //you dont need price when you are finished
        }

        getWorld().showText(currentUpgrade + " / " + maxPath, getX(), getY() + 65);
    }

    public void onRemove() {
        List<UpgradeDescriptionOverlay> upgradedesc = getWorld().getObjects(UpgradeDescriptionOverlay.class);
        UpgradeDescriptionOverlay upgradedesctoremove = null;
        for (UpgradeDescriptionOverlay upgradeDescription : upgradedesc) {
            if(upgradeDescription.getPath() == this.path) {
                upgradedesctoremove = upgradeDescription;
            }
        }
        getWorld().removeObject(upgradedesctoremove);

        getWorld().showText("", getX(), getY() + 65); //to delete the text
        getWorld().showText("", getX(), getY() - 65);

        getWorld().removeObject(this);

    }
}