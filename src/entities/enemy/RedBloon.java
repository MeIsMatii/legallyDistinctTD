package entities.enemy;

import entities.Entity;

public class RedBloon extends Enemy {
    public RedBloon(int speed, int lives) {
        super(speed, lives);
    }

    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0) {
            getWorld().removeObject(this);
            //money(or whatever it will be called)++;
            //how do you push again?
        }
    }
}
