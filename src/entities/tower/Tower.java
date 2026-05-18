package entities.tower;

import core.Clickable;
import entities.Entity;
import entities.enemy.Enemy;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import map.levels.Map;
import map.levels.util.Path;

import java.util.List;
import java.util.Objects;

/**
 * @author matii
 * @version hopefully the last one
 */

abstract class Tower extends Entity implements Clickable {
    private boolean isPlacing;
    private boolean isRangeVisible;
    private final GreenfootImage sprite;

    private Enemy targetedEnemy;

    private boolean canPlace;

    private final int range;


    public Tower(boolean isPlacing, int range, String spriteName) {
        this.sprite = new GreenfootImage(spriteName);
        setRangeVisibility(false, null);
        this.isPlacing = isPlacing;
        this.canPlace = true;

        targetedEnemy = null;

        this.range = range;
    }

    public void act() {

        if (isPlacing) {
            followCursor();
            checkClick();
        }
        this.canPlace = true; //default value


    }

    /**
     * Sets the visibility and colour of the range display circle.
     *
     * @param state whether the range is visible or not.
     * @param color the colour (can be null) that the range display is set to, in case it's visible.
     */
    public void setRangeVisibility(boolean state, Color color) {
        if (!state) {
            setImage(sprite);
        } else {
            displayRange(color);
        }

        isRangeVisible = state;
    }

    public void onHit(Entity hitter) {
        if (!(hitter instanceof Path) && !(hitter instanceof Tower)) { ///is there a better way than instanceof?
            canPlace = true;
            return;
        }
        canPlace = false;
    }

    public void checkHover(boolean isHovering) {
        if (isPlacing) {
            return;
        }
        super.checkHover(isHovering);
    }


    public void onClick() {
        if (isPlacing && canPlace) {
            place();
        } else {
            Map map = (Map) getWorld();

        }
    }

    public void onHover() {
        if (!isRangeVisible) {
            setRangeVisibility(true, new Color(128, 128, 128, 128));
        }
    }

    public void onUnhover() {
        if (isRangeVisible) {
            setRangeVisibility(false, null);
        }
    }

    /**
     * Places the tower onto the map and makes the range invisible.
     */
    public void place() {
        isPlacing = false;
        setRangeVisibility(false, null);
        getHitbox().setFollowing(false);
    }

    /**
     * Makes the tower follow the cursor. <br>
     * Calls the checkPlacement() func to set the placement indicator.
     */
    public void followCursor() {

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        Map currMap = (Map) getWorld();
        if (mouseInfo == null || currMap.getCURSOR().getX() > 1620) {

            return;
        }

        int mouseX = mouseInfo.getX();
        int mouseY = mouseInfo.getY();

        setLocation(mouseX, mouseY);
        checkPlacement();
    }

    /**
     * Checks whether the tower can be placed in the current location.<br>
     * {@code red} when the placement location is obstructed.<br>
     * {@code grey} when the placement location is valid.<br>
     */
    public void checkPlacement() {
        if (!isRangeVisible) {
            if (canPlace) {
                setRangeVisibility(true, new Color(128, 128, 128, 128));
            } else {
                setRangeVisibility(true, new Color(128, 0, 0, 128));
            }
        } else {
            if (Objects.equals(getImage().getColor(), new Color(128, 0, 0, 128)) && canPlace) {
                //red range, should be grey
                setImage(sprite);
                setRangeVisibility(true, new Color(128, 128, 128, 128));
            } else if (Objects.equals(getImage().getColor(), new Color(128, 128, 128, 128)) && !canPlace) {
                //grey range, should be red
                setImage(sprite);
                setRangeVisibility(true, new Color(128, 0, 0, 128));
            }
        }
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
        int radius = range * getWorld().getCellSize();
        int diameter = radius * 2;

        GreenfootImage canvas = getGreenfootImage(diameter, color);

        setImage(canvas);

        isRangeVisible = true;
    }

    /**
     * draws a circle based on the diameter in the chosen colour.
     *
     * @param diameter the diameter of the circle.
     * @param color    the colour of the circle.
     * @return the image.
     */
    private GreenfootImage getGreenfootImage(int diameter, Color color) {
        GreenfootImage img = getImage();

        int size = Math.max(diameter, Math.max(img.getWidth(), img.getHeight()));
        GreenfootImage canvas = new GreenfootImage(size, size);

        canvas.setColor(color); //semi transparent grey  //fuck you intelliJ, im speak BE not AE
        canvas.fillOval(0, 0, diameter, diameter);
        canvas.drawOval(0, 0, diameter, diameter);

        canvas.drawImage(img, (size - img.getWidth()) / 2, (size - img.getHeight()) / 2); //so the original image is not in the top left
        return canvas;
    }

    /**
     * Targets an enemy
     *
     * @param enemy the Enemy to target
     */
    public void targetEnemy(Enemy enemy) {
        if (enemy == null) {
            return;
        }
        turnTowards(enemy.getX(), enemy.getY());
        targetedEnemy = enemy;
    }

    /**
     * nothing<br>
     * ...yet
     */
    public void shoot() {
        // TODO implement @Mathilo
    }


    public void setTargetedEnemy() {
        // TODO check in range and not inside the hitbox D: @Mathilo
        List<Enemy> enemiesInRange = super.getHitbox().getSpecificEntitiesInHitbox(Enemy.class);
    }
}
