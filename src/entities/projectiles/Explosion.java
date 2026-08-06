package entities.projectiles;

import core.MainClass;
import entities.tower.Tower;
import greenfoot.Actor;
import util.HasSound;

public class Explosion extends Projectile implements HasSound {

    private int timer = 10;

    public Explosion(Tower owner) {
        super(owner);
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

    @Override
    public String getName() {
        return "Explosion";
    }
}
//für traptower lool