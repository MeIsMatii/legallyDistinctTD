package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.HomingProjectile;
import greenfoot.GreenfootImage;

public class HomingTower extends Tower {

    private int[] upgrades1 = new int[]{150,500,2500,7500,17000};
    private int[] upgrades2 = new int[]{200,450,3000,10000,25000};
    private int[] upgrades3 = new int[]{100,350,1750,6000, 9500};


    public HomingTower() {
        super(100,true,  300, 10, 20, 30, 2, 10);

        //GreenfootImage img = new GreenfootImage("MageTower.png");
        //img.scale(120, 100);
        //setImage(img);

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

    public void act() {
        if (isPaused()) {
            return;
        }
        super.act();
    }

    public String upgrade1() {
        setUpgrade1(getUpgrade1()+1);
        return "";
    }

    public String upgrade2() {
        setUpgrade2(getUpgrade2()+1);
        return "";
    }

    public String upgrade3() {
        setUpgrade3(getUpgrade3()+1);
        return "";
    }

    void shoot(Enemy e) {
        getWorld().addObject(new HomingProjectile(this), getX(), getY());
    }

    @Override
    public String getTowerName() {
        return "HomingTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
    }
}
