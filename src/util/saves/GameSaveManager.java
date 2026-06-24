package util.saves;

import entities.tower.*;
import greenfoot.Actor;
import map.levels.Map;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.io.File;


public class GameSaveManager extends Actor implements Saveable {

    // path to the save file — stored in a "saves" folder next to the project
    private String Map;
    private String SAVE_PATH = "saves/savedgames/" + "testFile";
    public String getMapNr() {
        return Map;
    }
    public void setMapNr(String map) {
        Map = map;
        SAVE_PATH = "saves/savedgames/" + Map + ".save";
    }
    // only instance of this class
    private GameSaveManager instance = null;

    // holds all key=value data loaded from the file
    private Properties saveData;

    /** Private constructor — use getInstance() instead. */
    public GameSaveManager() {
        setImage("invisible.png");
        try {
            File file = new File(SAVE_PATH);
            file.getParentFile().mkdirs();
            boolean isNewFile = file.createNewFile(); // true only if it didn't exist before

            if (isNewFile) {
                Path path = Paths.get(SAVE_PATH);
               // Files.writeString(path, "");
            }
        } catch (IOException e) {
            System.out.println("Could not create save file: " + e.getMessage());
        }
        saveData = loadSave(SAVE_PATH);
    }

    public GameSaveManager getInstance() {
        if (instance == null) {
            instance = new GameSaveManager(); // create once
        }
        return instance; // return the existing one every time after
    }

    /// rereads the svae file call if something changed
    public void reload() {
        saveData = loadSave(SAVE_PATH); // reload from disk
    }



    /** Returns last round 1. */
    public int getLastRound() {
        return getInt(saveData, "lastRound", 1);
    }

    /** Saves last Round. */
    public void setLastRound(int RoundNumber) {
        saveData.setProperty("lastRound", String.valueOf(RoundNumber));
        saveValue(SAVE_PATH, "lastRound", RoundNumber);
    }
    public int getCoins() {
        return getInt(saveData, "Coins", 100);
    }

    /** Saves coins. */
    public void setCoins(int Coins) {
        saveData.setProperty("Coins", String.valueOf(Coins));
        saveValue(SAVE_PATH, "Coins", Coins);
    }

    /**
     * Saves any custom key=value pair.
     * Works with int, double, boolean, String
     *
     * Example:
     *   SaveManager.getInstance().set("highScore", 9999);
     *   SaveManager.getInstance().set("endBossDefeated", true);
     */
    public void set(String key, Object value) {
        saveData.setProperty(key, String.valueOf(value)); // update in memory
        saveValue(SAVE_PATH, key, value);                 // write to disk
    }

    /**
     * Gets any custom key as a String.
     * Returns null if the key doesn't exist.
     */
    public String get(String key) {
        return getValue(saveData, key);
    }

    /**
     * Gets any custom key as an int.
     *
     * @param key          the key to look up
     * @param defaultValue returned if the key is missing
     */
    public int getAsInt(String key, int defaultValue) {
        return getInt(saveData, key, defaultValue);
    }

    /**
     * Gets any custom key as a double.
     *
     * @param key          the key to look up
     * @param defaultValue returned if the key is missing
     */
    public double getAsDouble(String key, double defaultValue) {
        return getDouble(saveData, key, defaultValue);
    }

    /**
     * Gets any custom key as a boolean.
     *
     * @param key          the key to look up
     * @param defaultValue returned if the key is missing
     */
    public boolean getAsBoolean(String key, boolean defaultValue) {
        return getBoolean(saveData, key, defaultValue);
    }



    public void saveGame() {
        Map map = (Map) getWorld();
        set("currentWave", map.getWave() -1); //bc the wave immediately advances bc all enemies are dead
        set("Towers", saveTowerData(map));
        set("coins", map.getPLAYER().getCoins() - map.getReceivedWaveMoney()); //to avoid an exploit where you can save and keep your earned money
        set("health", map.getPLAYER().getHealth());
    }

    public String saveTowerData(Map map) {
        List<Tower> towers = map.getObjects(Tower.class);
        StringBuilder data = new StringBuilder();
        for(Tower tower: towers) {
            if(tower.isPlacing()) { //we only wanna save placed towers
                map.getPLAYER().setCoins(map.getPLAYER().getCoins() + tower.getPRICE());
                continue;
            }
            data.append(tower.getTowerName()).append(",")
                .append(tower.getX()).append(",")
                .append(tower.getY()).append(",")
                .append(tower.getUpgrade1()).append(",")
                .append(tower.getUpgrade2()).append(",")
                .append(tower.getUpgrade3()).append("\n");
        }
        return data.toString();
    }

    public void loadGame(Map map) {
        System.out.println(SAVE_PATH);
        map.setWave(Integer.parseInt(get("currentWave")));
        map.getPLAYER().setCoins(Integer.parseInt(get("coins")));
        map.getPLAYER().setHealth(Integer.parseInt(get("health")));
        loadTowerData();


    }

    public void loadTowerData() {
        String towers = get("Towers");
        String[] entries = towers.split("\n");

        for (String entry: entries) {
            if(entry.isBlank()) { //no towers
                return;
            }
            String[] data = entry.split(",");
            String towerType = data[0];
            int x = Integer.parseInt(data[1]);
            int y = Integer.parseInt(data[2]);
            int u1 = Integer.parseInt(data[3]);
            int u2 = Integer.parseInt(data[4]);
            int u3 = Integer.parseInt(data[5]);

            Tower towerToPlace = null;
            switch(towerType) {
                //all towers need to go here
                case "TestTower": towerToPlace = new TestTower(); break;
                case "HomingTower": towerToPlace = new HomingTower(); break;
                case "Rocketlauncher": towerToPlace = new Rocketlauncher(); break;
                case "Sniper": towerToPlace = new Sniper(); break;
                case "TrapTower": towerToPlace = new TrapTower(); break;
                case "Flamethrower": towerToPlace = new Flamethrower(); break;
                case "Helicopter": break; //we do not want to spawn the heli, bc the pad spawns it
                case "HelicopterPad": towerToPlace = new HelicopterPad(); break;

                default:
                    System.out.println("tower not in list @GameSaveManager.loadTowerData()\n Please fix it or contact @Mathilo");
                    break;
            }

            if (towerToPlace != null) {
                towerToPlace.setUpgrade1(u1);
                towerToPlace.setUpgrade2(u2);
                towerToPlace.setUpgrade3(u3);

                towerToPlace.setPlacing(false);
                getWorld().addObject(towerToPlace, x, y);

            }
        }
    }
}
