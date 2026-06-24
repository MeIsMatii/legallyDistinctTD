package map.levels;

import core.Player;
import entities.Hitbox;
import entities.enemy.Enemy;
import entities.tower.Tower;
import entities.tower.util.RangeDisplay;
import greenfoot.World;
import map.levels.util.Path;
import ui.hud.TowerSelectorSpawner;
import ui.hud.UpgradeMenu;
import util.Cursor;
import util.WaveManager;
import util.saves.GameSaveManager;

import java.util.ArrayList;
import java.util.List;

public abstract class Map extends World {
    private UpgradeMenu UPGRADEMENU;
    private boolean isUpgradeMenuVisible;

    private final Player PLAYER;
    private final Cursor CURSOR;
    private final int PATHWIDTH;
    private int[] SPAWNLOCATION;


    private final GameSaveManager GAMESAVEMANAGER;
    private final WaveManager WAVEMANAGER;
    private int SPAWNDELAY;

    private List<Enemy> enemiesToSpawn = new ArrayList<>();
    private List<Enemy> aliveEnemies = new ArrayList<>();
    private int spawnDelayCounter = 0;
    private int waveEndMoney;
    private int receivedWaveMoney;


    private int wave = 1;

    public Map() {
        super(1920, 1080, 1);
        this.GAMESAVEMANAGER = new GameSaveManager();
        this.WAVEMANAGER = WaveManager.getInstance();
        this.SPAWNDELAY = 45;

        addObject(GAMESAVEMANAGER, 0,0);

        setPaintOrder(Hitbox.class, Tower.class, RangeDisplay.class); //Tower infront of it's range

        this.PATHWIDTH = 120;
        PLAYER = new Player(100, 100); //jannis ganz alleine gemacht
        CURSOR = new Cursor();

        addHud();
    }

    public void addHud() {
        UPGRADEMENU = null;
        addObject(PLAYER, 0, 0);

        addObject(CURSOR, 0, 0);

        addObject(new TowerSelectorSpawner(), 1770, 540);
    }

    public void setUpgradeMenuVisibility(boolean isVisible, Tower tower) {
        isUpgradeMenuVisible = isVisible;
        //TODO add paths and delete them @Elias
        if(isVisible) {
            int width = (getWidth()-300)/2;
            if(UPGRADEMENU!=null) {
                UPGRADEMENU.delete();
            }
            UPGRADEMENU = new UpgradeMenu(tower);
            addObject(UPGRADEMENU,width, getHeight()-216/2);
        } else if(!getObjects(UpgradeMenu.class).isEmpty()) {
            UPGRADEMENU.delete();
            UPGRADEMENU = null;
        }
    }

    public UpgradeMenu getUpgradeMenu() {
        return this.UPGRADEMENU;
    }
    public boolean isUpgradeMenuVisible() {
        return isUpgradeMenuVisible;
    }

    public Player getPLAYER() {                   //jannis
        return PLAYER;
    }

    public GameSaveManager getGameSaveManager() {
        return GAMESAVEMANAGER;
    }

    public Cursor getCURSOR() {
        return CURSOR;
    }

    public int getReceivedWaveMoney() {
        return receivedWaveMoney;
    }

    public int[] getSpawnLocation() {
        if(this.SPAWNLOCATION == null) {
            throw new RuntimeException("No spawnlocation. Please fix.");
        }
        return this.SPAWNLOCATION;
    }

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

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public void spawnWave(int wave, int spawnDelay) {

        if(enemiesToSpawn.isEmpty() && aliveEnemies.isEmpty()) { //spawns new wave
            setWave(wave +1);
            System.out.println("New Wave: " + wave);

            enemiesToSpawn = WAVEMANAGER.generateWave(wave);

            getPLAYER().setCoins(getPLAYER().getCoins() + waveEndMoney);

            waveEndMoney = 0;
            receivedWaveMoney = 0;

            for(Enemy enemy : enemiesToSpawn) {
              waveEndMoney += (int) enemy.getLives();
            }
            waveEndMoney *= getWave();

            GAMESAVEMANAGER.saveGame(); //so when you quit it continues on the last wave
        }

        if(spawnDelayCounter < spawnDelay) { //so they don't all spawn on 1 tick
            spawnDelayCounter++;
            return;
        }
        spawnDelayCounter = 0;

        if(enemiesToSpawn.isEmpty()) { //just incase
            return;
        }
        Enemy enemy = enemiesToSpawn.get(0);
        addObject(enemy, SPAWNLOCATION[0], SPAWNLOCATION[1]);
        aliveEnemies.add(enemy);
        enemiesToSpawn.remove(enemy);
    }

    public void removeDeadEnemies() {
        List<Enemy> deadEnemies = new ArrayList<>();
        for(Enemy enemy : aliveEnemies) {
            if(!(enemy.getWorld() == null || enemy.getLives() < 0)) {
                return;
            }
            deadEnemies.add(enemy);
            receivedWaveMoney += enemy.getInitialLives();
        }
        aliveEnemies.removeAll(deadEnemies);
    }

    public void resetWave() {
        aliveEnemies.clear();
        enemiesToSpawn.clear();
        setWave(getWave()-1); //so the new wave is the old wave
        waveEndMoney = 0; //so it does not give money
        getPLAYER().setCoins(getPLAYER().getCoins()-receivedWaveMoney); //so you cant dupe coins
    }

    public void act() {
        if(!enemiesToSpawn.isEmpty() || aliveEnemies.isEmpty()) {
            spawnWave(getWave(), SPAWNDELAY);
        }
        removeDeadEnemies();
    }


}
