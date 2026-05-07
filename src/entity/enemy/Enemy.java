package entity.enemy;

import entity.Entity;
import map.util.Path;

import java.util.List;

abstract class Enemy extends Entity {
    /// @ELIAS
    /// TODO make the bloons follow the path
    /// @mathilo I fucking hated this
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

    public void act() {
        pathFinding();
    }
    public void pathFinding(){
        if (isTouching(Path.class)){
            List<Path> Paths = getIntersectingObjects(Path.class);
            Path path = Paths.get(0);
            int nextX = path.getNextPathX();
            int nextY = path.getNextPathY();
            moveTo(nextX, nextY);
        }
    }

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
