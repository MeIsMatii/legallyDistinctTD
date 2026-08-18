package maps.levels;

import core.MainClass;
import core.Player;
import entities.Entity;
import entities.base.Enemy;
import entities.base.Projectile;
import entities.base.Tower;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import maps.levels.util.Path;
import maps.levels.util.WaveManager;
import maps.levels.util.antiCheat.AntiCheat;
import maps.menu.PauseMenu;
import maps.util.CustomWorld;
import ui.common.BackButton;
import ui.hud.QuestionPopup;
import ui.hud.buttons.*;
import ui.hud.towerSelector.TowerSelectorSpawner;
import ui.hud.upgrades.UpgradeMenu;
import ui.settings.SettingsPopup;
import ui.settings.sound.SongButton;
import ui.settings.sound.SongDropDown;
import ui.settings.sound.VolumeSlider;
import util.Cursor;
import util.HasSound;
import util.multiplayer.NetworkManager;
import util.saves.GameSaveManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

/**
 * @author paths: Julian
 * @author waves & gamesaves: Mati
 * * @author Jannis
 */
public abstract class GameMap extends CustomWorld implements HasSound {
    private final Player player;
    private final Cursor cursor;
    private final int pathWidth;
    private final GameSaveManager gameSaveManager;
    private final WaveManager waveManager;
    private final int spawnDelay;
    private final List<Enemy> aliveEnemies = new ArrayList<>();
    private Difficulty difficulty;
    private boolean isMultiplayer;
    private boolean hasGameStarted;
    private int dotCounter = 0;
    private int dotTimer = 90;
    private UpgradeMenu upgradeMenu;
    private boolean isUpgradeMenuVisible;
    private int[] spawnLocation;
    private List<Enemy> enemiesToSpawn = new ArrayList<>();
    private int spawnDelayCounter = 0;
    private int waveEndMoney;
    private int receivedWaveMoney = 0;
    private int wave = 0;
    private int oldWave = 0;

    private boolean isPaused;
    private boolean isForcedPause;

    private String lastKeyPressed;
    private boolean isFreeplay = false;

    public GameMap() {

        super();
        GreenfootImage map = new GreenfootImage("Maps/Map" + getMapNumber() + ".png");
        map.scale(1620, 1080);
        setBackground(map);


        setPaintOrder(RetryButton.class, MuteButton.class, SongButton.class, WaveResetButton.class, SongDropDown.class, VolumeSlider.class, SettingsPopup.class, SettingsButton.class, BackButton.class, PlayOnButton.class, PauseMenu.class); //TODO better paintorder

        this.isMultiplayer = false;
        setHasGameStarted(true);

        this.gameSaveManager = new GameSaveManager();
        this.waveManager = WaveManager.getInstance();
        this.spawnDelay = 45;

        gameSaveManager.setMapNr("map" + getMapNumber());
        addObject(gameSaveManager, 0, 0);

        this.pathWidth = 120;
        player = new Player(100, 100);
        cursor = new Cursor();

        isPaused = false;
        isForcedPause = false;

        lastKeyPressed = Greenfoot.getKey();

        addHud();
    }

    public GameMap(boolean isMultiplayer, boolean isHost) {
        this();
        if (!isMultiplayer) {
            System.out.println("Singleplayer");
            return;
        }

        System.out.println("Multiplayer");
        setMultiplayer(true);
        NetworkManager.getInstance().setMapNr(getMapNumber()); //doesnt hurt incase the client also knows the mapnr lmao
        NetworkManager.getInstance().setDifficulty(difficulty);
        if (isHost) {
            startHost();//so the multiplayer session only starts when a map is connected
        }
        pauseObjects(true, true); //so nothing moves while client not connected
    }

    public void startHost() {
        NetworkManager.getInstance().startHost(7777);
    }

    /**
     * adds hud elements to the screen.
     */
    public void addHud() {
        upgradeMenu = null;
        addObject(player, 0, 0);

        addObject(cursor, 0, 0);

        addObject(new TowerSelectorSpawner(), 1741, 540);
    }

    /**
     * @return the current map number.
     */
    public abstract int getMapNumber();

    public void setUpgradeMenuVisibility(boolean isVisible, Tower tower) {
        isUpgradeMenuVisible = isVisible;
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
            System.err.println("No spawnlocation. Please fix.");
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

                if (isPathValid(x, y)) {
                    addObject(new Path(nextX, nextY, pathWidth), x, y);
                } else {
                    System.out.println("Invalid path at:" + x + " | " + y);
                }
                //System.out.println("meow" +x +y);
            } else {
                addObject(new Path(0, 0, pathWidth), x, y);
            }

        }
    }

    public boolean isPathValid(int x, int y) {
        return x < getWidth() && y < getHeight();
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

            if (isMultiplayer()) {// you alr know it, host and multiplayer
                String msg = "SET_WAVE" + "," + getWave();
                NetworkManager.getInstance().sendData(msg);
            }

            waveEndMoney = 0;
            receivedWaveMoney = 0;
            //nerv in later rounds
            for (Enemy enemy : enemiesToSpawn) {
                waveEndMoney += (int) enemy.getLives() / 2;
            }
            double divisor = (wave < 6) ? 1.0 : (wave < 13) ? 1.5 : (wave < 20) ? 2.0 : (wave < 26) ? 2.5 : (wave < 31) ? 3.0 : (wave < 41) ? 4.0 : (wave < 51) ? 5.0 : (wave < 61) ? 6.0 : 8.0;
            waveEndMoney = (int) (waveEndMoney * (wave / divisor));


            gameSaveManager.saveGame(); //so when you quit it continues on the last wave
        }

        int delay = (spawnDelay - (getWave() / 2));
        if (delay < 0) {
            delay = 0;
        }


        if (spawnDelayCounter < delay) { //so they don't all spawn on 1 tick
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
        if (isMultiplayer && NetworkManager.getInstance().isHost()) {
            String msg = "SPAWN_ENEMY" + "," + enemy.getName() + "," + enemy.getUniqueId();
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
            receivedWaveMoney += enemy.getInitialLives() * 2;
        }
        aliveEnemies.removeAll(deadEnemies);
    }

    /**
     * restarts the wave.
     */
    public void resetWave() {
        if (!NetworkManager.getInstance().isHost()) {
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
        for (Enemy e : getObjects(Enemy.class)) {
            removeObject(e);
        }
    }

    public void act() {
        super.act();
        AntiCheat.update(this);
        Wavecheat();
        NetworkManager nm = NetworkManager.getInstance();
        if (isMultiplayer) {
            if (nm.isDisconnected()) { //disconnected incase restart on connection loss
                System.out.println("disconnected@Map");
                if (hasGameStarted) {
                    pauseObjects(true, true);
                    BackButton backButton = new BackButton();
                    nm.setConnected(false);
                    nm.setDisconnected(false); // to make it stop
                    if (nm.isHost()) {
                        QuestionPopup questionPopup = new QuestionPopup("You were disconnected.\nWould you like to start a new session?", backButton, new RestartMultiplayerButton());
                        removeObject(questionPopup.getCloseButton());
                        addObject(questionPopup, getWidth() / 2, getHeight() / 2);

                    } else {
                        addObject(new QuestionPopup("You were disconnected.\nReturn to title?", backButton, null), getWidth() / 2, getHeight() / 2);
                    }
                } else {
                    nm.setDisconnected(false);
                    nm.setConnected(false);
                    NetworkManager.getInstance().startHost(7777);
                }
            } else if (nm.isConnected() && !hasGameStarted) {
                hasGameStarted = true;
                showText("", getWidth() / 2, getHeight() / 2);
                onContinue();
            } else if (!nm.isConnected() && !hasGameStarted) {
                dotTimer++;
                if (dotTimer < 23) {
                    return;
                }
                dotTimer = 0;

                dotCounter++;
                if (dotCounter > 3) {
                    dotCounter = 1;
                }
                String dotAmount = ".".repeat(dotCounter);
                showText("Waiting for second Player to join" + dotAmount, getWidth() / 2, getHeight() / 2);
            }
            readNetworkData();
        }

        lastKeyPressed = Greenfoot.getKey(); //so it updates exactly once per frame

        if (Greenfoot.isKeyDown("SHIFT") && Greenfoot.isKeyDown("PAGE UP")) {
            setWave(getWinWave());
            enemiesToSpawn.clear();
            aliveEnemies.clear();

            for (Enemy e : getObjects(Enemy.class)) {
                removeObject(e);
            }
        }
        if (Greenfoot.isKeyDown("SHIFT") && Greenfoot.isKeyDown("PAGE DOWN")) {
            player.damage(1232131111);
        }
        checkPaused();

        if (nm.isHost() && !isPaused && !(getSpawnLocation() == null)) { //you only have the ability to spawnwaves when: It is singleplayer or u are the host  and its not paused and paths are defined
            if ((!enemiesToSpawn.isEmpty() || aliveEnemies.isEmpty())) {
                spawnWave(getWave(), spawnDelay);
            }
            removeDeadEnemies();

        }
        showWave();
    }

    /**
     * displays the current wave on the screen
     */
    public void showWave() {
        if (oldWave != wave) {
            //showText("Wave: " + getWave(), 1540, 40);
            if (!isFreeplay) {
                showText("Wave: " + getWave() + " / " + getWinWave(), 1500, 40);
                if (getWave() > getWinWave()) {
                    showText("Wave: " + getWave() + " / " + "inf", 1500, 40);

                    QuestionPopup questionPopup = new QuestionPopup("You won!\nWould you like to continue in Freeplay\nor return to title", new BackButton(), new PlayOnButton());
                    removeObject(questionPopup.getCloseButton());
                    questionPopup.setCloseButton(null);
                    addObject(questionPopup, getWidth() / 2, getHeight() / 2);
                    pauseObjects(true, true);
                    playSound("winSound.mp3");
                    isFreeplay = true;
                }
            } else {
                showText("Wave: " + getWave() + " / " + "inf", 1500, 40);
            }
            oldWave = wave;
        }
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        getGameSaveManager().saveGame();
        if (NetworkManager.getInstance().isMultiplayer() && NetworkManager.getInstance().isHost()) {
            NetworkManager.getInstance().setDifficulty(difficulty);
        }
    }

    public int getWinWave() {
        if (difficulty == null) {
            return 40;
        }
        return difficulty.getWinWave();
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
            if (pauseMenus.isEmpty() && settingsPopups.isEmpty()) {
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


    // <--! PAUSING LOGIC !-->

    public void pauseObjects() {
        List<MainClass> objs = getObjects(MainClass.class);
        for (MainClass obj : objs) {
            obj.setPaused(isPaused());
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

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    // <--! MULTIPLAYER !-->

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
        setHasGameStarted(!multiplayer);
    }

    public void setHasGameStarted(boolean hasGameStarted) {
        this.hasGameStarted = hasGameStarted;
        pauseObjects(!hasGameStarted, !hasGameStarted);
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

                upgradeTowerFromNetwork(uniqueId, path, level);
                break;
            }
            case "SPAWN_ENEMY": {
                String enemyType = tokens[1];
                String enemyId = tokens[2];
                spawnEnemyFromNetwork(enemyType, enemyId);
                break;
            }
            case "DAMAGE_ENEMY": {
                String enemyId = tokens[1];
                double damage = Double.parseDouble(tokens[2]);
                damageEnemyFromNetwork(enemyId, damage);
                break;
            }
            case "SPAWN_PROJECTILE": {
                String projectileType = tokens[1];
                String projectileId = tokens[2];
                String ownerId = tokens[3];
                spawnProjectileFromNetwork(projectileType, projectileId, ownerId);
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
            case "SET_TARGETED_ENEMY": {
                String towerID = tokens[1];
                String enemyID = tokens[2];
                targetEnemyFromNetwork(towerID, enemyID);
                break;
            }
            case "MOVE_ENTITY": {
                String entityID = tokens[1];
                int x = Integer.parseInt(tokens[2]);
                int y = Integer.parseInt(tokens[3]);
                moveEntityFromNetwork(entityID, x, y);
                break;
            }
            case "REMOVE_ENTITY": {
                String entityID = tokens[1];
                removeEntityFromNetwork(entityID);
                break;
            }


        }
    }

    public void spawnTowerFromNetwork(String towerType, String uuid, int x, int y) {
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
        for (Tower t : getObjects(Tower.class)) {
            if (t.getUniqueId().equals(uuid)) {
                switch (upgradePath) {
                    case 1:
                        t.upgrade1(true);
                        break;
                    case 2:
                        t.upgrade2(true);
                        break;
                    case 3:
                        t.upgrade3(true);
                        break;
                }
                break;
            }
        }
    }

    public void spawnEnemyFromNetwork(String enemyType, String enemyId) {
        Map<String, Supplier<Enemy>> possibleEnemies = WaveManager.getEnemyList();
        Supplier<Enemy> enemySupplier = possibleEnemies.get(enemyType);
        if (enemySupplier == null) {
            System.out.println("invalid enemy");
            return;
        }
        Enemy enemyToSpawn = enemySupplier.get();

        System.out.println(enemyToSpawn);

        enemyToSpawn.setUniqueId(enemyId); //to sync UUID between players
        addObject(enemyToSpawn, spawnLocation[0], spawnLocation[1]);


    }

    public void spawnProjectileFromNetwork(String targetId, String projectileId, String towerOwnerId) {
        Tower owner = null;
        for (Tower t : getObjects(Tower.class)) {
            if (t.getUniqueId().equals(towerOwnerId)) {
                owner = t;
            }
        }
        if (owner == null) {
            return;
        }

        Enemy target = null;
        for (Enemy e : getObjects(Enemy.class)) {
            if (e.getUniqueId().equals(targetId)) {
                target = e;
            }
        }
        if (target == null) {
            return;
        }

        owner.shoot(target, projectileId);

    }

    public void damageEnemyFromNetwork(String enemyId, double damage) {
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
        player.setCoins(coins, true);
    }

    public void setWaveFromNetwork(int wave) {
        this.wave = wave; //func not needed but now its uniform
    }

    public void targetEnemyFromNetwork(String towerUUID, String enemyUUID) {
        Enemy enemyToTarget = null;
        for (Enemy e : getObjects(Enemy.class)) {
            if (e.getUniqueId().equals(enemyUUID)) {
                enemyToTarget = e;
                break;
            }
        }
        for (Tower t : getObjects(Tower.class)) {
            if (t.getUniqueId().equals(towerUUID)) {
                t.setTargetedEnemyManual(enemyToTarget);
                break;
            }
        }
    }

    public void Wavecheat() {
        if (Greenfoot.isKeyDown("P") && Greenfoot.isKeyDown("Shift")) {
            wave = wave + 1;
        }
    }

    public void moveEntityFromNetwork(String uuid, int x, int y) {
        for (Entity e : getObjects(Entity.class)) {
            if (e.getUniqueId().equals(uuid)) {
                e.setLocation(x, y, true);
                break;
            }
        }
    }

    public void removeEntityFromNetwork(String uuid) {
        for (Entity e : getObjects(Entity.class)) {
            if (e.getUniqueId().equals(uuid)) {
                removeObject(e);
                break;
            }
        }
    }

    public enum Difficulty {
        EASY(40),
        MEDIUM(60),
        HARD(80);

        private final int winWave;

        Difficulty(int winWave) {
            this.winWave = winWave;
        }

        public int getWinWave() {
            return winWave;
        }
    }


}

