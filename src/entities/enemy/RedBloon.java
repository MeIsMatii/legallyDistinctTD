package entities.enemy;

import entities.Entity;
import entities.tower.Tower;

public class RedBloon extends Enemy {
    public RedBloon(double speed, int lives) {
        super(speed, lives);
        setImage("Coin.png"); //TODO FIX @elias/art guys
    }

    public void onHit(Entity hitter) {
        if (hitter instanceof Tower) // TODO REPLACE WITH PROJECTILE @ELIAS
        {
            lives--;
            if (lives <= 0) {
                getWorld().removeObject(this);
                //money(or whatever it will be called)++;
                //how do you push again?
            }
        }
    }
}
