package entity.enemy;

import entity.Entity;

public class WhiteBloon extends Enemy {
    public WhiteBloon(int speed, int lives) {
        super(speed, lives);
    }

    //
     //NEEDS FREEZE RESISTENCE
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
