package entities.projectiles;

import core.MainClass;
import entities.Entity;
import entities.enemy.Enemy;
import greenfoot.World;

import java.util.List;

public abstract class Projectile extends Entity {
    private int speed;
    private int damage;
    private int piercing;
    private int targetX;
    private int targetY;

    public Projectile(int speed, int piercing, int damage, int targetX, int targetY){
        this.speed = speed;
        this.piercing = piercing;
        this.damage = damage;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public int getPiercing() {
        return piercing;
    }

    public void addedToWorld(World w) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = (int) ((getImage().getWidth()*1.25) / CELLSIZE);
        int hitboxHeight = (int) (getImage().getHeight()*1.25) / CELLSIZE;

        spawnHitbox(hitboxWidth,hitboxHeight);


        target();
    }

    public void act() {
        move(speed);
        //System.out.printf("x: %d, y: %d\n", getX(), getY());

        if(isAtEdge()) {
            getWorld().removeObject(this);
        }

    }

    public void onHit(Entity hitter) {
        if(hitter instanceof Enemy) {
            this.piercing--;
        }
    }

    public void target() {
        turnTowards(targetX,targetY);
    }
}

