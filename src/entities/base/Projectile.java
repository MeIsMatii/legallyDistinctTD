package entities.base;

import entities.Entity;
import greenfoot.World;
import maps.levels.GameMap;
import util.HasSound;
import util.multiplayer.NetworkManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathilo
 * @author Julian
 */
public abstract class Projectile extends Entity implements HasSound {
    private Tower owner;
    private final Map<Enemy, Integer> hitEnemies = new HashMap<>();
    private double damage;
    private int iframes;
    private double speed;
    private double piercing;
    private int targetX;
    private int targetY;

    public Projectile(Tower owner) {
        super();
        this.owner = owner;
    }

    public Projectile() {
        super();
    }

    public Tower getOwner() {
        return owner;
    }

    public void setOwner(Tower owner) {
        this.owner = owner;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDamage() {
        return damage;
    }

    public double getPiercing() {
        return piercing;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public void addedToWorld(World w) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = (int) ((getImage().getWidth() * 1.25) / CELLSIZE);
        int hitboxHeight = (int) (getImage().getHeight() * 1.25) / CELLSIZE;

        spawnHitbox(hitboxWidth, hitboxHeight);

        if (owner != null && owner.getWorld() != null) {
            this.speed = owner.getProjectileSpeed();
            this.piercing = owner.getProjectilePiercing();
            this.damage = owner.getProjectileDamage();
            this.iframes = owner.getProjectileIFrames();

            if (owner.getTargetedEnemy() != null && owner.getTargetedEnemy().getWorld() != null) {
                this.targetX = owner.getTargetedEnemy().getX();
                this.targetY = owner.getTargetedEnemy().getY();
                target();
            } else if (targetX != 0 || targetY != 0) {
                target();
            } else {
                setRotation(owner.getRotation());
            }
        } else if (targetX != 0 || targetY != 0) {
            target();
        }
    }

    public void act() {
        if (getWorld() == null || isPaused()) return;


        updateIFrames();
        move();

        if (isAtEdge() || piercing <= 0 || getX() > 1620) { //1620 is the upgrade screen
            getWorld().removeObject(this);
        }

    }

    /**
     * Advances the iFrames of every hit enemy by one.<br>
     * When an enemies value is greater than 10, it removes it from the Map, allowing it to be hit again.
     */
    public void updateIFrames() {
        hitEnemies.replaceAll((e, frames) -> frames + 1); //increment local iframes by 1
        hitEnemies.entrySet().removeIf(entry -> entry.getValue() >= this.iframes);
    }

    /**
     * This method only does something when the hitter is an Enemy.<br>
     * In that case, it damages it and adds it to the hitEnemies Map.
     * @param hitter the Entity that collided with the hitbox.
     */
    public void onHit(Entity hitter) {
        if (piercing <= 0 || !(hitter instanceof Enemy)) return;

        Enemy e = (Enemy) hitter;
        if (hitEnemies.containsKey(e)) return; //already hit

        hitEnemies.put(e, 1); //add enemy to hashmap, with 1 iframe
        if (NetworkManager.getInstance().isHost()) { //host or singleplayer
            e.damage(this.damage);
            if ((getWorldOfType(GameMap.class).isMultiplayer())) {//multiplayer and is host
                String msg = "DAMAGE_ENEMY" + "," + e.getUniqueId() + "," + damage;
                NetworkManager.getInstance().sendData(msg);
            }
        }
        this.piercing--;
        playSound("hitSound.mp3");
    }


    public void target() {
        turnTowards(targetX, targetY);
    }

    /**
     * Default implementation.
     */
    public void move() {
        move((int) Math.round(speed));
    }

}

