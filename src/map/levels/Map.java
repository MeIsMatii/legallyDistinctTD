package map.levels;

import core.MainClass;
import core.Player;
import entities.Hitbox;
import entities.enemy.Enemy;
import entities.projectiles.Projectile;
import entities.tower.Tower;
import entities.tower.util.RangeDisplay;
import greenfoot.Greenfoot;
import greenfoot.World;
import map.levels.util.Path;
import map.levels.util.WaveManager;
import map.menu.PauseMenu;
import ui.hud.TowerSelectorSpawner;
import ui.hud.UpgradeMenu;
import ui.settings.SettingsPopup;
import util.Cursor;
import util.multiplayer.NetworkManager;
import util.saves.GameSaveManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

/**
 * @author paths: Julian
 * @author waves & gamesaves: Mati
 */
public abstract class Map extends World {
    private final Player player;
    private final Cursor cursor;
    private final int pathWidth;
    private final GameSaveManager gameSaveManager;
    private final WaveManager waveManager;
    private final int spawnDelay;
    private final List<Enemy> aliveEnemies = new ArrayList<>();
    private final boolean isMultiplayer;
    private UpgradeMenu upgradeMenu;
    private boolean isUpgradeMenuVisible;
    private int[] spawnLocation;
    private List<Enemy> enemiesToSpawn = new ArrayList<>();
    private int spawnDelayCounter = 0;
    private int waveEndMoney;
    private int receivedWaveMoney;
    private int wave = 0;
    private int oldWave = 0;

    private boolean isPaused;
    private boolean isForcedPause;

    private String lastKeyPressed;

    public Map() {
        super(1920, 1080, 1);

        System.out.println("Singleplayer");
        this.isMultiplayer = false;

        this.gameSaveManager = new GameSaveManager();
        this.waveManager = WaveManager.getInstance();
        this.spawnDelay = 45;

        gameSaveManager.setMapNr("map" + getMapNumber());
        addObject(gameSaveManager, 0, 0);

        setPaintOrder(Hitbox.class, Tower.class, RangeDisplay.class); //Tower infront of it's range

        this.pathWidth = 120;
        player = new Player(100, 100); //jannis ganz alleine gemacht
        cursor = new Cursor();

        isPaused = false;
        isForcedPause = false;

        lastKeyPressed = Greenfoot.getKey();

        addHud();
    }

    public Map(boolean isMultiplayer) {
        super(1920, 1080, 1);

        System.out.println("Multiplayer: " + isMultiplayer);
        this.isMultiplayer = isMultiplayer;
        if (isMultiplayer && NetworkManager.getInstance().isHost()) {
            NetworkManager.getInstance().sendData("MAP:" + getMapNumber());
        }

        this.gameSaveManager = new GameSaveManager();
        this.waveManager = WaveManager.getInstance();
        this.spawnDelay = 45;

        gameSaveManager.setMapNr("map" + getMapNumber());
        addObject(gameSaveManager, 0, 0);

        setPaintOrder(Hitbox.class, Tower.class, RangeDisplay.class); //Tower infront of it's range

        this.pathWidth = 120;
        player = new Player(100, 100); //jannis ganz alleine gemacht
        cursor = new Cursor();

        isPaused = false;
        isForcedPause = false;

        lastKeyPressed = Greenfoot.getKey();

        addHud();
    }

    /**
     * adds hud elements to the screen.
     */
    public void addHud() {
        upgradeMenu = null;
        addObject(player, 0, 0);

        addObject(cursor, 0, 0);

        addObject(new TowerSelectorSpawner(), 1770, 540);
    }

    /**
     * @return the current map number.
     */
    public abstract int getMapNumber();

    public void setUpgradeMenuVisibility(boolean isVisible, Tower tower) {
        isUpgradeMenuVisible = isVisible;
        //TODO add paths and delete them @Elias
        if (isVisible) {
            int width = (getWidth() - 300) / 2;
            if (upgradeMenu != null) {
                upgradeMenu.delete();
            }
            upgradeMenu = new UpgradeMenu(tower);
            addObject(upgradeMenu, width, getHeight() - 216 / 2);
        } else if (!getObjects(UpgradeMenu.class).isEmpty()) {
            upgradeMenu.delete();
            upgradeMenu = null;
        }
    }

    /**
     * @return the currently active upgrade screen or null.
     */
    public UpgradeMenu getUpgradeMenu() {
        return this.upgradeMenu;
    }

    /**
     * @return whether there is a visible upgrade screen.
     */
    public boolean isUpgradeMenuVisible() {
        return isUpgradeMenuVisible;
    }

    /**
     * @return the player (for lives etc).
     */
    public Player getPlayer() {                   //jannis
        return player;
    }

    /**
     * @return the gamesavemanager.
     */
    public GameSaveManager getGameSaveManager() {
        return gameSaveManager;
    }

    /**
     * @return the money given to the player via enemies dying from the current wave.
     */
    public int getReceivedWaveMoney() {
        return receivedWaveMoney;
    }

    /**
     * @return the spawn location for enemies.
     */
    public int[] getSpawnLocation() {
        if (this.spawnLocation == null) {
            throw new RuntimeException("No spawnlocation. Please fix.");
        }
        return this.spawnLocation;
    }

    /**
     * adds the paths for enemies to the map.
     *
     * @param pathList the list of corners.
     */
    public void addPath(int[][] pathList) {
        this.spawnLocation = pathList[0];
        for (int i = 0; i < pathList.length; i++) {
            int x = pathList[i][0];
            int y = pathList[i][1];


            if (i + 1 < pathList.length) {
                int nextX = pathList[i + 1][0];
                int nextY = pathList[i + 1][1];

                addObject(new Path(nextX, nextY, pathWidth), x, y);
                //System.out.println("meow" +x +y);
            } else {
                addObject(new Path(0, 0, pathWidth), x, y);
            }

        }
    }

    /**
     * @return the current wave.
     */
    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    /**
     * Spawns a given wave.
     *
     * @param wave       the Enemies to spawn.
     * @param spawnDelay the delay between enemies.
     */
    public void spawnWave(int wave, int spawnDelay) {
        if (enemiesToSpawn.isEmpty() && aliveEnemies.isEmpty()) { //spawns new wave
            setWave(wave + 1);
            System.out.println("New Wave: " + getWave());

            enemiesToSpawn = waveManager.generateWave(wave);

            getPlayer().setCoins(getPlayer().getCoins() + waveEndMoney);

            waveEndMoney = 0;
            receivedWaveMoney = 0;

            for (Enemy enemy : enemiesToSpawn) {
                waveEndMoney += (int) enemy.getLives();
            }
            waveEndMoney *= getWave();

            gameSaveManager.saveGame(); //so when you quit it continues on the last wave
        }

        if (spawnDelayCounter < spawnDelay) { //so they don't all spawn on 1 tick
            spawnDelayCounter++;
            return;
        }
        spawnDelayCounter = 0;

        if (enemiesToSpawn.isEmpty()) { //just incase
            return;
        }
        Enemy enemy = enemiesToSpawn.get(0);
        addObject(enemy, getSpawnLocation()[0], getSpawnLocation()[1]);
        aliveEnemies.add(enemy);
        enemiesToSpawn.remove(enemy);
    }

    /**
     * removes dead enemies from the "aliveEnemies" list.
     */
    public void removeDeadEnemies() {
        List<Enemy> deadEnemies = new ArrayList<>();
        for (Enemy enemy : aliveEnemies) {
            if (!(enemy.getWorld() == null || enemy.getLives() < 0)) {
                return;
            }
            deadEnemies.add(enemy);
            receivedWaveMoney += enemy.getInitialLives();
        }
        aliveEnemies.removeAll(deadEnemies);
    }

    /**
     * restarts the wave.
     */
    public void resetWave() {
        aliveEnemies.clear();
        enemiesToSpawn.clear();
        setWave(getWave() - 1); //so the new wave is the old wave
        waveEndMoney = 0; //so it does not give money
        getPlayer().setCoins(getPlayer().getCoins() - receivedWaveMoney); //so you cant dupe coins

        for (Projectile p : getObjects(Projectile.class)) {
            removeObject(p);
        }
    }

    public void act() {
        if (isMultiplayer) {
            readNetworkData();
        }

        lastKeyPressed = Greenfoot.getKey(); //so it updates exactly once per frame
        checkPaused();
        if (isPaused()) {
            return;
        }
        if (!enemiesToSpawn.isEmpty() || aliveEnemies.isEmpty()) {
            spawnWave(getWave(), spawnDelay);
        }
        removeDeadEnemies();
        showWave();
    }

    /**
     * displays the current wave on the screen
     */
    public void showWave() {
        if (oldWave != wave) {
            showText("Wave: " + getWave(), 1540, 40);
            oldWave = wave;
        }
    }


    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isForcedPause() {
        return isForcedPause;
    }

    public void setForcedPause(boolean paused) {
        this.isForcedPause = paused;
    }


    // <--! PAUSING LOGIC !-->

    public void checkPaused() {
        if (isForcedPause) {
            return;
        }

        if ("escape".equals(lastKeyPressed)) {
            System.out.println("popup");
            setPaused(!isPaused());
            //System.out.printf("isPaused: %s\n", isPaused);
            pauseObjects();
            List<PauseMenu> pauseMenus = getObjects(PauseMenu.class);
            List<SettingsPopup> settingsPopups = getObjects(SettingsPopup.class);
            if (pauseMenus.isEmpty()) {
                addObject(new PauseMenu(), getWidth() / 2, getHeight() / 2);
            } else {
                for (PauseMenu pauseMenu : pauseMenus) {
                    pauseMenu.onRemove();
                }
            }
            if (!settingsPopups.isEmpty()) {
                for (SettingsPopup settingsPopup : settingsPopups) {
                    settingsPopup.onRemove();
                }
            }


        } else if ("space".equals(lastKeyPressed)) {
            setPaused(!isPaused());
            pauseObjects();
        }

    }


    public void pauseObjects() {
        List<MainClass> objs = getObjects(MainClass.class);
        for (int i = 0; i < objs.size(); i++) {
            objs.get(i).setPaused(isPaused());
        }
    }

    public void pauseObjects(boolean isPaused) {
        this.isPaused = isPaused;
        pauseObjects();
    }

    public void onContinue() {
        setPaused(false);
        pauseObjects();
    }

    // <--! MULTIPLAYER !-->

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public void readNetworkData() {
        ConcurrentLinkedQueue<String> queue = NetworkManager.getInstance().getInboundQueue();

        while (!queue.isEmpty()) {
            String msg = queue.poll();
            processCommand(msg);
        }
    }

    public void processCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return;
        }

        System.out.println("Porcessing incoming command: " + command);

        String[] tokens = command.split(",");
        String action = tokens[0]; // Format: <Command>, x,y,z, whatever //example: SPAWN:<Tower>, "x", "y"

        if (action.startsWith("SPAWN:")) {
            String towerType = action.substring(6); //so the "SPAWN:" is stripped
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);

            spawnTowerFromNetwork(towerType, x, y);
        } else if (action.equals("DAMAGE_ENEMY")) {
            String enemyId = tokens[1];
            int damage = Integer.parseInt(tokens[2]);

            damageEnemyFromNetwork(enemyId, damage);
        } else if (action.equals("DAMAGE_PLAYER")) {
            int damage = Integer.parseInt(tokens[1]);
            getPlayer().damage(damage);
        }
    }

    public void spawnTowerFromNetwork(String towerType, int x, int y) {
        HashMap<String, Supplier<Tower>> possibleTowers = gameSaveManager.getTowerList();

        Supplier<Tower> towerSupplier = possibleTowers.get(towerType);
        if (towerSupplier != null) {
            Tower towerToPlace = towerSupplier.get();

            //no need to upgrade bc spawned towers are always 0 0 0
            towerToPlace.setPlacing(false);
            addObject(towerToPlace, x, y);

        } else if (!Objects.equals(towerType, "Helicopter")) {
            throw new RuntimeException(towerType + " could not be spawned. Please add to HashMap (if you created a new tower) or contact @Mati (you still do that)");
        }

    }

    public void damageEnemyFromNetwork(String enemyId, int damage) {
        for (Enemy e : getObjects(Enemy.class)) {
            if (e.getUniqueId().equals(enemyId)) {
                e.damage(damage);
                break;
            }
        }
    }

    /**
     * @param coins the new value, not the difference.
     */
    public void updateFromNetworkCoins(int coins) {
        player.setCoins(coins);
    }


}

