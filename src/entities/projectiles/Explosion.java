package entities.projectiles;

import core.MainClass;
import greenfoot.Actor;
import util.HasSound;

public class Explosion extends MainClass implements HasSound {

    private int timer = 10;

    public Explosion() {
        setImage("Explosion.png");
        getImage().scale(100, 100);
        playSound("Explosion.mp3");
    }

    public void act() {
        timer--;
        if (timer <= 0) {
            getWorld().removeObject(this);
        }
    }
}
//für traptower lool