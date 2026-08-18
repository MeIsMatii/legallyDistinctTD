package entities.projectiles;

import entities.base.Projectile;
import entities.base.Tower;
import greenfoot.GreenfootImage;

/**
 * @author Mathilo
 * @author colin
 * @author Julian
 */
public class TestProjectile extends Projectile {
    public TestProjectile(Tower owner) {
        super(owner);
        GreenfootImage img = new GreenfootImage("projectiles/projectile.png");
        //img.scale(100,60);
        setImage(img);
    }

    public TestProjectile() {
        super();
        GreenfootImage img = new GreenfootImage("projectiles/projectile.png");
        //img.scale(100,60);
        setImage(img);
    }


}
