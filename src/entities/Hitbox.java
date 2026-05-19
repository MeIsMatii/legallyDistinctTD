package entities;

import core.MainClass;
import greenfoot.*;
import util.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author matii
 * @version hopefully the last one
 */

public class Hitbox extends MainClass {
    private final Entity OWNER; // CAPS, because it's a constant
    private final int width;
    private final int height;
    private int widthInCells, heightInCells;
    private boolean wasHovering;

    private boolean isHittingSomething;
    private boolean isDebug;
    private boolean isFollowing;

    public Hitbox(int width, int height, boolean isFollowing, Entity owner) {
        this.OWNER = owner;
        this.width = width;
        this.height = height;
        this.isFollowing = isFollowing;

        this.wasHovering = false;
        this.isHittingSomething = false;
        this.isDebug = false;
    }

    public Entity getOWNER() {
        return this.OWNER;
    }

    public void addedToWorld(World world) {
        this.widthInCells = this.width * world.getCellSize() + world.getCellSize() / 2;
        this.heightInCells = this.height * world.getCellSize() + world.getCellSize() / 2;

        // w, h*2 bc my offset is in each direction

        GreenfootImage img = new GreenfootImage(widthInCells, heightInCells);

        img.setColor(Color.RED);
        img.drawRect(0, 0, (widthInCells) - 1, (heightInCells) - 1);

        setImage(img);

        updateAppearance(isHittingSomething);
    }

    public void setFollowing(boolean state) {
        this.isFollowing = state;
    }

    public void act() {
        // rm hitbox when player is gone
        if (OWNER == null || OWNER.getWorld() == null) {
            getWorld().removeObject(this);
            return;
        }

        if (isFollowing) {
            followPlayer();
        }

        checkTouching();

        // handles visibility
        updateAppearance(isHittingSomething);


        boolean isHovering = checkHover();
        //System.out.println(isHovering + ", " + wasHovering);
        if (isHovering != wasHovering) {
            OWNER.checkHover(isHovering);
            wasHovering = isHovering;
        }
    }

    public void followPlayer() {
        //center to player
        setLocation(OWNER.getX(), OWNER.getY());
    }

    public void checkTouching() {
        List<Hitbox> overlapping = getIntersectingObjects(Hitbox.class);

        boolean foundTarget = false;
        for (Hitbox hitbox : overlapping) {
            if (hitbox != this) {
                foundTarget = true;
                /// DEBUG
                ///System.out.printf("Owner: %s, Hitter: %s\n", OWNER, actor);
                OWNER.onHit(hitbox.getOWNER());
                break;
            }
        }

        isHittingSomething = foundTarget;
    }

    /**
     * gets all entities inside one's hitbox
     *
     * @return a list of all entities
     */
    public List<Entity> getEntitiesInHitbox() {
        List<Hitbox> overlapping = getIntersectingObjects(Hitbox.class);

        List<Entity> entities = new ArrayList<>();

        for (Hitbox hitbox : overlapping) {
            entities.add(hitbox.getOWNER());
        }
        return entities;
    }

    /**
     * gets Entities of a specific subclass (e.g. Tower, Enemy)
     *
     * @return a list of all Objects of that class
     */
    public <T> List<T> getSpecificEntitiesInHitbox(Class<T> targetedClass) {
        List<Hitbox> overlapping = getIntersectingObjects(Hitbox.class);

        List<T> entities = new ArrayList<>();
        for (Hitbox hitbox : overlapping) {
            if (targetedClass.isInstance(hitbox.getOWNER())) {
                entities.add(targetedClass.cast(hitbox.getOWNER()));
            }
        }

        return entities;
    }

    /**
     * Checks whether the hitbox is touching the Cursor class (that constantly follows the mouse).
     *
     * @return {@code true} if the hitbox is touching the Cursor class.<br>
     * {@code false} if the hitbox isn't touching the Cursor class.
     */

    public boolean checkHover() {
        return isTouching(Cursor.class);
    }

    /**
     * Updates the appearance of the hitbox based on whether it is hitting something.<br>
     * {@code red} if it is hitting something.<br>
     * {@code green} if it is not hitting anything.<br>
     *
     * @param isHittingSomething whether the hitbox is hitting something
     */
    public void updateAppearance(boolean isHittingSomething) {
        // make it transparent if it's not debug time
        setDebug();
        if (!isDebug) {
            return;
        }
        GreenfootImage img = getImage();
        img.clear();
        if (isHittingSomething) {
            img.setColor(new Color(255, 0, 0, 150)); // red, semi transparent
        } else {
            img.setColor(new Color(0, 255, 0, 150)); // green, semi transparent
        }

        // fill area, draw border
        img.fillRect(0, 0, img.getWidth() - 1, img.getHeight() - 1);
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, img.getWidth() - 1, img.getHeight() - 1);
        setImage(img);
    }

    /**
     * makes the hitbox invisible in case it's not debug time.
     */
    public void setDebug() {
        GreenfootImage img = getImage();
        if (Greenfoot.isKeyDown("f12")) {
            this.isDebug = true;
            img.setTransparency(255);
        } else {
            this.isDebug = false;
            img.setTransparency(0);
        }
        setImage(img);
    }

}