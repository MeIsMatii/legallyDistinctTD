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
    private final long destroyAfter = System.currentTimeMillis() + 100;


    public Ice(Tower owner) {
        super(owner);
        GreenfootImage img = new GreenfootImage("ice.png");
        img.scale(150,150);
        img.rotate(90);
        setImage(img);

    }


    public void onHit(Entity hitter) {
        if (!(hitter instanceof Enemy) || getWorld() == null) return;
        //ImageDisplay explosion = new ImageDisplay("Explosion.png");    //ice bei bloon
        //getWorld().addObject(explosion,getX(),getY());
        List<Enemy> enemies = getObjectsInRange(100, Enemy.class);
        if (!enemies.isEmpty()) {
            for (Enemy enemy : enemies) {
                enemy.damage(getDamage());
                IceTower owner = (IceTower) getOwner();
                enemy.applySlow(owner.getSlow(), owner.getSlowTimer());

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