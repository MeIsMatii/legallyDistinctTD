package entities;

import core.MainClass;
import greenfoot.World;

/**
 * @author matii
 * @version hopefully the last one
 */

public abstract class Entity extends MainClass {

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

        spawnHitbox(hitboxWidth,hitboxHeight);
    }

    public void spawnHitbox(int hitboxWidth, int hitboxHeight) {
        Hitbox hitbox = new Hitbox(hitboxWidth, hitboxHeight, true, this);
        getWorld().addObject(hitbox, getX(), getY());

    }


    public void act() {
        super.act();
    }
}
