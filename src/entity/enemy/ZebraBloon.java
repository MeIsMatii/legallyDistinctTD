package entity.enemy;

import entity.Entity;

public class ZebraBloon extends Enemy {
    public ZebraBloon(int speed, int lives) {
        super(speed, lives);
    }

    //
    //NEEDS immunity to everything except fire & explosions
    //

    @Override
    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0) {
            getWorld().addObject(new BlackBloon(1,1), getX(), getY());
            getWorld().addObject(new WhiteBloon(1,1), getX(), getY());
            getWorld().removeObject(this);
        }
    }
}

