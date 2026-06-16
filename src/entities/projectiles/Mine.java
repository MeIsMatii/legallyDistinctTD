package entities.projectiles;

import bluej.utility.filefilter.DirectoryFilter;
import entities.Entity;
import entities.enemy.Enemy;
import entities.tower.Tower;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import map.levels.util.Path;

import java.util.List;

public class Mine extends Projectile{
    private int explosionCounter = 0;
    private int explosionTime;
    private boolean isExploding = false;

    private boolean isMoving =true;

    private int mineRadius;

    public Mine(Tower owner) {
        super(owner);
        this.mineRadius = mineRadius;
        this.explosionTime = 45;
    }

    void move() {
        if(!isMoving) {
            return;
        }
        move(getSpeed());
    }

    public void onHit(Entity hitter) {
        if(hitter instanceof Path) {
            isMoving = false;
        }
        this.isExploding = true;
        GreenfootImage img = new GreenfootImage(mineRadius, mineRadius);
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
    }

    public void act() {
        super.act();
        if(getWorld() == null) {
            return;
        }

        if(isExploding) {
            explode();
        }
    }

    public void explode() {
        explosionCounter++;
        if(explosionCounter >= explosionTime) {
            getWorld().removeObject(this);
            return;
        }

        List<Enemy> enemys = getObjectsInRange(mineRadius, Enemy.class);
        for (Enemy enemy : enemys) {
            enemy.damage(getDamage());
        }

    }


}
