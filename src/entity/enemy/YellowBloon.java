package entity.enemy;

import entity.Entity;

public class YellowBloon extends Enemy {
    public YellowBloon(int speed, int lives) {
    super(speed, lives);
}

    @Override
    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0){
            getWorld().addObject(new GreenBloon(1,1),getX(),getY());
            getWorld().removeObject(this);
        }
    }
}
