package entities.tower;

import entities.Entity;
import entities.Hitbox;
import entities.enemy.Enemy;
import entities.tower.util.RangeDisplay;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;
import greenfoot.World;
import map.levels.Map;
import map.levels.util.Path;
import ui.hud.TowerSelector;
import ui.hud.UpgradeMenu;
import util.Animations;
import util.Clickable;
import util.HasSound;

import java.util.List;

/**
 * @author mati
 * @version hopefully the last one
 */

public abstract class Tower extends Entity implements Clickable, Animations, HasSound {
    private final int PRICE;

    private final RangeDisplay RANGEDISPLAY;
    private final int range;
    private final Color colorRed = new Color(128, 0, 0, 128);
    private final Color colorGrey = new Color(128, 128, 128, 128);
    private boolean isPlacing;
    private Enemy targetedEnemy;
    private boolean canPlace;
    private double projectileDamage;
    private int projectileSpeed;
    private int projectilePiercing;
    private int projectileIFrames;
    private int upgrade1 = 0;
    private int upgrade2 = 0;
    private int upgrade3 = 0;
    private final int[] upgrades1 = new int[]{500, 500, 500, 500, 500};
    private final int[] upgrades2 = new int[]{500, 500, 500, 500, 500};
    private final int[] upgrades3 = new int[]{500, 500, 500, 500, 500};
    private int shootingDelay;
    private int shootingDelayCounter;
    /// <UPGRADES>
    private int frameCounter = 0;
    private List<String> frameList;
    private int frameIndex = 0;
    private boolean isAnimating = false;
    private String spritePath;
    private String spriteName;

    public Tower(int price, boolean isPlacing, int range, int shootingDelay, int projectileDamage, int projectileSpeed, int projectilePiercing, int projectileIFrames) {
        this.RANGEDISPLAY = new RangeDisplay(this, range, isPlacing);
        this.PRICE = price;

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

        setImage("towers/" + getTowerName() + "/" + getTowerName() + "_idle.png");

    }

    protected boolean getCanPlace() {
        return canPlace;
    }

    protected void setCanPlace(boolean canPlace) {                   //hat jannis(ich) jetzt für traptower gemacht hoffe ist ok
        this.canPlace = canPlace;
    }

    public void addedToWorld(World world) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = getImage().getWidth() / CELLSIZE - 30;
        int hitboxHeight = getImage().getHeight() / CELLSIZE - 30;
        spawnHitbox(hitboxWidth, hitboxHeight);

        world.addObject(RANGEDISPLAY, getX(), getY());
        RANGEDISPLAY.setRangeVisibility(false, null);
    }

    public Color getColorRed() {
        return colorRed;
    }

    public Color getColorGrey() {
        return colorGrey;
    }

    public RangeDisplay getRANGEDISPLAY() {
        return RANGEDISPLAY;
    }

    public int getPRICE() {
        return PRICE;
    }

    public double getProjectileDamage() {
        return projectileDamage;
    }

    public void setProjectileDamage(double projectileDamage) {
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

    public int[] getUpgrades1() {
        return upgrades1;
    }

    public int[] getUpgrades2() {
        return upgrades2;
    }

    public int[] getUpgrades3() {
        return upgrades3;
    }

    public void setShootingDelayCounter(int count) {
        shootingDelayCounter = count;
    }

    public boolean isPlacing() {
        return this.isPlacing;
    }

    public void setPlacing(boolean isPlacing) {
        this.isPlacing = isPlacing;
    }

    public void act() {
        if (isPaused()) return;

        checkClick();
        if (isPlacing) {
            followCursor();
            RANGEDISPLAY.setFollowing(true);
        } else {
            shootingDelayCounter++;
            RANGEDISPLAY.setFollowing(false);
            setTargetedEnemy();
            targetEnemy(targetedEnemy);

            if (targetedEnemy != null && canShoot()) {
                shoot(targetedEnemy);
                Animations.super.startAnimation();
                shootingDelayCounter = 0;
            }

            if (isAnimating) {
                frameCounter++;
                if (frameCounter > getAnimationSpeed()) {
                    playAnimation();
                    frameCounter = 0;
                }
            }
        }


    }

    public void onHit(Entity hitter) {
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
            if (!(getX() > 1620)) {
                place();
            } else if (getWorld().getObjectsAt(getX(), getY(), TowerSelector.class).isEmpty()) {
                Map map = (Map) getWorld();
                map.getPLAYER().setCoins(map.getPLAYER().getCoins() + getPRICE());
                getWorld().removeObject(this);
            }
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
        playSound("Place.mp3");
    }

    /**
     * Makes the tower follow the cursor. <br>
     * Calls the checkPlacement() func to set the placement indicator.
     */
    public void followCursor() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo == null || getWorld() == null) {
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


        List<Hitbox> hitboxes = getIntersectingObjects(Hitbox.class);
        canPlace = true;
        if (getX() > 1620) {
            RANGEDISPLAY.setRangeVisibility(false, null);
            return;
        }
        if (canPlace) {
            for (Hitbox hitbox : hitboxes) {
                if ((hitbox.getOWNER() instanceof Path || hitbox.getOWNER() instanceof Tower) && hitbox.getOWNER() != this) {
                    canPlace = false;
                    break;
                }
            }
        }

        if (canPlace) {
            RANGEDISPLAY.setRangeVisibility(true, colorGrey);
        } else {
            RANGEDISPLAY.setRangeVisibility(true, colorRed);
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
        //turnTowards(enemy.getX(), enemy.getY());
        targetedEnemy = enemy;
    }

    public abstract String upgrade1(); // wouldn't it be good if we could make it give back multiple things(string + int)

    public abstract String upgrade2();

    public abstract String upgrade3();

    public boolean canShoot() {
        return shootingDelayCounter >= shootingDelay;
    }

    /**
     * this method gets called when an enemy e is inside the range of the tower.
     */
    abstract void shoot(Enemy e);

    public void setTargetedEnemyManual(Enemy e) {
        this.targetedEnemy = e;
    }

    public void setTargetedEnemy() {
        // TODO check in range and not inside the hitbox D: @Mathilo
        List<Enemy> enemiesInRange = getObjectsInRange(range, Enemy.class);
        if (enemiesInRange.isEmpty()) {
            this.targetedEnemy = null;
            return;
        }
        this.targetedEnemy = enemiesInRange.get(0);
    }

    public Enemy getTargetedEnemy() {
        return targetedEnemy;
    }

    public abstract String getTowerName();

    public String getSpriteName() {
        return spriteName;
    }

    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public abstract int getAnimationSpeed();

    public int getFrameCounter() {
        return frameCounter;
    }

    public void setFrameCounter(int counter) {
        this.frameCounter = counter;
    }

    public List<String> getFrameList() {
        return frameList;
    }

    public void setFrameList(List<String> frames) {
        this.frameList = frames;
    }

    public int getCurrentFrameIndex() {
        return frameIndex;
    }

    public void setCurrentFrameIndex(int i) {
        this.frameIndex = i;
    }

    public void isAnimating(boolean isAnimating) {
        this.isAnimating = isAnimating;
    }

    /**
     * Changes the sprite and animation
     * @param path the path to upgrade
     */
    public void onUpgrade(int path) {
        int maxPath = (Math.max(Math.max(getUpgrade1(), getUpgrade2()), getUpgrade3()));
        //so a lesser upgrade does not override the animation
        if(maxPath != path) {
            return;
        }
        switch (path) {
            case 1:
                setSpriteName(getTowerName() + "_upgrade1");
                setSpritePath("towers/" + getTowerName() + "/upgrades/upgrade" + path + "/" + getUpgrade1());
                generateFrameList();
                break;

            case 2:
                setSpriteName(getTowerName() + "_upgrade2");
                setSpritePath("towers/" + getTowerName() + "/upgrades/upgrade" + path + "/" + getUpgrade2());
                generateFrameList();
                break;

            case 3:
                setSpriteName(getTowerName() + "_upgrade3");
                setSpritePath("towers/" + getTowerName() + "/upgrades/upgrade" + path + "/" + getUpgrade3());
                generateFrameList();
                break;

            default:
                System.out.println("Given Path must be between 1 & 3");
        }
    }
    ///</UPGRADES>
}
