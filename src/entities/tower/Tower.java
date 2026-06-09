package entities.tower;

import core.Clickable;
import entities.Entity;
import entities.enemy.Enemy;
import entities.tower.util.RangeDisplay;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;
import greenfoot.World;
import map.levels.Map;
import map.levels.util.Path;
import ui.hud.UpgradeMenu;

import java.util.List;

/**
 * @author matii
 * @version hopefully the last one
 */

public abstract class Tower extends Entity implements Clickable {
    private final RangeDisplay RANGEDISPLAY;
    private final int range;
    private final Color colorRed = new Color(128, 0, 0, 128);
    private final Color colorGrey = new Color(128, 128, 128, 128);
    private boolean isPlacing;
    private Enemy targetedEnemy;
    private boolean canPlace;
    private int projectileDamage;
    private int projectileSpeed;
    private int projectilePiercing;
    private int projectileIFrames;
    private int upgrade1 = 0;
    private int upgrade2 = 0;
    private int upgrade3 = 0;

    private int shootingDelay;
    private int shootingDelayCounter;

    public Tower(boolean isPlacing, int range, int shootingDelay, int projectileDamage, int projectileSpeed, int projectilePiercing, int projectileIFrames) {
        this.RANGEDISPLAY = new RangeDisplay(this, range, isPlacing);
        this.isPlacing = isPlacing;
        this.canPlace = true;

        targetedEnemy = null;

        this.range = range;

        this.shootingDelay = shootingDelay;
        this.shootingDelayCounter = shootingDelay;

        this.projectileIFrames = projectileIFrames;
        this.projectileDamage = projectileDamage;
        this.projectileSpeed = projectileSpeed;
        this.projectilePiercing = projectilePiercing;
    }

    public void addedToWorld(World world) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = getImage().getWidth() / CELLSIZE - 30;
        int hitboxHeight = getImage().getHeight() / CELLSIZE - 30;
        spawnHitbox(hitboxWidth, hitboxHeight);

        world.addObject(RANGEDISPLAY, getX(), getY());
        RANGEDISPLAY.setRangeVisibility(false, null);
    }

    public int getProjectileDamage() {
        return projectileDamage;
    }

    public void setProjectileDamage(int projectileDamage) {
        this.projectileDamage = projectileDamage;
    }

    public int getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(int projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public int getProjectilePiercing() {
        return projectilePiercing;
    }

    public void setProjectilePiercing(int projectilePiercing) {
        this.projectilePiercing = projectilePiercing;
    }

    public int getProjectileIFrames() {
        return projectileIFrames;
    }

    public void setProjectileIFrames(int projectileIFrames) {
        this.projectileIFrames = projectileIFrames;
    }

    public int getShootingDelay() {
        return shootingDelay;
    }

    public void setShootingDelay(int shootingDelay) {
        this.shootingDelay = shootingDelay;
    }

    public int getUpgrade1() {
        return upgrade1;
    }

    public void setUpgrade1(int upgrade1) {
        this.upgrade1 = upgrade1;
    }

    public int getUpgrade2() {
        return upgrade2;
    }

    public void setUpgrade2(int upgrade2) {
        this.upgrade2 = upgrade2;
    }

    public int getUpgrade3() {
        return upgrade3;
    }

    public void setUpgrade3(int upgrade3) {
        this.upgrade3 = upgrade3;
    }

    public void act() {
        if(isPaused()) return;

        checkClick();
        if (isPlacing) {
            followCursor();
            RANGEDISPLAY.setFollowing(true);
        } else {
            RANGEDISPLAY.setFollowing(false);
            setTargetedEnemy();
            targetEnemy(targetedEnemy);

            if (targetedEnemy != null && canShoot()) {
                shoot(targetedEnemy);
                shootingDelayCounter = 0;
            }
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
        } else if (!isPlacing) {
            Map map = (Map) getWorld();
            UpgradeMenu upgradeMenu = map.getUpgradeMenu();
            if (upgradeMenu == null) {
                map.setUpgradeMenuVisibility(true, this);
            } else if (upgradeMenu.getTower() != this) {
                map.setUpgradeMenuVisibility(true, this);
            } else {
                map.setUpgradeMenuVisibility(false, null);
            }


            //TODO make it so it checks the specific upgrade menu for this tower @Mathilo
        }
    }

    public void onHover() {
        if (!RANGEDISPLAY.isRangeVisible) {
            RANGEDISPLAY.setRangeVisibility(true, colorGrey);
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
    }

    /**
     * Makes the tower follow the cursor. <br>
     * Calls the checkPlacement() func to set the placement indicator.
     */
    public void followCursor() {

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo == null) {
            return;
        }

        int mouseX = mouseInfo.getX();
        int mouseY = mouseInfo.getY();

        if (mouseX != getX() || mouseY != getY()) {
            setLocation(mouseX, mouseY);
        }
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
                RANGEDISPLAY.setRangeVisibility(true, colorGrey);
            } else {
                RANGEDISPLAY.setRangeVisibility(true, colorRed);
            }
        } else {
            if (canPlace) {
                RANGEDISPLAY.setRangeVisibility(true, colorGrey);
            } else {
                //grey range, should be red
                RANGEDISPLAY.setRangeVisibility(true, colorRed);
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

    public abstract String upgrade1(); // wouldn't it be good if we could make it give back multiple things(string + int)

    public abstract String upgrade2();

    public abstract String upgrade3();


    public boolean canShoot() {
        if (shootingDelayCounter < shootingDelay) {
            shootingDelayCounter++;
            return false;
        }
        return true;
    }

    /**
     * nothing<br>
     * ...yet
     */
    abstract void shoot(Enemy e);


    public void setTargetedEnemy() {
        // TODO check in range and not inside the hitbox D: @Mathilo
        List<Enemy> enemiesInRange = getObjectsInRange(range, Enemy.class);
        if (enemiesInRange.isEmpty()) {
            this.targetedEnemy = null;
            return;
        }
        this.targetedEnemy = enemiesInRange.get(0);
    }


}
