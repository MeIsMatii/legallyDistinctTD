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

    @Override
    public void onHit(Entity hitter) {
        List<Enemy> enemys = getObjectsInRange(25, Enemy.class);
        for (int i = 0; i < enemys.toArray().length -1; i++) {
            enemys.get(i).damage(getDamage());
        }

    }
}