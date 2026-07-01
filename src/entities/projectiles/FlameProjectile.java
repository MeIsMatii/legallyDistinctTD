package entities.projectiles;

import entities.tower.Tower;
import greenfoot.GreenfootImage;

public class FlameProjectile extends Projectile {
    public FlameProjectile(Tower owner) {
        super(owner);
        GreenfootImage img = new GreenfootImage("flame.png");
        img.scale(100,150);
        setImage(img);
    }

    void move() {
        move(getSpeed());
    }

}

