package entity;

import greenfoot.Actor;
import greenfoot.World;

/**
 * @author matii
 * @version hopefully the last one
 */

public abstract class Entity extends Actor {
   /**
    * This method gets called when an Entity collides with a hitbox.
    * @param hitter the Entity that collided with the hitbox.
    */
    public abstract void onHit(Entity hitter);

    /**
     * This method gets called by the hitbox. <br>
     * Once, when the cursor starts hovering above it, and once when it stops.
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
        return;
    }
    /**
     * This method gets called when the mouse cursor (the Cursor object) stops colliding with the hitbox (will only activate again after colliding).
     */
    public void onUnhover() {
        return;
    }

    public void addedToWorld(World world) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = getImage().getWidth() / CELLSIZE;
        int hitboxHeight = getImage().getHeight() / CELLSIZE;


        Hitbox hitbox = new Hitbox(hitboxWidth, hitboxHeight, this);
        getWorld().addObject(hitbox, getX(), getY());
    }
}
