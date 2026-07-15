package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.HomingProjectile;
import greenfoot.GreenfootImage;

public class HomingTower extends Tower {

    private final int[] upgrades1 = new int[]{150,500,2500,7500,17000};
    private final int[] upgrades2 = new int[]{200,450,3000,10000,25000};
    private final int[] upgrades3 = new int[]{100,350,1750,6000, 9500};


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


    void shoot(Enemy e) {
        getWorld().addObject(new HomingProjectile(this), getX(), getY());
    }

    @Override
    public String getName() {
        return "HomingTower";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
    }

    public void upgrade(int path) {
        //TODO better upgrades
        switch (path) {
            case 1:
                switch (getUpgrade1()) {
                    case 1:
                        setRange((getRange() * 1.1));
                        break;
                    case 2:
                        setRange((getRange() * 1.3));
                        //some kinda different behaviour
                        break;
                    case 3:
                        setRange((getRange() * 1.5));
                        //some kinda different behaviour
                        break;
                }

                break;

            case 2:
                switch (getUpgrade2()) {
                    case 1:
                        setProjectilePiercing(getProjectilePiercing() * 1.5);

                        break;
                    case 2:
                        setProjectilePiercing(getProjectilePiercing() * 2);
                        //some kinda different behaviour
                        break;
                    case 3:
                        setProjectilePiercing(getProjectilePiercing() * 3);
                        //some kinda different behaviour
                        break;
                }
                break;

            case 3:
                switch (getUpgrade3()) {
                    case 1:
                        setProjectileSpeed(getProjectileSpeed() * 1.5);
                        break;
                    case 2:
                        setProjectileSpeed(getProjectileSpeed() * 2);
                        //some kinda different behaviour
                        break;
                    case 3:
                        setProjectileSpeed(getProjectileSpeed() * 3);
                        //some kinda different behaviour
                        break;
                }
                break;
            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }
}
