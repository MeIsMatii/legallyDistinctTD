package entity.enemy;

import entity.Entity;

public class PurpleBloon extends Enemy {
    public PurpleBloon(int speed, int lives) {
        super(speed, lives);
    }

    //
    //NEEDS Immune to energy, fire, and plasma.
    //

    @Override
    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0) {
            getWorld().addObject(new PinkBloon(1, 1), getX(), getY());
            getWorld().addObject(new PinkBloon(1, 1), getX(), getY());
            getWorld().removeObject(this);
        }
    }
}
