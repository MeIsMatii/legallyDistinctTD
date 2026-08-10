package entities.projectiles;

import entities.tower.Tower;
import greenfoot.GreenfootImage;
/**
 * @author Elias
 * @author colin
 */
public class FlameProjectile extends Projectile {
    public FlameProjectile(Tower owner) {
        super(owner);
        GreenfootImage img = new GreenfootImage("flame.png");
        img.scale(100, 150);
        setImage(img);
    }
    public FlameProjectile() {
        super();
        GreenfootImage img = new GreenfootImage("flame.png");
        img.scale(100, 150);
        setImage(img);
    }

}

