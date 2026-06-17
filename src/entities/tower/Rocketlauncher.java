package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.Rocket;
import entities.projectiles.TestProjectile;
import greenfoot.GreenfootImage;

public class Rocketlauncher extends Tower{
    private int[] upgrades1 = new int[]{150,500,2500,7500,17000};
    private int[] upgrades2 = new int[]{200,450,3000,10000,25000};
    private int[] upgrades3 = new int[]{100,350,1750,6000, 9500};

    public Rocketlauncher() {
        super(500, true, 500, 250, 25, 25, 1, 0);
        GreenfootImage img = new GreenfootImage("Rocketlauncher.jpg");
        img.scale(200,200);
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

    public String upgrade1() {
        setUpgrade1(getUpgrade1()+1);
        System.out.println("flamethrower upgrade 1" + " " + getUpgrade1());
        return "flamethrower 1";
    }

    public String upgrade2() {
        setUpgrade2(getUpgrade2()+1);
        System.out.println("flamethrower upgrade 2" + " " + getUpgrade2());
        return "flamethrower 2";
    }

    public String upgrade3() {
        setUpgrade3(getUpgrade3()+1);
        System.out.println("flamethrower upgrade 3" + " " + getUpgrade3());
        return "flamethrower 3";
    }

    @Override
    public void shoot(Enemy e) {
        getWorld().addObject(new Rocket(this), getX(), getY());
    }
}