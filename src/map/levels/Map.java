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
    private final Player PLAYER;
    private final Cursor CURSOR;
    private final int PATHWIDTH;
    private final GameSaveManager GAMESAVEMANAGER;
    private final WaveManager WAVEMANAGER;
    private final int SPAWNDELAY;
    private final List<Enemy> aliveEnemies = new ArrayList<>();
    private UpgradeMenu UPGRADEMENU;
    private boolean isUpgradeMenuVisible;
    private int[] SPAWNLOCATION;
    private List<Enemy> enemiesToSpawn = new ArrayList<>();
    private int spawnDelayCounter = 0;
    private int waveEndMoney;
    private int receivedWaveMoney;

    private final boolean isMultiplayer;


    private int wave = 0;
    private int oldWave = 0;

    private boolean isPaused;
    private boolean isForcedPause;

    private String lastKeyPressed;

    public Map() {
        super(1920, 1080, 1);

        System.out.println("Singleplayer");
        this.isMultiplayer = false;

        this.GAMESAVEMANAGER = new GameSaveManager();
        this.WAVEMANAGER = WaveManager.getInstance();
        this.SPAWNDELAY = 45;

        GAMESAVEMANAGER.setMapNr("map" + getMapNumber());
        addObject(GAMESAVEMANAGER, 0, 0);

        setPaintOrder(Hitbox.class, Tower.class, RangeDisplay.class); //Tower infront of it's range

        this.PATHWIDTH = 120;
        PLAYER = new Player(100, 100); //jannis ganz alleine gemacht
        CURSOR = new Cursor();

        isPaused = false;
        isForcedPause = false;

        lastKeyPressed = Greenfoot.getKey();

        addHud();
    }

    public Map(boolean isMultiplayer) {
        super(1920, 1080, 1);

        System.out.println("Multiplayer: " + isMultiplayer);
        this.isMultiplayer = isMultiplayer;

        this.GAMESAVEMANAGER = new GameSaveManager();
        this.WAVEMANAGER = WaveManager.getInstance();
        this.SPAWNDELAY = 45;

        GAMESAVEMANAGER.setMapNr("map" + getMapNumber());
        addObject(GAMESAVEMANAGER, 0, 0);

        setPaintOrder(Hitbox.class, Tower.class, RangeDisplay.class); //Tower infront of it's range

        this.PATHWIDTH = 120;
        PLAYER = new Player(100, 100); //jannis ganz alleine gemacht
        CURSOR = new Cursor();

        isPaused = false;
        isForcedPause = false;

        lastKeyPressed = Greenfoot.getKey();

        addHud();
    }

    /**
     * adds hud elements to the screen.
     */
    public void addHud() {
        UPGRADEMENU = null;
        addObject(PLAYER, 0, 0);

        addObject(CURSOR, 0, 0);

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
            if (UPGRADEMENU != null) {
                UPGRADEMENU.delete();
            }
            UPGRADEMENU = new UpgradeMenu(tower);
            addObject(UPGRADEMENU, width, getHeight() - 216 / 2);
        } else if (!getObjects(UpgradeMenu.class).isEmpty()) {
            UPGRADEMENU.delete();
            UPGRADEMENU = null;
        }
    }

    /**
     * @return the currently active upgrade screen or null.
     */
    public UpgradeMenu getUpgradeMenu() {
        return this.UPGRADEMENU;
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
    public Player getPLAYER() {                   //jannis
        return PLAYER;
    }

    /**
     * @return the gamesavemanager.
     */
    public GameSaveManager getGameSaveManager() {
        return GAMESAVEMANAGER;
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
        if (this.SPAWNLOCATION == null) {
            throw new RuntimeException("No spawnlocation. Please fix.");
        }
        return this.SPAWNLOCATION;
    }

    /**
     * adds the paths for enemies to the map.
     *
     * @param pathList the list of corners.
     */
    public void addPath(int[][] pathList) {
        this.SPAWNLOCATION = pathList[0];
        for (int i = 0; i < pathList.length; i++) {
            int x = pathList[i][0];
            int y = pathList[i][1];


            if (i + 1 < pathList.length) {
                int nextX = pathList[i + 1][0];
                int nextY = pathList[i + 1][1];

                addObject(new Path(nextX, nextY, PATHWIDTH), x, y);
                //System.out.println("meow" +x +y);
            } else {
                addObject(new Path(0, 0, PATHWIDTH), x, y);
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

            enemiesToSpawn = WAVEMANAGER.generateWave(wave);

            getPLAYER().setCoins(getPLAYER().getCoins() + waveEndMoney);

            waveEndMoney = 0;
            receivedWaveMoney = 0;

            for (Enemy enemy : enemiesToSpawn) {
                waveEndMoney += (int) enemy.getLives();
            }
            waveEndMoney *= getWave();

            GAMESAVEMANAGER.saveGame(); //so when you quit it continues on the last wave
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
        getPLAYER().setCoins(getPLAYER().getCoins() - receivedWaveMoney); //so you cant dupe coins

        for (Projectile p : getObjects(Projectile.class)) {
            removeObject(p);
        }
    }

    public void act() {
        if(isMultiplayer) {
            readNetworkData();
        }

        lastKeyPressed = Greenfoot.getKey(); //so it updates exactly once per frame
        checkPaused();
        if (isPaused()) {
            return;
        }
        if (!enemiesToSpawn.isEmpty() || aliveEnemies.isEmpty()) {
            spawnWave(getWave(), SPAWNDELAY);
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

        while(!queue.isEmpty()) {
            String msg = queue.poll();
            processCommand(msg);
        }
    }

    public void processCommand(String command) {
        if(command == null || command.trim().isEmpty()) {
            return;
        }

        System.out.println("Porcessing incoming command: " + command);

        String[] tokens = command.split(",");
        String action = tokens[0]; // Format: <Command>, x,y,z, whatever //example: SPAWN:<Tower>, "x", "y"

        if(action.startsWith("SPAWN:")) {
            String towerType = action.substring(6); //so the "SPAWN:" is stripped
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);

            spawnTowerFromNetwork(towerType, x, y);
        } else if (action.equals("DAMAGE_ENEMY")) {
            String enemyId = tokens[1];
            int damage = Integer.parseInt(tokens[2]);

            damageEnemyFromNetwork(enemyId, damage);
        }
    }

    public void spawnTowerFromNetwork(String towerType, int x, int y) {
        HashMap<String, Supplier<Tower>> possibleTowers = GAMESAVEMANAGER.getTowerList();

        Supplier<Tower> towerSupplier = possibleTowers.get(towerType);
        if (towerSupplier != null) {
            Tower towerToPlace = towerSupplier.get();

            //no need to upgrade bc spawned towers are always 0 0 0
            towerToPlace.setPlacing(false);
            addObject(towerToPlace, x, y);

        } else if(!Objects.equals(towerType, "Helicopter")){
            throw new RuntimeException(towerType + " could not be spawned. Please add to HashMap (if you created a new tower) or contact @Mati (you still do that)");
        }

    }

    public void damageEnemyFromNetwork(String enemyId, int damage) {
        for (Enemy e: getObjects(Enemy.class)) {
            if(e.getUniqueId().equals(enemyId)) {
                e.damage(damage);
                break;
            }
        }
    }

    public void updateCoins(int coins) {

    }



}

