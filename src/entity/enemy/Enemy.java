package entity.enemy;

import entity.Entity;

abstract class Enemy extends Entity {
    /// @ELIAS
    /// TODO make the bloons follow the path
    /// the path has "getNextPathX() and -Y().
    /// Make the bloons get that when touching a path and move towards it
    /// (use the moveTo(x,y) func)



    private int lives;
    private int speed;

    public Enemy(int speed, int lives) {
    setSpeed(speed);
    setLives(lives);
    }

    public int getLives() {
        return lives;
    }

    public int getSpeed() {
        return speed;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void onHover() {
        //nothing?
    }
    public void onHit(Entity hitter) {
        lives--;
        if (lives <= 0) {
            getWorld().removeObject(this);
            //getWorld().getPlayer(or what its going to be )
        }
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
