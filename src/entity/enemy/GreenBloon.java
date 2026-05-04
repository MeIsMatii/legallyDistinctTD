package entity.enemy;

import entity.Entity;

public class GreenBloon extends Enemy {
    public GreenBloon(int speed, int lives) {
        super(speed, lives);
    }

    @Override
    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0){
            getWorld().addObject(new BlueBloon(1,1),getX(),getY());
            getWorld().removeObject(this);
        }
    }
}
