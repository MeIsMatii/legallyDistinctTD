package entities.projectiles;

import entities.Entity;
import entities.base.Enemy;
import entities.base.Projectile;
import entities.base.Tower;
import entities.tower.IceTower;
import greenfoot.GreenfootImage;
import maps.levels.GameMap;
import util.HasSound;
import util.multiplayer.NetworkManager;

import java.util.List;

/**
 * @author Jannis
 */
public class Ice extends Projectile implements HasSound {

    private int destructionCounter = 0;


    public Ice(Tower owner) {
        super(owner);
        GreenfootImage img = new GreenfootImage("projectiles/ice.png");
        img.scale(150, 150);
        img.rotate(90);
        setImage(img);

    }

    public Ice() {
        super();
        GreenfootImage img = new GreenfootImage("projectiles/ice.png");
        img.scale(150, 150);
        img.rotate(90);
        setImage(img);

    }


    public void onHit(Entity hitter) {
        if (!(hitter instanceof Enemy) || getWorld() == null) return;
        if (NetworkManager.getInstance().isHost()) {
            List<Enemy> enemies = getObjectsInRange(100, Enemy.class);
            if (!enemies.isEmpty()) {
                for (Enemy enemy : enemies) {
                    enemy.damage(getDamage());
                    if (getOwner() instanceof IceTower) {
                        IceTower owner = (IceTower) getOwner();
                        enemy.applySlow(owner.getSlow(), owner.getSlowTimer());
                    }
                    if (getWorldOfType(GameMap.class) != null && getWorldOfType(GameMap.class).isMultiplayer()) {
                        String msg = "DAMAGE_ENEMY" + "," + enemy.getUniqueId() + "," + getDamage();
                        NetworkManager.getInstance().sendData(msg);
                    }
                }
            }
        }
    }

    public void act() {
        if (getWorld() == null) {
            return;
        }
        super.act();
        destructionCounter++;
        IceTower owner = (IceTower) getOwner();
        if (destructionCounter > owner.getDestroyAfter() && getWorld() != null) {
            getWorld().removeObject(this);
        }
    }
}