package entities.projectiles;

import entities.tower.Tower;
import greenfoot.GreenfootImage;

public class TestProjectile extends Projectile {
    public TestProjectile(Tower owner) {
        super(owner);
        GreenfootImage img = new GreenfootImage("projectile.png");
        //img.scale(100,60);
        setImage(img);
    }
}
