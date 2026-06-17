package entities.projectiles;

import entities.Entity;
import entities.enemy.Enemy;
import entities.tower.Tower;
import greenfoot.Color;
import greenfoot.GreenfootImage;

import java.util.List;

public class Rocket extends Projectile{

    public Rocket(Tower owner) {
        super(owner);
        setImage("rocket.jpg");
    }

    @Override
    void move() {
        move(getSpeed());
    }


    public void onHit(Entity hitter) {
        if(!(hitter instanceof Enemy) || getWorld() == null) return;

        System.out.println("onHit Rocket");
        List<Enemy> enemies = getObjectsInRange(200, Enemy.class);
        if (!enemies.isEmpty()){
            for (Enemy enemy : enemies) {
                enemy.damage(getDamage());
            }
        }
        getWorld().removeObject(this);
    }

}