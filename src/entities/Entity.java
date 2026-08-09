package entities;

import core.MainClass;
import greenfoot.World;
import util.multiplayer.NetworkManager;

import java.util.UUID;

/**
 * @author mati
 * @version probably broken
 */

public abstract class Entity extends MainClass {
    protected String uniqueId; //for multiplayer

    public Entity() {
        this.uniqueId = UUID.randomUUID().toString();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uuid) { //to sync the enemy ids for multiplayer
        this.uniqueId = uuid;
    }

    /**
     * This method gets called when an Entity collides with a hitbox.
     *
     * @param hitter the Entity that collided with the hitbox.
     */
    public abstract void onHit(Entity hitter);


    /**
     * This method gets called by the hitbox. <br>
     * Once, when the cursor starts hovering above it, and once when it stops.
     *
     * @param isHovering whether the mouse cursor is hovering above that object.
     */
    public void checkHover(boolean isHovering) {
        if (isHovering) {
            //System.out.println("hover");
            onHover();
        } else {
            //System.out.println("unhover");
            onUnhover();
        }
    }

    /**
     * This method gets called when the mouse cursor (the Cursor object) collides with the hitbox (will only activate again after "uncolliding").
     */
    public void onHover() {
    }

    /**
     * This method gets called when the mouse cursor (the Cursor object) stops colliding with the hitbox (will only activate again after colliding).
     */
    public void onUnhover() {
    }

    public void addedToWorld(World world) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = getImage().getWidth() / CELLSIZE;
        int hitboxHeight = getImage().getHeight() / CELLSIZE;

        spawnHitbox(hitboxWidth, hitboxHeight);
    }

    /**
     * spawns a new hitbox for an entity
     *
     * @param hitboxWidth  width of the hitbox
     * @param hitboxHeight height of the hitbox
     */
    public void spawnHitbox(int hitboxWidth, int hitboxHeight) {
        Hitbox hitbox = new Hitbox(hitboxWidth, hitboxHeight, this);
        getWorld().addObject(hitbox, getX(), getY());

    }

    public void move(int speed) {
        super.move(speed);

        NetworkManager nm = NetworkManager.getInstance();
        if (nm.isMultiplayer() && nm.isHost()) {
            String msg = "MOVE_ENTITY" + "," + uniqueId + "," + getX() + "," + getY();
            nm.sendData(msg);
        }
    }

    public void setLocation(int x, int y) {
        NetworkManager nm = NetworkManager.getInstance();
        super.setLocation(x, y);
        if (nm.isHost()) {
            if (nm.isMultiplayer()) {
                String msg = "MOVE_ENTITY" + "," + uniqueId + "," + getX() + "," + getY();
                nm.sendData(msg);
            }
        }
    }

    public void setLocation(int x, int y, boolean isFromNetwork) {
        if (isFromNetwork) {
            super.setLocation(x, y);
        } else {
            setLocation(x, y);
        }
    }


    public void act() {
        super.act();
    }
}
