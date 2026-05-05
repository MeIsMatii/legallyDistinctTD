package entity.enemy;

import entity.Entity;

public class RainbowBloon extends Enemy {
    public RainbowBloon(int speed, int lives) {
        super(speed, lives);
    }

    @Override
    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0) {
            onDeath();
        }
    }

    @Override
    public void onDeath() {
        getWorld().addObject(new ZebraBloon(1,1), getX(), getY());
        getWorld().addObject(new ZebraBloon(1,1), getX(), getY());
        super.onDeath();
    }
}