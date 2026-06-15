package entities.tower;

import entities.enemy.Enemy;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Sniper extends Tower{
    public Sniper() {
        super(200, true, 2000, 100, 10, 0, 0, 0);
        GreenfootImage img = new GreenfootImage("sniperTower.png");
        img.scale(200, 200);
        setImage(img);
    }

    @Override
    public String upgrade1() {
        return "";
    }

    @Override
    public String upgrade2() {
        return "";
    }

    @Override
    public String upgrade3() {
        return "";
    }

    @Override
    void shoot(Enemy e) {
        e.damage(getProjectileDamage());
    }


}
