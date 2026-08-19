package entities.base;

import core.Player;
import entities.Entity;
import entities.enemy.EnemyHitbox;
import greenfoot.World;
import maps.levels.GameMap;
import maps.levels.util.Path;
import util.multiplayer.NetworkManager;

import java.util.List;

/**
 * @author Elias
 * @author Mathilo
 * @author Jannis
 */
public abstract class Enemy extends Entity {

    double lives;
    double speed;
    double normalSpeed;                             //Freezetower
    int initialLives;
    int nextX;
    int nextY;
    private double realPosX;
    private double realPosY;
    private int slowTimer = 0;                          //Freezetower

    public Enemy(double speed, int lives) {
        super();

        this.speed = speed;
        this.lives = lives;
        initialLives = lives;
    }

    public double getSpeed() {                     //von Jannis hoffe darf das weil ist für freezetower
        return speed;
    }

    public void setSpeed(double speed) {                        //Freezetower
        this.speed = speed;
    }

    /**
     * Slows the enemy. This effect is applied by things like the IceTower.
     * @param slowSpeed how fast the enemy should be.
     * @param duration how long it should be slowed for.
     */
    public void applySlow(double slowSpeed, int duration) {                  //Freezetower
        if (slowTimer == 0) {
            normalSpeed = speed;
        }

        speed = slowSpeed;
        slowTimer = duration;
    }

    /**
     * Updates the slowing effect on an enemy.
     */
    public void updateSlow() {                                         //Freezetower
        if (slowTimer > 0) {
            slowTimer--;

            if (slowTimer == 0) {
                speed = normalSpeed;
            }
        }
    }

    public abstract String getName();


    public void addedToWorld(World world) {
        super.addedToWorld(world);

        this.realPosX = getX();
        this.realPosY = getY();
    }

    /**
     * Spawns a custom oval hitbox for enemies instead of the normal square one.
     * @param hitboxWidth  width of the hitbox.
     * @param hitboxHeight height of the hitbox.
     */
    public void spawnHitbox(int hitboxWidth, int hitboxHeight) {
        EnemyHitbox hitbox = new EnemyHitbox(hitboxWidth, hitboxHeight, this);
        getWorld().addObject(hitbox, getX(), getY());
    }

    public void act() {
        if (isPaused()) return;
        if (NetworkManager.getInstance().isHost()) {
            findPath();
            moveTo(nextX, nextY);
        }
        updateSlow();              //Freezetower
    }

    /**
     * Sets the next path-waypoint to go to, or incase there is no next waypoint, it damages the player.
     */
    public void findPath() {
        List<Path> pathList = getWorld().getObjectsAt(getX(), getY(), Path.class);
        if (!pathList.isEmpty()) {
            Path path = pathList.get(0);
            this.nextX = path.getNextPathX();
            this.nextY = path.getNextPathY();
            if (nextX == 0 && nextY == 0) {
                GameMap gameMap = (GameMap) getWorld();
                if (NetworkManager.getInstance().isHost()) { // host or singleplayer
                    gameMap.getPlayer().damage(getInitialLives());

                    if (gameMap.isMultiplayer()) { //host and is multiplayer
                        String msg = "DAMAGE_PLAYER" + "," + getInitialLives();
                        NetworkManager.getInstance().sendData(msg);
                    }
                }
                if (getWorld() != null) {
                    gameMap.removeObject(this);
                }
            }
        }
    }

    /**
     * Damages the enemy and makes it die in case the health reaches <= 0.
     * @param damage the amount of damage.
     */
    public void damage(double damage) {
        if (getWorld() == null) {
            return;
        }
        this.lives = this.lives - damage;
        if (lives <= 0) {
            List<Player> player = getWorld().getObjects(Player.class);
            Player player1 = player.get(0);
            if (NetworkManager.getInstance().isHost()) {
                player1.setCoins(player1.getCoins() + (getInitialLives()));
            }
            getWorld().removeObject(this);
        }
    }

    // move()

    /**
     * Moves the enemy towards a targeted position.<br>
     * The step-size is dependent on the speed attribute.
     * @param targetX
     * @param targetY
     */
    public void moveTo(int targetX, int targetY) {
        if (getWorld() == null) {
            return;
        }

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

    /**
     * This method was used to detect whether a projectile is hitting it, but has since been moved to Projectile.java.
     * @param e the Entity that collided with the hitbox.
     */
    public void onHit(Entity e) {
    }

    /**
     * @return The current amount of lives
     */
    public double getLives() {
        return this.lives;
    }

    /**
     * @return The amount of lives an enemy initially had
     */
    public int getInitialLives() {
        return initialLives;
    }

}
