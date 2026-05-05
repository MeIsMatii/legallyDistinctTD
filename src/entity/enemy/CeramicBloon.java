package entity.enemy;

import entity.Entity;

public class CeramicBloon extends Enemy {
    public CeramicBloon(int speed, int lives) {
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
        getWorld().addObject(new RainbowBloon(1,1), getX(), getY());
        getWorld().addObject(new RainbowBloon(1,1), getX(), getY());
        super.onDeath();
    }
}