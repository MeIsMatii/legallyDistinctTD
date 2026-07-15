package entities.enemy;

import core.Player;
import entities.Entity;
import greenfoot.World;
import map.levels.Map;
import map.levels.util.Path;
import util.multiplayer.NetworkManager;

import java.util.List;
import java.util.UUID;

public abstract class Enemy extends Entity {
    private final String uniqueId; //for multiplayer

    double lives;
    double speed;

    int initialLives;

    int nextX;
    int nextY;

    public Enemy(double speed, int lives) {
        this.uniqueId = UUID.randomUUID().toString();

        this.speed = speed;
        this.lives = lives;
        initialLives = lives;
    }

    public Enemy(double speed, int lives, String uuid) {
        this.uniqueId = uuid;

        this.speed = speed;
        this.lives = lives;
        initialLives = lives;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void addedToWorld(World world) {
        super.addedToWorld(world);
    }

    public void spawnHitbox(int hitboxWidth, int hitboxHeight) {
        EnemyHitbox hitbox = new EnemyHitbox(hitboxWidth, hitboxHeight, this);
        getWorld().addObject(hitbox, getX(), getY());
    }

    public void act() {
        if (isPaused()) return;

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
                if (map.isMultiplayer()) {
                    map.getPlayer().damage(getInitialLives());
                }
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
            if (NetworkManager.getInstance().isHost()) {
                player1.setCoins(player1.getCoins() + getInitialLives());
            }
            getWorld().removeObject(this);
        }
    }

    // move()
    public void moveTo(int targetX, int targetY) {
        /*double dx = targetX - realPosX;
        double dy = targetY - realPosY;

        if (Math.abs(dx) > speed) {
            realPosX += speed * Math.signum(dx);
        } else if (Math.abs(dy) > speed) {
            realPosY += speed * Math.signum(dy);
        } else {
            realPosX = targetX;
            realPosY = targetY;
        }

        realPosX += speed;
        realPosY += speed;*/

        turnTowards(nextX, nextY);
        move((int) Math.round(speed));
        setRotation(0);
        //setLocation((int) Math.round(realPosX), (int) Math.round(realPosY));
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

    public double getLives() {
        return this.lives;
    }

    public int getInitialLives() {
        return initialLives;
    }

}
