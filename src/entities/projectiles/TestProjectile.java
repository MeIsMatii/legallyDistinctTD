package entities.projectiles;

import entities.tower.Tower;
import greenfoot.GreenfootImage;
/**
 * @author Mathilo
 * @author colin
 * @author Julian
 */
public class TestProjectile extends Projectile {
    public TestProjectile(Tower owner) {
        super(owner);
        GreenfootImage img = new GreenfootImage("projectile.png");
        //img.scale(100,60);
        setImage(img);
    }


}
