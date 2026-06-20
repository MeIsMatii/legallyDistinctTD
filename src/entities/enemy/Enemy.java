package entities.enemy;

import core.Player;
import entities.Entity;
import greenfoot.World;
import map.levels.Map;
import map.levels.util.Path;

import java.util.List;

public abstract class Enemy extends Entity {
    double lives;
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
        super.addedToWorld(world);
        this.realPosX = getX();
        this.realPosY = getY();
    }

    public void act() {
        if(isPaused()) return;

        findPath();
        moveTo(nextX, nextY);
    }

    public void findPath() {
        List<Path> pathList = getWorld().getObjectsAt(getX(), getY(), Path.class);
        if (!pathList.isEmpty()) {
            Path path = pathList.get(0);
            this.nextX = path.getNextPathX();
            this.nextY = path.getNextPathY();
            if (nextX == 0 && nextY == 0) {
                Map map = (Map) getWorld();
                map.getPLAYER().damage(10);
                map.removeObject(this);
            }
        }
    }


    public void onHover() {
        //nothing?
    }

    public void damage(double damage) {
        if (getWorld() == null) {
            return;
        }
        this.lives = this.lives - damage;
        if (lives <= 0) {
            List<Player> player = getWorld().getObjects(Player.class);
            Player player1 = player.get(0);
            player1.setCoins(player1.getCoins() + 1);
            getWorld().removeObject(this);
        }
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

    /// Note (from Mathilo): this did NOT work, because sometimes the proj deleted itself before the enemies hitbox could pick up on it existing leading to it not being damaged
    /// So i made the proj call damage()
    /*public void onHit(Entity hitter) {
        if(getWorld() == null) {
            return;
        }
        if (hitter instanceof Projectile)
        {
            System.out.println(lives);
            Projectile p = (Projectile) hitter;
            damage(p.getDamage());
        }
    }*/
    public void onHit(Entity e) {
    }

}
