package entities.tower;

import core.Clickable;
import entities.Entity;
import entities.enemy.Enemy;
import entities.tower.util.RangeDisplay;
import greenfoot.*;
import map.levels.Map;
import map.levels.util.Path;
import ui.hud.UpgradeMenu;

import java.util.List;
import java.util.Objects;

/**
 * @author matii
 * @version hopefully the last one
 */

public abstract class Tower extends Entity implements Clickable {
    private boolean isPlacing;
    private final RangeDisplay RANGEDISPLAY;

    private Enemy targetedEnemy;

    private boolean canPlace;

    private final int range;


    public Tower(boolean isPlacing, int range) {
        this.RANGEDISPLAY = new RangeDisplay(this,range,isPlacing);
        this.isPlacing = isPlacing;
        this.canPlace = true;

        targetedEnemy = null;

        this.range = range;
    }

    public void addedToWorld(World world) {
        super.addedToWorld(world);
        world.addObject(RANGEDISPLAY,getX(),getY());
        RANGEDISPLAY.setRangeVisibility(false, null);
    }

    public void act() {
        checkClick();
        if (isPlacing) {
            followCursor();
            RANGEDISPLAY.setFollowing(true);
        } else {
            RANGEDISPLAY.setFollowing(false);
            setTargetedEnemy();
            targetEnemy(targetedEnemy);
        }
        this.canPlace = true; //default value


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

    /**
     * {@code  if isPlacing AND canPlace:}<br>
     * Places the Tower.<br>
     * {@code if not isPlacing:}<br>
     * Toggles the UpgradeMenu.<br>
     */
    public void onClick() {

        if (isPlacing && canPlace) {
            place();
            return;
        } else if(!isPlacing){
            Map map = (Map) getWorld();
            UpgradeMenu upgradeMenu = map.getUpgradeMenu();
            if(upgradeMenu == null) {
                map.setUpgradeMenuVisibility(true, this);
            } else if(upgradeMenu.getTower() != this) {
                map.setUpgradeMenuVisibility(true, this);
            } else {
                map.setUpgradeMenuVisibility(false, null);
            }


             //TODO make it so it checks the specific upgrade menu for this tower @Mathilo
        }
    }

    public void onHover() {
        if (!RANGEDISPLAY.isRangeVisible) {
            RANGEDISPLAY.setRangeVisibility(true, new Color(128, 128, 128, 128));
        }
    }

    public void onUnhover() {
        if (RANGEDISPLAY.isRangeVisible) {
            RANGEDISPLAY.setRangeVisibility(false, null);
        }
    }

    /**
     * Places the tower onto the map and makes the range invisible.
     */
    public void place() {
        isPlacing = false;
        RANGEDISPLAY.setRangeVisibility(false, null);
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
        if (!RANGEDISPLAY.isRangeVisible) {
            if (canPlace) {
                RANGEDISPLAY.setRangeVisibility(true, new Color(128, 128, 128, 128));
            } else {
                RANGEDISPLAY.setRangeVisibility(true, new Color(128, 0, 0, 128));
            }
        } else {
            if (canPlace) {
                RANGEDISPLAY.setRangeVisibility(true, new Color(128, 128, 128, 128));
            } else {
                //grey range, should be red
                RANGEDISPLAY.setRangeVisibility(true, new Color(128, 0, 0, 128));
            }
        }
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
        List<Enemy> enemiesInRange = getObjectsInRange(range,Enemy.class);
        if(enemiesInRange.isEmpty()) {
            this.targetedEnemy = null;
            return;
        }
        this.targetedEnemy = enemiesInRange.get(0);
    }
}
