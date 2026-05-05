package entity.enemy;

import entity.Entity;

public class BlackBloon extends Enemy {
    public BlackBloon(int speed, int lives) {
        super(speed, lives);
    }


     //
     // NEEDS BLAST RESISTENCE
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
        getWorld().addObject(new PinkBloon(1,1), getX(), getY());
        getWorld().addObject(new PinkBloon(1,1), getX(), getY());
        super.onDeath();
    }
}

