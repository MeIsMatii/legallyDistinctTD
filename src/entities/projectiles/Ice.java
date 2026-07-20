package entities.projectiles;

import entities.Entity;
import entities.enemy.Enemy;
import entities.tower.IceTower;
import entities.tower.Tower;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import ui.common.ImageDisplay;
import util.HasSound;

import java.util.List;

public class Ice extends Projectile implements HasSound {
    private final long destroyAfter = System.currentTimeMillis() + 60;

    /*public double getSlow() {
        return slow;
    }

    public void setSlow(double slow) {
        this.slow = slow;
    }

    public double slow = 1;

    public int getSlowTimer() {
        return slowTimer;
    }

    public void setSlowTimer(int slowTimer) {
        this.slowTimer = slowTimer;
    }

    public int slowTimer = 50; */
    public Ice(Tower owner) {
        super(owner);
        setImage("rocket.png");
    }



    public void onHit(Entity hitter) {
        if(!(hitter instanceof Enemy) || getWorld() == null) return;
        //ImageDisplay explosion = new ImageDisplay("Explosion.png");    //ice bei bloon
        //getWorld().addObject(explosion,getX(),getY());
        List<Enemy> enemies = getObjectsInRange(100, Enemy.class);
        if (!enemies.isEmpty()){
            for (Enemy enemy : enemies) {
                enemy.damage(getDamage());
                IceTower penis = (IceTower)getOwner();
                enemy.applySlow(0.3, penis.getSlowTimer());

            }
        }
        //playSound("Explosion.mp3");

        //getWorld().removeObject(explosion);
        //getWorld().removeObject(this);
    }
    /*private void handleVisual(){
        playSound("Explosion.mp3");
        ImageDisplay explosion = new ImageDisplay("Explosion.png");
        getWorld().addObject(explosion,getX(),getY());
        if (destroyAfter < System.currentTimeMillis()){
            getWorld().removeObject(explosion);
        }   */
    }
