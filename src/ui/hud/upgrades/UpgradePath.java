package ui.hud.upgrades;

import core.Player;
import entities.base.Tower;
import greenfoot.*;
import maps.levels.GameMap;
import ui.hud.UpgradeDescriptionOverlay;
import util.Clickable;

import java.util.List;
/**
 * @author Mathilo
 * @author Elias
 */
public class UpgradePath extends Actor implements Clickable {
    private final Tower tower;
    private final int path;
    private final GreenfootImage imageNoPrice;
    private int oldCoins = 0;
    private boolean isPriceVisible = true;


    public UpgradePath(Tower TOWER, int path) {
        setImage("upgradeNew.png");
        imageNoPrice = getImage();
        imageNoPrice.setFont(new Font("Arial", true, false, 20));

        this.path = path;
        this.tower = TOWER;
    }

    @Override
    protected void addedToWorld(World world) {
        int maxPath = getMaxPath();

        updateText(maxPath);
        updatePrice(maxPath);

        getWorld().addObject(new UpgradeDescriptionOverlay(tower, this.path, maxPath), getX(), getY());

    }

    @Override
    public void onClick() { // i have added a few sectioning comments because i could not read this --Mathilo
        //VARIABLES
        List<Player> player = getWorld().getObjects(Player.class);
        if (player.isEmpty()) return;
        Player player1 = player.get(0);

        int upgradeLevel = getCurrentUpgradeLevel();
        int[] upgrades = getUpgradePrices();

        if (upgrades == null) return;

        int otherUpgradeA = getOtherUpgradeA();
        int otherUpgradeB = getOtherUpgradeB();

        //PATH LOCKING
        if (otherUpgradeA > 0 && otherUpgradeB > 0) {
            System.out.println("locked case " + this.path);
            updateText(0);
            updatePrice(0);
            return;
        }

        int maxPath = getMaxPath();

        if (upgradeLevel >= upgrades.length || upgradeLevel >= maxPath) {
            System.out.println("Max upgrade reached");
            updatePrice(maxPath);
            return;
        }

        int price = upgrades[upgradeLevel];

        if (player1.getCoins() < price) {
            return;
        }

        //UPGRADE LOGIC (rm coins and upgrade)
        player1.setCoins(player1.getCoins() - price);

        switch (this.path) {
            case 1: tower.upgrade1(); break;
            case 2: tower.upgrade2(); break;
            case 3: tower.upgrade3(); break;
        }

        //OVERLAY
        removeOverlay();
        getWorld().addObject(new UpgradeDescriptionOverlay(tower, this.path, maxPath), getX(), getY());
        //HUD
        updateText(maxPath);
        updatePrice(maxPath);
    }

    public void act() {
        checkClick();
        checkText();
        updatePrice();
    }

    public void checkText() {
        int maxPath = getMaxPath();
        updateText(maxPath);
        updatePrice(maxPath);
    }

    public void updatePrice(int maxPath) {
        int upgradeLevel = getCurrentUpgradeLevel();
        int[] upgrades = getUpgradePrices();

        if (upgrades == null) return;

        if (upgradeLevel >= upgrades.length || upgradeLevel >= maxPath) {
            setImage(imageNoPrice);
            isPriceVisible = false;
            return;
        }
        updatePrice();
    }

    /**
     * updates the colour of the price
     */
    public void updatePrice() {
        int coins = getWorldOfType(GameMap.class).getPlayer().getCoins();
        int upgradeLevel = getCurrentUpgradeLevel();
        int[] upgrades = getUpgradePrices();

        if(coins == oldCoins || upgradeLevel >= upgrades.length || !isPriceVisible) {
            return;
        }
        oldCoins = coins;

        int price = upgrades[upgradeLevel];



        GreenfootImage img = new GreenfootImage(imageNoPrice);
        if(coins >= price) {
            img.setColor(Color.GREEN);
        } else {
            img.setColor(Color.RED);
        }

        img.drawString(String.valueOf(price + "$"), getImage().getWidth()/2 -30, 30);
        setImage(img);
    }


    public void updateText(int maxPath) {
        int currentUpgrade = getCurrentUpgradeLevel();

        if(maxPath == 0) {
            removeOverlay();
            getWorld().addObject(new UpgradeDescriptionOverlay(tower, this.path, maxPath), getX(), getY());
            getWorld().showText("", getX(), getY() - 65); // so it does not display "0/0"
        }

        getWorld().showText(currentUpgrade + " / " + maxPath, getX(), getY() + 65);
    }

    public void onRemove() {
        removeOverlay();
        getWorld().showText("", getX(), getY() - 65); // so it does not display tier
        removeOverlay();
        getWorld().removeObject(this);
    }


    //HELPER METHODS (so i can read the code, this was sooooo difficult to read -- Mathilo)

    private int getMaxPath() {
        int otherA = getOtherUpgradeA();
        int otherB = getOtherUpgradeB();

        if (otherA > 0 && otherB > 0) return 0;
        if (Math.max(otherA, otherB) >= 2) return 1;
        return 3;
    }

    private int getOtherUpgradeA() {
        switch (this.path) {
            case 1:  return tower.getUpgrade2();
            case 2://case 3 and 2 do the same thing
            case 3:
                return tower.getUpgrade1();
            default: return 0;
        }
    }

    private int getOtherUpgradeB() {
        switch (this.path) {
            case 1: //case 1 and 2 do the same thing
            case 2:
                return tower.getUpgrade3();
            case 3:  return tower.getUpgrade2();
            default: return 0;
        }
    }

    private void removeOverlay() {
        List<UpgradeDescriptionOverlay> overlays = getWorld().getObjects(UpgradeDescriptionOverlay.class);
        for (UpgradeDescriptionOverlay overlay : overlays) {
            if (overlay.getPath() == this.path) {
                getWorld().removeObject(overlay);
                break;
            }
        }
    }

    private int getCurrentUpgradeLevel() {
        switch (this.path) {
            case 1: return tower.getUpgrade1();
            case 2: return tower.getUpgrade2();
            case 3: return tower.getUpgrade3();
            default: return 0;
        }
    }

    private int[] getUpgradePrices() {
        switch (this.path) {
            case 1: return tower.getUpgrade1Prices();
            case 2: return tower.getUpgrade2Prices();
            case 3: return tower.getUpgrade3Prices();
            default: return null;
        }
    }


}