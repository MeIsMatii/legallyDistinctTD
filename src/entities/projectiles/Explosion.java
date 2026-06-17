package entities.projectiles;

import greenfoot.Actor;

public class Explosion extends Actor {

    private int timer = 10;

    public Explosion() {
        setImage("Explosion.png");
        getImage().scale(100, 100);
    }

    public void act() {
        timer--;
        if (timer <= 0) {
            getWorld().removeObject(this);
        }
    }
}
//für traptower lool