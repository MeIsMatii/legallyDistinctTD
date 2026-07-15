package entities;

import core.MainClass;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import util.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author matii
 * @version hopefully the last one
 */

public class Hitbox extends MainClass {
    private final Entity owner;
    private final int width;
    private final int height;
    private boolean wasHovering;

    private boolean isHittingSomething;
    private boolean isDebug;
    private boolean isFollowing;

    public Hitbox(int width, int height, Entity owner) {
        this.owner = owner;
        this.width = width;
        this.height = height;
        this.isFollowing = true;

        this.wasHovering = false;
        this.isHittingSomething = false;
        this.isDebug = false;
    }

    public Entity getOwner() {
        return this.owner;
    }

    public void addedToWorld(World world) {
        int widthInCells = this.width * world.getCellSize() + world.getCellSize() / 2; //this was from when we were not using 1px cells   //ima just leave it here --Mathilo
        int heightInCells = this.height * world.getCellSize() + world.getCellSize() / 2;

        // w, h*2 bc my offset is in each direction
        setImage(drawHitbox(widthInCells, heightInCells, Color.RED, false));

        updateAppearance(isHittingSomething);
    }

    public GreenfootImage drawHitbox(int w, int h, Color color, boolean fill) {
        GreenfootImage img = new GreenfootImage(w, h);
        img.setColor(color);
        if (fill) {
            img.fillRect(0, 0, w - 1, h - 1);
            img.setColor(Color.BLACK);
        }
        img.drawRect(0, 0, w - 1, h - 1);


        return img;
    }

    public void setFollowing(boolean state) {
        this.isFollowing = state;
    }

    public void act() {
        // rm hitbox when player is gone
        if (owner == null || owner.getWorld() == null) {
            getWorld().removeObject(this);
            return;
        }

        if ((getX() != owner.getX() || getY() != owner.getY()) && isFollowing) {
            followPlayer();
        }

        checkTouching();

        // handles visibility
        updateAppearance(isHittingSomething);


        boolean isHovering = checkHover();
        //System.out.println(isHovering + ", " + wasHovering);
        if (isHovering != wasHovering) {
            owner.checkHover(isHovering);
            wasHovering = isHovering;
        }
    }

    /**
     * centers itself to the player.
     */
    public void followPlayer() {
        setLocation(owner.getX(), owner.getY());
        setRotation(owner.getRotation());
    }

    /**
     * incase it is touching another hitbox, it calls the onHit function inside the owner.
     */
    public void checkTouching() {
        List<Hitbox> overlapping = getIntersectingObjects(Hitbox.class);

        boolean foundTarget = false;
        for (Hitbox hitbox : overlapping) {
            if (hitbox != this) {
                foundTarget = true;
                /// DEBUG
                ///System.out.printf("Owner: %s, Hitter: %s\n", OWNER, actor);
                owner.onHit(hitbox.getOwner());

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
            entities.add(hitbox.getOwner());
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
            if (targetedClass.isInstance(hitbox.getOwner())) {
                entities.add(targetedClass.cast(hitbox.getOwner()));
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
            setImage(drawHitbox(img.getWidth(), img.getHeight(), new Color(255, 0, 0, 150), true)); // red, semi transparent
        } else {
            setImage(drawHitbox(img.getWidth(), img.getHeight(), new Color(0, 255, 0, 150), true)); // green, semi transparent
        }
        //setImage(drawHitbox(img.getWidth(), img.getHeight(), Color.BLACK, false)); //black outline
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