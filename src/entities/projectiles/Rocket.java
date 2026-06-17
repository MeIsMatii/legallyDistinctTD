package entities.projectiles;

import entities.Entity;
import entities.enemy.Enemy;
import entities.tower.Tower;
import greenfoot.Color;
import greenfoot.GreenfootImage;

import java.util.List;

public class Rocket extends Projectile{
    private Tower owner;
    public Rocket(Tower owner) {
        super(owner);
        setImage("rocket.jpg");
        this.owner = owner;
    }

    @Override
    void move() {
        move(getSpeed());
    }

    @Override
    public void onHit(Entity hitter) {
        System.out.println("onHit Rocket");
        List<Enemy> enemys = getObjectsInRange(25, Enemy.class);
       if (!enemys.isEmpty()){
           for (int i = 0; i < enemys.toArray().length-1; i++) {
               enemys.get(i).damage(owner.getProjectileDamage());
           }
       }
    }
}