package entities.enemy;

import entities.Entity;
import greenfoot.World;
import map.levels.Map;
import map.levels.util.Path;

import java.util.List;

public abstract class Enemy extends Entity {
    int lives;
    double speed;
    double realPosX;
    double realPosY;

    int nextX;
    int nextY;

    public Enemy(double speed, int lives) {
        this.speed = speed;
        this.lives = lives;

        this.realPosX = 0;
        this.realPosY = 0;
    }
    public void addedToWorld(World world) {
        this.realPosX = getX();
        this.realPosY = getY();
    }

    public void act() {
        List<Path> pathList = getWorld().getObjectsAt(getX(),getY(), Path.class);
        if(!pathList.isEmpty()) {
            Path path = pathList.get(0);
            this.nextX = path.getNextPathX();
            this.nextY = path.getNextPathY();
            if(nextX == 0 && nextY == 0) {
                Map map = (Map) getWorld();
                map.getPLAYER().damage(10);
                map.removeObject(this);
                //TODO DAMAGE PLAYER @ELIAS
            }
        }
        moveTo(nextX,nextY);
    }


    public void onHover() {
        //nothing?
    }

    // move()
    public void moveTo(int targetX, int targetY) {
        /// ////////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA D:

        double dx = targetX - realPosX;
        double dy = targetY - realPosY;

        if (Math.abs(dx) > speed) {
            realPosX += speed * Math.signum(dx);
        } else if (Math.abs(dy) > speed) {
            realPosY += speed * Math.signum(dy);
        } else {
            realPosX = targetX;
            realPosY = targetY;
        }

        setLocation((int) Math.round(realPosX), (int) Math.round(realPosY));
    }

}
