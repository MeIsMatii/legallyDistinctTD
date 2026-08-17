package entities.projectiles;

import entities.Hitbox;
import entities.tower.Tower;
import greenfoot.GreenfootImage;
/**
 * @author Elias
 * @author colin
 */
public class FlameProjectile extends Projectile {
    public FlameProjectile(Tower owner) {
        super(owner);
        setImage("projectiles/flame.png");
    }
    public FlameProjectile() {
        super();
        setImage("projectiles/flame.png");
    }

    public void move() {
        super.move();
        GreenfootImage img = getImage();
        img.scale(getImage().getWidth()+1,getImage().getHeight()+1);
        setImage(img);

        for(Hitbox hb : getWorld().getObjects(Hitbox.class)) {
          if(hb.getOwner() == this) {
              getWorld().removeObject(hb);
              break;
          }
        }
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = getImage().getWidth() / CELLSIZE;
        int hitboxHeight = getImage().getHeight() / CELLSIZE;

        spawnHitbox(hitboxWidth, hitboxHeight);
    }

}

