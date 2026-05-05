package entity.enemy;

import entity.Entity;

public class PinkBloon  extends Enemy {
    public PinkBloon(int speed, int lives) {
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
        getWorld().addObject(new YellowBloon(1,1), getX(), getY());
        super.onDeath();
    }
}
