package entities.projectiles;

import entities.enemy.Enemy;
import entities.tower.Tower;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.List;

public class HomingProjectile extends Projectile {
    private final int homingRadius;
    private Enemy targetedEnemy;

    public HomingProjectile(Tower owner) {
        super(owner);

        GreenfootImage img = new GreenfootImage("projectile.png");
        img.scale(100,60);
        setImage(img);

        this.homingRadius = 100;
    }

    public void addedToWorld(World w) {
        super.addedToWorld(w);
        this.targetedEnemy = getWorld().getObjectsAt(getTargetX(), getTargetY(), Enemy.class).get(0);
    }

    public void move() {
        if (targetedEnemy.getWorld() == null) {
            List<Enemy> enemyList = getObjectsInRange(homingRadius, Enemy.class);
            if (!enemyList.isEmpty()) {
                this.targetedEnemy = enemyList.get(0);
            } else {
                move((int) Math.round(getSpeed()));
            }
        }
        if (targetedEnemy.getWorld() == null) {
            move((int) Math.round(getSpeed()));
            return;
        }

        target(targetedEnemy);
    }

    public void target(Enemy e) {
        setTargetX(e.getX());
        setTargetY(e.getY());

        target();
        move((int) Math.round(getSpeed()));
    }
}
