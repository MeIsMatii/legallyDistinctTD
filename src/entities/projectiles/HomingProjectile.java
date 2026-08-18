package entities.projectiles;

import entities.base.Enemy;
import entities.base.Projectile;
import entities.base.Tower;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.List;

/**
 * @author Mathilo
 */
public class HomingProjectile extends Projectile {
    private int homingRadius;
    private Enemy targetedEnemy;

    public HomingProjectile(Tower owner) {
        super(owner);

        GreenfootImage img = new GreenfootImage("projectiles/homingProjectile.png");
        setImage(img);

        this.homingRadius = 100;
    }

    public HomingProjectile() {
        super();

        GreenfootImage img = new GreenfootImage("projectiles/homingProjectile.png");
        setImage(img);

        this.homingRadius = 100;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        if (getOwner() != null && getOwner().getTargetedEnemy() != null) {
            this.targetedEnemy = getOwner().getTargetedEnemy();
        } else {
            List<Enemy> enemyList = getObjectsInRange(homingRadius, Enemy.class);
            if (!enemyList.isEmpty()) {
                this.targetedEnemy = enemyList.get(0);
            }
        }
    }

    public void move() {
        if (targetedEnemy == null || targetedEnemy.getWorld() == null) {
            List<Enemy> enemyList = getObjectsInRange(homingRadius, Enemy.class);
            if (!enemyList.isEmpty()) {
                this.targetedEnemy = enemyList.get(0);
            } else {
                move((int) Math.round(getSpeed()));
                return;
            }
        }

        target(targetedEnemy);
    }

    public void target(Enemy e) {
        setTargetX(e.getX());
        setTargetY(e.getY());

        target();
        move((int) Math.round(getSpeed()));
    }

    public int getHomingRadius() {
        return homingRadius;
    }

    public void setHomingRadius(int homingRadius) {
        this.homingRadius = homingRadius;
    }
}
