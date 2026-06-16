package entities.tower;

import entities.enemy.Enemy;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Sniper extends Tower{
    private int[] upgrades1 = new int[]{150,500,2500,7500,17000};
    private int[] upgrades2 = new int[]{200,450,3000,10000,25000};
    private int[] upgrades3 = new int[]{100,350,1750,6000, 9500};

    public Sniper() {
        super(200, true, 2000, 100, 10, 0, 0, 0);
        GreenfootImage img = new GreenfootImage("sniperTower.png");
        img.scale(200, 200);
        setImage(img);
    }
    public int[] getUpgrades1() {
        return upgrades1;
    }

    public int[] getUpgrades2() {
        return upgrades2;
    }

    public int[] getUpgrades3() {
        return upgrades3;
    }

    @Override
    public String upgrade1() {
        setUpgrade1(getUpgrade1()+1);
        return "";
    }

    @Override
    public String upgrade2() {
        setUpgrade1(getUpgrade2()+1);
        return "";
    }

    @Override
    public String upgrade3() {
        setUpgrade1(getUpgrade3()+1);
        return "";
    }

    @Override
    void shoot(Enemy e) {
        e.damage(getProjectileDamage());
    }


}
