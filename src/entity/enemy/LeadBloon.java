package entity.enemy;

import entity.Entity;

public class LeadBloon extends Enemy {
    public LeadBloon(int speed, int lives) {
        super(speed, lives);
    }

    //
    //NEEDS immunity to everything except fire & explosions
    //

    @Override
    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0) {
            onDeath();
        }
    }

    @Override
    public void onDeath() {
        getWorld().addObject(new BlackBloon(1,1), getX(), getY());
        getWorld().addObject(new BlackBloon(1,1), getX(), getY());
        super.onDeath();
    }
}