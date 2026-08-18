package entities.base;

import entities.Entity;
import entities.Hitbox;
import entities.tower.util.RangeDisplay;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;
import greenfoot.World;
import maps.levels.GameMap;
import maps.levels.util.Path;
import ui.hud.towerSelector.TowerSelector;
import ui.hud.upgrades.UpgradeMenu;
import util.Animations;
import util.Clickable;
import util.HasSound;
import util.multiplayer.NetworkManager;

import java.util.List;
import java.util.UUID;

/**
 * @author mati
 * @version hopefully the last one
 */

public abstract class Tower extends Entity implements Clickable, Animations, HasSound {
    protected final int price;

    private final RangeDisplay rangeDisplay;
    private final Color colorRed = new Color(128, 0, 0, 128);
    private final Color colorGrey = new Color(128, 128, 128, 128);
    protected int[] upgrade1Prices;
    protected int[] upgrade2Prices;
    protected int[] upgrade3Prices;
    protected String[] upgradeDescription1 = new String[3];
    protected String[] upgradeDescription2 = new String[3];
    protected String[] upgradeDescription3 = new String[3];
    protected Projectile projectileToShoot = null;
    protected double projectileDamage;
    protected double projectileSpeed;
    protected double projectilePiercing;
    protected int projectileIFrames;
    protected int upgrade1 = 0;
    protected int upgrade2 = 0;
    protected int upgrade3 = 0;
    private int moneySpentOnUpgrades = 0;

    protected int shootingDelay;
    protected int shootingDelayCounter;
    private double range;
    private boolean isPlacing;
    private Enemy targetedEnemy;
    private boolean canPlace;
    /// <ANIMATIONS>
    private int frameCounter = 0;
    private List<String> frameList;
    private int frameIndex = 0;
    private boolean isAnimating = false;
    private String spritePath;
    private String spriteName;
    protected int[] projectileSpawnOffset = new int[2];

    /// </ANIMATIONS>

    public Tower(int price, boolean isPlacing, int range, int shootingDelay, int projectileDamage, int projectileSpeed, int projectilePiercing, int projectileIFrames) {
        super();
        this.rangeDisplay = new RangeDisplay(this, range, isPlacing);
        this.price = price;
        this.uniqueId = UUID.randomUUID().toString();

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

        setImage("towers/" + getName() + "/" + getName() + "_idle.png");
        setSpriteName(getName());
        setSpritePath("towers/" + getName());

    }


    protected boolean canPlace() {
        return canPlace;
    }

    protected void setCanPlace(boolean canPlace) {                   //hat jannis(ich) jetzt für traptower gemacht hoffe ist ok
        this.canPlace = canPlace;
    }

    public void addedToWorld(World world) {
        Animations.super.addedToWorld(world);
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = getImage().getWidth() / CELLSIZE - 30;
        int hitboxHeight = getImage().getHeight() / CELLSIZE - 30;
        spawnHitbox(hitboxWidth, hitboxHeight);

        world.addObject(rangeDisplay, getX(), getY());
        rangeDisplay.setRangeVisibility(false, null);
    }

    public Color getColorRed() {
        return colorRed;
    }

    public Color getColorGrey() {
        return colorGrey;
    }

    public RangeDisplay getRangeDisplay() {
        return rangeDisplay;
    }

    public int getPrice() {
        return price;
    }


    public int getMoneySpentOnUpgrades() {
        return moneySpentOnUpgrades;
    }

    public void setMoneySpentOnUpgrades(int moneySpentOnUpgrades) {
        this.moneySpentOnUpgrades = moneySpentOnUpgrades;
    }

    private void trackUpgradeCost(int path) {
        int[] prices = null;
        int currentLevel = 0;

        switch (path) {
            case 1:
                prices = upgrade1Prices;
                currentLevel = getUpgrade1();
                break;
            case 2:
                prices = upgrade2Prices;
                currentLevel = getUpgrade2();
                break;
            case 3:
                prices = upgrade3Prices;
                currentLevel = getUpgrade3();
                break;
        }

        if (prices != null && currentLevel >= 0 && currentLevel < prices.length) {
            this.moneySpentOnUpgrades += prices[currentLevel];
        }
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getProjectileDamage() {
        return projectileDamage;
    }

    public void setProjectileDamage(double projectileDamage) {
        this.projectileDamage = projectileDamage;
    }

    public double getProjectileSpeed() {
        return projectileSpeed;
    }

    public void setProjectileSpeed(double projectileSpeed) {
        this.projectileSpeed = projectileSpeed;
    }

    public double getProjectilePiercing() {
        return projectilePiercing;
    }

    public void setProjectilePiercing(double projectilePiercing) {
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

    public int[] getUpgrade1Prices() {
        return upgrade1Prices;
    }

    public void setUpgrade1Prices(int[] upgrade1Prices) {
        this.upgrade1Prices = upgrade1Prices;
    }

    public int[] getUpgrade2Prices() {
        return upgrade2Prices;
    }

    public void setUpgrade2Prices(int[] upgrade2Prices) {
        this.upgrade2Prices = upgrade2Prices;
    }

    public int[] getUpgrade3Prices() {
        return upgrade3Prices;
    }

    public void setUpgrade3Prices(int[] upgrade3Prices) {
        this.upgrade3Prices = upgrade3Prices;
    }

    public String getUpgradeDescription1() {
        return upgradeDescription1[getUpgrade1()];
    }

    public void setUpgradeDescription1(String[] desc) {
        this.upgradeDescription1 = desc;
    }

    public String getUpgradeDescription2() {
        return upgradeDescription2[getUpgrade2()];
    }

    public void setUpgradeDescription2(String[] desc) {
        this.upgradeDescription2 = desc;
    }

    public String getUpgradeDescription3() {
        return upgradeDescription3[getUpgrade3()];
    }

    public void setUpgradeDescription3(String[] desc) {
        this.upgradeDescription3 = desc;
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
            rangeDisplay.setFollowing(true);
        } else {
            shootingDelayCounter++;
            rangeDisplay.setFollowing(false);
            if (NetworkManager.getInstance().isHost()) {
                setTargetedEnemy();
                if (NetworkManager.getInstance().isMultiplayer() && targetedEnemy != null) {
                    String msg = "SET_TARGETED_ENEMY" + "," + getUniqueId() + "," + targetedEnemy.getUniqueId();
                    NetworkManager.getInstance().sendData(msg);
                }
            }

            if (targetedEnemy != null && canShoot()) {
                shoot(targetedEnemy);
                startAnimation();
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

    public void startAnimation() {
        Animations.super.startAnimation();
    }

    public void onHit(Entity hitter) {
    }

    public void checkHover(boolean isHovering) {
        if (isPlacing) {
            return;
        }
        super.checkHover(isHovering);
    }

    public void onClick() {
        if ((isPlacing && canPlace) || getX() > 1620) {
            if (!(getX() > 1620)) {
                place();
            } else if (getWorld().getObjectsAt(getX(), getY(), TowerSelector.class).isEmpty()) {
                GameMap gameMap = (GameMap) getWorld();
                gameMap.getPlayer().setCoins(gameMap.getPlayer().getCoins() + getPrice());
                getWorld().removeObject(this);
            }
        } else if (!isPlacing) {
            GameMap gameMap = (GameMap) getWorld();
            UpgradeMenu upgradeMenu = gameMap.getUpgradeMenu();
            if (upgradeMenu == null) {
                gameMap.setUpgradeMenuVisibility(true, this);
            } else if (upgradeMenu.getTower() != this) {
                gameMap.setUpgradeMenuVisibility(true, this);
            } else {
                gameMap.setUpgradeMenuVisibility(false, null);
            }
        }
    }

    public void onHover() {
        if (!rangeDisplay.isRangeVisible) {
            rangeDisplay.setRangeVisibility(true, colorGrey);
        }
    }

    public void onUnhover() {
        if (rangeDisplay.isRangeVisible) {
            rangeDisplay.setRangeVisibility(false, null);
        }
    }

    public void place() {
        isPlacing = false;
        rangeDisplay.setRangeVisibility(false, null);
        playSound("Place.mp3");

        if (getWorldOfType(GameMap.class).isMultiplayer()) {
            String msg = "SPAWN_TOWER" + "," + getName() + "," + uniqueId + "," + getX() + "," + getY();
            NetworkManager.getInstance().sendData(msg);
        }
    }

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

    public void checkPlacement() {
        List<Hitbox> hitboxes = getIntersectingObjects(Hitbox.class);
        canPlace = true;
        if (getX() > 1620) {
            rangeDisplay.setRangeVisibility(false, null);
            return;
        }
        if (canPlace) {
            for (Hitbox hitbox : hitboxes) {
                if ((hitbox.getOwner() instanceof Path || hitbox.getOwner() instanceof Tower) && hitbox.getOwner() != this) {
                    canPlace = false;
                    break;
                }
            }
        }

        if (canPlace) {
            rangeDisplay.setRangeVisibility(true, colorGrey);
        } else {
            rangeDisplay.setRangeVisibility(true, colorRed);
        }
    }

    public void upgrade1() {
        if(getWorld() == null) {
            return;
        }

        trackUpgradeCost(1);

        int oldLevel = getUpgrade1();
        setUpgrade1(getUpgrade1() + 1);
        System.out.println(getName() + " upgrade1 level:" + oldLevel + "->" + getUpgrade1());
        upgrade(1);
        onUpgrade(1);

        if (getWorldOfType(GameMap.class).isMultiplayer()) {
            String msg = "UPGRADE_TOWER" + "," + getUniqueId() + "," + 1 + "," + getUpgrade1();
            NetworkManager.getInstance().sendData(msg);
        }
    }

    public void upgrade1(boolean receivedFromNetwork) {
        if(getWorld() == null) {
            return;
        }

        trackUpgradeCost(1);

        int oldLevel = getUpgrade1();
        setUpgrade1(getUpgrade1() + 1);
        System.out.println(getName() + " upgrade1 level:" + oldLevel + "->" + getUpgrade1());
        upgrade(1);
        onUpgrade(1);

        if (getWorldOfType(GameMap.class).isMultiplayer() && !receivedFromNetwork) {
            String msg = "UPGRADE_TOWER" + "," + getUniqueId() + "," + 1 + "," + getUpgrade1();
            NetworkManager.getInstance().sendData(msg);
        }
    }

    public void upgrade2() {
        if(getWorld() == null) {
            return;
        }

        trackUpgradeCost(2);

        int oldLevel = getUpgrade2();
        setUpgrade2(getUpgrade2() + 1);
        System.out.println(getName() + " upgrade2 level:" + oldLevel + "->" + getUpgrade2());
        upgrade(2);
        onUpgrade(2);
        if (getWorldOfType(GameMap.class).isMultiplayer()) {
            String msg = "UPGRADE_TOWER" + "," + getUniqueId() + "," + 2 + "," + getUpgrade2();
            NetworkManager.getInstance().sendData(msg);
        }
    }

    public void upgrade2(boolean receivedFromNetwork) {
        if(getWorld() == null) {
            return;
        }

        trackUpgradeCost(2);

        int oldLevel = getUpgrade2();
        setUpgrade2(getUpgrade2() + 1);
        System.out.println(getName() + " upgrade2 level:" + oldLevel + "->" + getUpgrade2());
        upgrade(2);
        onUpgrade(2);
        if (getWorldOfType(GameMap.class).isMultiplayer() && !receivedFromNetwork) {
            String msg = "UPGRADE_TOWER" + "," + getUniqueId() + "," + 2 + "," + getUpgrade2();
            NetworkManager.getInstance().sendData(msg);
        }
    }

    public void upgrade3() {
        if(getWorld() == null) {
            return;
        }

        trackUpgradeCost(3);

        int oldLevel = getUpgrade3();
        setUpgrade3(getUpgrade3() + 1);
        System.out.println(getName() + " upgrade3 level:" + oldLevel + "->" + getUpgrade3());
        upgrade(3);
        onUpgrade(3);
        if (getWorldOfType(GameMap.class).isMultiplayer()) {
            String msg = "UPGRADE_TOWER" + "," + getUniqueId() + "," + 3 + "," + getUpgrade3();
            NetworkManager.getInstance().sendData(msg);
        }
    }

    public void upgrade3(boolean receivedFromNetwork) {
        if(getWorld() == null) {
            return;
        }

        trackUpgradeCost(3);

        int oldLevel = getUpgrade3();
        setUpgrade3(getUpgrade3() + 1);
        System.out.println(getName() + " upgrade3 level:" + oldLevel + "->" + getUpgrade3());
        upgrade(3);
        onUpgrade(3);
        if (getWorldOfType(GameMap.class).isMultiplayer() && !receivedFromNetwork) {
            String msg = "UPGRADE_TOWER" + "," + getUniqueId() + "," + 3 + "," + getUpgrade3();
            NetworkManager.getInstance().sendData(msg);
        }
    }

    public boolean canShoot() {
        return shootingDelayCounter >= shootingDelay;
    }

    public Projectile createProjectile() {
        Projectile proj = null;
        try {
            proj =  projectileToShoot.getClass().getDeclaredConstructor().newInstance();
            proj.setOwner(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proj;
    }

    public Projectile getProjectileToShoot() {
        return this.projectileToShoot;
    }

    public void setProjectileToShoot(Projectile projectileToShoot) {
        this.projectileToShoot = projectileToShoot;
    }

    public void shoot(Enemy e) {
        Projectile projectile = createProjectile();
        if (projectile == null) {
            projectile = getProjectileToShoot();
        }
        if (projectile == null) {
            String string = "Projectile to shoot has not been defined in: " + getName();
            System.out.println(string);
            return;
        }
        NetworkManager nm = NetworkManager.getInstance();
        if (nm.isHost()) {
            getWorld().addObject(projectile, getX() + projectileSpawnOffset[0], getY() + + projectileSpawnOffset[1]);

            if (nm.isMultiplayer()) {
                String msg = "SPAWN_PROJECTILE" + "," + e.getUniqueId() + "," + projectile.getUniqueId() + "," + getUniqueId();
                nm.sendData(msg);
            }
        }
    }

    public void shoot(Enemy e, String projectileId) {
        if (projectileId.isBlank()) {
            shoot(e);
            return;
        }
        Projectile projectile = createProjectile();
        if (projectile == null) {
            projectile = getProjectileToShoot();
        }
        if (projectile == null) {
            String string = "Projectile to shoot has not been defined in: " + getName();
            System.out.println(string);
            return;
        }
        projectile.setUniqueId(projectileId);
        getWorld().addObject(projectile, getX(), getY());
    }

    public void setTargetedEnemyManual(Enemy e) {
        this.targetedEnemy = e;
    }

    public void setTargetedEnemy() {
        List<Enemy> enemiesInRange = getObjectsInRange((int) Math.round(range), Enemy.class);
        if (enemiesInRange.isEmpty()) {
            this.targetedEnemy = null;
            return;
        }
        this.targetedEnemy = enemiesInRange.get(0);
    }

    public Enemy getTargetedEnemy() {
        return targetedEnemy;
    }

    public abstract String getName();

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

    public void onUpgrade(int path) {
    }

    public abstract void upgrade(int path);
}