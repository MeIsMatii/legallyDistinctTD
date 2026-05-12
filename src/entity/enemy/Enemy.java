package entity.enemy;

import entity.Entity;

public abstract class Enemy extends Entity {
    int lives;
    int speed;

    public Enemy(int speed, int lives) {
        this.speed = speed;
        this.lives = lives;
    }

    public void onHover() {
        //nothing?
    }

    // move()
    public void moveTo(int x, int y) {
        /// Hopefully works --Mathilo
        if (getX() != x) {
            turnTowards(x, getY());
        }

        for (int i = 0; i < speed; i++) {
            move(1);
            if (getX() == x) {
                break;
            }
        }


        if (getY() != y) {
            turnTowards(getX(), y);
        }

        for (int i = 0; i < speed; i++) {
            move(1);
            if (getY() == y) {
                break;
            }
        }
    }

}
