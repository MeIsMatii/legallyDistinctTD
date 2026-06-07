package entities.projectiles;

import core.MainClass;
import entities.Entity;
import entities.enemy.Enemy;
import greenfoot.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Projectile extends Entity {
    private int speed;
    private final int damage;
    private int piercing;
    private int targetX;
    private int targetY;

    private Map<Enemy,Integer> hitEnemies = new HashMap<>();
    private final int iframes;

    public Projectile(int speed, int piercing, int damage, int targetX, int targetY, int iframes){
        this.speed = speed;
        this.piercing = piercing;
        this.damage = damage;
        this.targetX = targetX;
        this.targetY = targetY;

        this.iframes = iframes;
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

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void addedToWorld(World w) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = (int) ((getImage().getWidth()*1.25) / CELLSIZE);
        int hitboxHeight = (int) (getImage().getHeight()*1.25) / CELLSIZE;

        spawnHitbox(hitboxWidth,hitboxHeight);


        target();
    }

    public void act() {
        if(getWorld() == null){
            return;
        }


        updateIFrames();
        move();

        if(isAtEdge() || piercing <= 0 || getX() > 1620) {
            getWorld().removeObject(this);
        }

    }

    public void updateIFrames() {
        hitEnemies.replaceAll((e, frames) -> frames+1); //increment local iframes by 1
        hitEnemies.entrySet().removeIf(entry -> entry.getValue() >= this.iframes);
    }

    public void onHit(Entity hitter) {
        if(piercing<=0 || !(hitter instanceof Enemy)) return;

        Enemy e = (Enemy) hitter;
        if(hitEnemies.containsKey(e)) return; //already hit

        System.out.println("hit");
        hitEnemies.put(e,1); //add enemy to hashmap, with 1 iframe
        e.damage(this.damage);
        this.piercing--;

    }


    public void target() {
        turnTowards(targetX,targetY);
    }

    abstract void move();
}

