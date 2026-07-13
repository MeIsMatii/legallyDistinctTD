package entities.tower.util;

import core.MainClass;
import entities.tower.Tower;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class RangeDisplay extends MainClass {
    //TODO fix @Mathilo
    private final Tower OWNER;
    public boolean isRangeVisible;
    private double range;
    private boolean isFollowing;

    public RangeDisplay(Tower tower, double range, boolean isPlacing) {
        setImage("invisible.png");
        this.OWNER = tower;
        this.range = range;
        this.isRangeVisible = isPlacing;
        this.isFollowing = isPlacing;
    }


    public void act() {
        if(isPaused()) return;

        if (OWNER == null || OWNER.getWorld() == null) {
            getWorld().removeObject(this);
            return;
        }

        if(this.range != OWNER.getRange()) {
            updateRange(OWNER.getRange());
        }

        if (isFollowing) {
            followTower();
        }
    }

    public void setFollowing(boolean state) {
        this.isFollowing = state;
    }

    public void followTower() {
        setLocation(OWNER.getX(), OWNER.getY());
    }


    /**
     * Sets the visibility and colour of the range display circle.
     *
     * @param state whether the range is visible or not.
     * @param color the colour (can be null) that the range display is set to, in case it's visible.
     */
    public void setRangeVisibility(boolean state, Color color) {
        if (!state) {
            displayRange(new Color(255, 255, 255, 1)); //invisible
        } else {
            displayRange(color);
        }
        isRangeVisible = state;
    }

    public void updateRange(double range) {
        this.range = range;
    }

    /**
     * sets the required variables to be given to the getGreenfootImage() func. <br>
     * sets the image to the returned image.
     *
     * @param color the colour of the circle.
     */
    public void displayRange(Color color) {
        ///AAAAAAAAAAAAAAAAAAAAAAAAAAAA WHY DID I DO THIS
        /// WHY DID I NOT JUST LET SOMEONE ELSE DO IT
        // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA --Mathilo
        int radius = (int) Math.round(range) * getWorld().getCellSize();
        int diameter = radius * 2;

        GreenfootImage canvas = getGreenfootImage(diameter, color);

        setImage(canvas);

        isRangeVisible = true;
    }

    /**
     * draws a circle based on the diameter in the chosen colour.
     *
     * @param size  the diameter of the circle.
     * @param color the colour of the circle.
     * @return the image.
     */
    private GreenfootImage getGreenfootImage(int size, Color color) {
        GreenfootImage img = new GreenfootImage("invisible.png");

        GreenfootImage canvas = new GreenfootImage(size, size);

        canvas.setColor(color); //semi transparent grey  //fuck you intelliJ, im speak BE not AE
        canvas.fillOval(0, 0, size, size);
        canvas.drawOval(0, 0, size, size);

        canvas.drawImage(img, (size - img.getWidth()) / 2, (size - img.getHeight()) / 2); //so the original image is not in the top left
        return canvas;
    }


    /// TARGETING

    //getObjectsInRange exists D:


}
