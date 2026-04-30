package entity;

import greenfoot.*;

import java.util.List;

/**
 * @author matii
 * @version hopefully the last one
 */

public class Hitbox extends Actor {
    private final Entity OWNER; // CAPS, because it's a constant
    private final int width;
    private final int height;
    private int widthInCells, heightInCells;

    private boolean isHittingSomething;
    private boolean isDebug;

    public Hitbox(int width, int height, Entity owner) {
        this.OWNER = owner;
        this.width = width;
        this.height = height;

        this.isHittingSomething = false;
        this.isDebug = false;
    }

    public void addedToWorld(World world) {
        this.widthInCells = this.width * world.getCellSize() + world.getCellSize() / 2;
        this.heightInCells = this.height * world.getCellSize() + world.getCellSize() / 2;

        // w, h*2 bc my offset is in each direction

        GreenfootImage img = new GreenfootImage(widthInCells * 2, heightInCells * 2);

        img.setColor(Color.RED);
        img.drawRect(0, 0, (widthInCells * 2) - 1, (heightInCells * 2) - 1);

        setImage(img);
    }

    public void act() {
        // rm hitbox when player is gone
        if (OWNER == null || OWNER.getWorld() == null) {
            getWorld().removeObject(this);
            return;
        }

        //center to player
        setLocation(OWNER.getX(), OWNER.getY());

        checkTouching();
        setDebug();

        // handles visibility
        updateAppearance(isHittingSomething);
    }

    public void checkTouching() {

        List<Entity> overlapping = getIntersectingObjects(Entity.class);

        boolean foundTarget = false;
        for (Actor actor : overlapping) {
            if (actor != OWNER && actor != this) {
                foundTarget = true;
                break;
            }
        }

        if (foundTarget) {
            OWNER.onHit(OWNER);
        }

        isHittingSomething = foundTarget;
    }

    public void updateAppearance(boolean isHittingSomething) {
        GreenfootImage img = getImage();

        // make it transparent if it's not debug time
        if (!isDebug) {
            img.setTransparency(0);
            return;
        } else {
            img.setTransparency(255);
        }

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
    }

    public void setDebug() {
        if (Greenfoot.isKeyDown("f1")) {
            this.isDebug = true;
            return;
        }
        this.isDebug = false;
    }

}