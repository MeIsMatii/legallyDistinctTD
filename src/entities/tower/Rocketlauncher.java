package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.Rocket;
import entities.projectiles.TestProjectile;
import greenfoot.GreenfootImage;

public class Rocketlauncher extends Tower{
    private final int[] upgrades1 = new int[]{150,500,2500};
    private final int[] upgrades2 = new int[]{200,450,3000};
    private final int[] upgrades3 = new int[]{100,350,1750};

    public Rocketlauncher() {
        super(500, true, 500, 150, 50, 25, 1, 0);
        //GreenfootImage img = new GreenfootImage("Rocketlauncher.jpg");
        //img.scale(200,200);
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


    @Override
    public void shoot(Enemy e) {
        getWorld().addObject(new Rocket(this), getX(), getY());
    }

    @Override
    public String getName() {
        return "Rocketlauncher";
    }

    @Override
    public int getAnimationSpeed() {
        return 1;
    }

    public void upgrade(int path) {
        //TODO better upgrades
        onUpgrade(path);
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