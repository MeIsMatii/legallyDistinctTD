package maps.levels;

import core.MainClass;
import core.Player;
import entities.Hitbox;
import entities.enemy.Enemy;
import entities.projectiles.Projectile;
import entities.tower.*;
import entities.tower.util.RangeDisplay;
import greenfoot.Greenfoot;
import greenfoot.World;
import maps.levels.util.GameWonPopup;
import maps.levels.util.Path;
import maps.levels.util.WaveManager;
import maps.menu.PauseMenu;
import ui.hud.towerSelector.TowerSelectorSpawner;
import ui.hud.upgrades.UpgradeMenu;
import ui.settings.SettingsPopup;
import util.Cursor;
import util.multiplayer.NetworkManager;
import util.saves.GameSaveManager;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

/**
 * @author paths: Julian
 * @author waves & gamesaves: Mati
 */
public abstract class GameMap extends World {
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
    private int wave = 39;
    private int oldWave = 0;

    private boolean isPaused;
    private boolean isForcedPause;

    private String lastKeyPressed;

    public GameMap() {
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

    public GameMap(boolean isMultiplayer) {
        super(1920, 1080, 1);

        System.out.println("Multiplayer: " + isMultiplayer);
        this.isMultiplayer = isMultiplayer;
        if (isMultiplayer && NetworkManager.getInstance().isHost()) {
            NetworkManager.getInstance().sendData("MAP:" + getMapNumber());
        }

        this.gameSaveManager = new GameSaveManager();
        this.waveManager = WaveManager.getInstance();
        this.spawnDelay = 45;

        gameSaveManager.setMapNr("maps" + getMapNumber());
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
        //should only get called when singleplayer or is host
        if (enemiesToSpawn.isEmpty() && aliveEnemies.isEmpty()) { //spawns new wave
            setWave(wave + 1);



            System.out.println("New Wave: " + getWave());

            enemiesToSpawn = waveManager.generateWave(wave);

            getPlayer().setCoins(getPlayer().getCoins() + waveEndMoney);

            if(isMultiplayer()) {// you alr know it, host and multiplayer
                String msg = "SET_WAVE" + "," + getWave();
                NetworkManager.getInstance().sendData(msg);
            }

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
        if(isMultiplayer && NetworkManager.getInstance().isHost()) {
            String msg = "SPAWN_ENEMY" + "," + enemy.getName();
            NetworkManager.getInstance().sendData(msg);
        }
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
        if(!NetworkManager.getInstance().isHost()) {
            return;
        }

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

        if(NetworkManager.getInstance().isHost() && !isPaused) { //you only have the ability to spawnwaves when: It is singleplayer or u are the host  and its not paused
            if ((!enemiesToSpawn.isEmpty() || aliveEnemies.isEmpty())) {
                spawnWave(getWave(), spawnDelay);
            }
            removeDeadEnemies();
            showWave();
        }
    }

    /**
     * displays the current wave on the screen
     */
    public void showWave() {
        if (oldWave != wave) {
            //showText("Wave: " + getWave(), 1540, 40);
            if (!isFreeplay){
                showText("Wave: " + getWave() + " / " + getWinWave(), 1540, 40);
                if (getWave() > getWinWave()){
                    showText("Wave: " + getWave() + " / " + "inf", 1540, 40);
                    addObject(new GameWonPopup(), getWidth()/2, getHeight()/2);
                    pauseObjects(true, true);
                    isFreeplay = true;
                }
            }else{
                showText("Wave: " + getWave() + " / " + "inf", 1540, 40);
            }
            oldWave = wave;
        }
    }

    enum Difficulty {
        EASY(40),
        MEDIUM(60),
        HARD(80);

        private final int winWave;
        Difficulty(int winWave) {
            this.winWave = winWave;
        }

        public int getWinWave(){
            return winWave;
        }
    }

    public int getWinWave() {
        return difficulty.getWinWave();
    }

    private final Difficulty difficulty = Difficulty.EASY;



    private boolean isFreeplay = false;




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

    public void pauseObjects(boolean isPaused, boolean isForcedPause) {
        this.isPaused = isPaused;
        this.isForcedPause = isForcedPause;
        pauseObjects();
    }

    public void onContinue() {
        setPaused(false);
        setForcedPause(false); //cant be forced if its not paused
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

        System.out.println("Processing incoming command: " + command);

        String[] tokens = command.split(",");
        String action = tokens[0]; // Format: <Command>, x,y,z, whatever //example: SPAWN, "tower", "x", "y"


        switch (action) {
            case "SPAWN_TOWER": {
                String towerType = tokens[1];
                String uniqueId = tokens[2];
                int x = Integer.parseInt(tokens[3]);
                int y = Integer.parseInt(tokens[4]);
                spawnTowerFromNetwork(towerType, uniqueId, x, y);
                break;
            }
            case "UPGRADE_TOWER": {
                String uniqueId = tokens[1];
                int path = Integer.parseInt(tokens[2]);
                int level = Integer.parseInt(tokens[3]);

                upgradeTowerFromNetwork(uniqueId,path,level);
            }
            case "SPAWN_ENEMY": {
                String enemyType = tokens[1];
                String enemyId = tokens[2];
                spawnEnemyFromNetwork(enemyType, enemyId);
            }
            case "DAMAGE_ENEMY": {
                String enemyId = tokens[1];
                int damage = Integer.parseInt(tokens[2]);
                damageEnemyFromNetwork(enemyId, damage);
                break;
            }
            case "DAMAGE_PLAYER": {
                int damage = Integer.parseInt(tokens[1]);
                getPlayer().damage(damage);
                break;
            }
            case "SET_COINS": {
                int coins = Integer.parseInt(tokens[1]);
                setCoinsFromNetwork(coins);
                break;
            }
            case "SET_WAVE": {
                int wave = Integer.parseInt(tokens[1]);
                setWaveFromNetwork(wave);
                break;
            }


        }
    }

    public void spawnTowerFromNetwork(String towerType,String uuid, int x, int y) {
        Map<String, Supplier<Tower>> possibleTowers = GameSaveManager.getTowerList();

        Supplier<Tower> towerSupplier = possibleTowers.get(towerType);
        if (towerSupplier != null) {
            Tower towerToPlace = towerSupplier.get();
            towerToPlace.setUniqueId(uuid);

            //no need to upgrade bc spawned towers are always 0 0 0
            towerToPlace.setPlacing(false);
            addObject(towerToPlace, x, y);

        } else if (!Objects.equals(towerType, "Helicopter")) {
            throw new RuntimeException(towerType + " could not be spawned. Please add to HashMap (if you created a new tower) or contact @Mati (you still do that)");
        }

    }

    public void upgradeTowerFromNetwork(String uuid, int upgradePath, int upgradeLevel) {
        for(Tower t: getObjects(Tower.class)) {
            if(t.getUniqueId().equals(uuid)) {
                switch (upgradePath) {
                    case 1: t.upgrade1(); break;
                    case 2: t.upgrade2(); break;
                    case 3: t.upgrade3(); break;
                }
                break;
            }
        }
    }

    public void spawnEnemyFromNetwork(String enemyType, String enemyId) {
        Map<String, Supplier<Enemy>> possibleEnemies = WaveManager.getEnemyList();
        Supplier<Enemy> enemySupplier = possibleEnemies.get(enemyType);
        if(enemySupplier == null) {
            System.out.println("invalid enemy");
            return;
        }
        Enemy enemyToSpawn = enemySupplier.get();

        System.out.println(enemyToSpawn);

        enemyToSpawn.setUniqueId(enemyId); //to sync UUID between players
        addObject(enemyToSpawn, spawnLocation[0], spawnLocation[1]);


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
    public void setCoinsFromNetwork(int coins) {
        player.setCoins(coins);
    }

    public void setWaveFromNetwork(int wave) {
        this.wave = wave; //func not needed but now its uniform
    }



}

