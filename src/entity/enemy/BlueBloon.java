package entity.enemy;

import entity.Entity;

public class BlueBloon extends Enemy {
    public BlueBloon(int speed, int lives) {
        super(speed, lives);
    }

    @Override
    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0){
            getWorld().addObject(new RedBloon(1,1),getX(),getY());
            getWorld().removeObject(this);
        }
    }
}
