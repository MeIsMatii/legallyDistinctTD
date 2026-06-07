package entities.tower;

import entities.enemy.Enemy;
import entities.projectiles.HomingProjectile;
import greenfoot.GreenfootImage;

public class HomingTower extends Tower {


    public HomingTower(boolean isPlacing, int upgrade1lvl, int upgrade2lvl, int upgrade3lvl) {
        super(isPlacing, 300, 10, 2, 3, 100, 45);

        GreenfootImage img = new GreenfootImage("MageTower.png");
        img.scale(120, 100);
        setImage(img);

    }

    public void act() {
        if (isPaused()) {
            return;
        }
        super.act();
    }

    public String upgrade1() {
        return "";
    }

    public String upgrade2() {
        return "";
    }

    public String upgrade3() {
        return "";
    }

    void shoot(Enemy e) {
        getWorld().addObject(new HomingProjectile(getProjectileSpeed(), getProjectilePiercing(), getProjectileDamage(), e.getX(), e.getY(), getProjectileIFrames(), 100), getX(), getY());
    }
}
