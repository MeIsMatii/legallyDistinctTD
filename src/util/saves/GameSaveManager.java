package util.saves;

import entities.tower.*;
import greenfoot.Actor;
import map.levels.GameMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * @author matii
 * @author colin
 * @version hopefully a functional one
 */
public class GameSaveManager extends Actor implements Saveable {

    /**
     * Hashmap that stores the TowerData
     */
    private final static Map<String, Supplier<Tower>> TOWER_LIST = new HashMap<>() {{
        put("TestTower", TestTower::new);
        put("HomingTower", HomingTower::new);
        put("Rocketlauncher", Rocketlauncher::new);
        put("Sniper", Sniper::new);
        put("TrapTower", TrapTower::new);
        put("Flamethrower", Flamethrower::new);
        put("Helicopter", null); //we do not want to spawn the heli, bc the pad spawns it
        put("HelicopterPad", HelicopterPad::new);
    }};

    // path to the save file — stored in a "saves" folder next to the project
    private String Map;
    private String savePath = "saves/savedgames/" + "testFile"; //bc it immediately creates one before i can set the wave number
    // only instance of this class
    private GameSaveManager instance = null;
    // holds all key=value data loaded from the file
    private Properties saveData;

    /**
     * Private constructor — use getInstance() instead.
     */
    public GameSaveManager() {
        setImage("invisible.png");
        createSaveFile();
        saveData = loadSave(savePath);
    }

    public String getMapNr() {
        return Map;
    }

    public void setMapNr(String map) {
        Map = map;
        savePath = "saves/savedgames/" + Map + ".save";
        createSaveFile();
    }

    public static Map<String, Supplier<Tower>> getTowerList() {
        return TOWER_LIST;
    }

    public void createSaveFile() {
        try {
            File file = new File(savePath);
            file.getParentFile().mkdirs(); //idk what this does @colin
            boolean isNewFile = file.createNewFile(); // true only if it didn't exist before
            System.out.println("created: " + savePath);

            if (isNewFile) {
                Path path = Paths.get(savePath);
                // Files.writeString(path, "");
            }
        } catch (IOException e) {
            System.out.println("Could not create save file: " + e.getMessage());
        }
    }

    public GameSaveManager getInstance() {
        if (instance == null) {
            instance = new GameSaveManager(); // create once
        }
        return instance; // return the existing one every time after
    }

    /// rereads the svae file call if something changed
    public void reload() {
        saveData = loadSave(savePath); // reload from disk
    }


    /**
     * Returns last round 1.
     */
    public int getLastRound() {
        return getInt(saveData, "lastRound", 1);
    }

    /**
     * Saves last Round.
     */
    public void setLastRound(int RoundNumber) {
        saveData.setProperty("lastRound", String.valueOf(RoundNumber));
        saveValue(savePath, "lastRound", RoundNumber);
    }

    public int getCoins() {
        return getInt(saveData, "Coins", 100);
    }

    /**
     * Saves coins.
     */
    public void setCoins(int Coins) {
        saveData.setProperty("Coins", String.valueOf(Coins));
        saveValue(savePath, "Coins", Coins);
    }

    /**
     * Saves any custom key=value pair.
     * Works with int, double, boolean, String
     * <p>
     * Example:
     * SaveManager.getInstance().set("highScore", 9999);
     * SaveManager.getInstance().set("endBossDefeated", true);
     */
    public void set(String key, Object value) {
        saveData.setProperty(key, String.valueOf(value)); // update in memory
        saveValue(savePath, key, value);                 // write to disk
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


    /**
     * saves the current game to a savefile.
     */
    public void saveGame() {
        GameMap gameMap = (GameMap) getWorld();
        set("currentWave", gameMap.getWave() - 1); //bc the wave immediately advances bc all enemies are dead
        set("Towers", saveTowerData(gameMap));
        set("coins", gameMap.getPlayer().getCoins() - gameMap.getReceivedWaveMoney()); //to avoid an exploit where you can save and keep your earned money
        set("health", gameMap.getPlayer().getHealth());
    }

    /**
     * saves the Tower data.
     *
     * @param gameMap the map where the Towers are located.
     * @return the string to be stored inside the savefile.
     */
    public String saveTowerData(GameMap gameMap) {
        List<Tower> towers = gameMap.getObjects(Tower.class);
        StringBuilder data = new StringBuilder();
        for (Tower tower : towers) {
            if (tower.isPlacing()) { //we only wanna save placed towers
                gameMap.getPlayer().setCoins(gameMap.getPlayer().getCoins() + tower.getPrice());
                continue;
            }
            data.append(tower.getTowerName()).append(",")
                .append(tower.getX()).append(",")
                .append(tower.getY()).append(",")
                .append(tower.getUpgrade1()).append(",")
                .append(tower.getUpgrade2()).append(",")
                .append(tower.getUpgrade3()).append(";");
        }
        return data.toString();
    }

    /**
     * loads a game from a savefile.
     *
     * @param gameMap the map for the savedata to be loaded to.
     */
    public void loadGame(GameMap gameMap) {
        System.out.println(savePath);
        gameMap.setWave(Integer.parseInt(get("currentWave")));
        gameMap.getPlayer().setCoins(Integer.parseInt(get("coins")));
        gameMap.getPlayer().setHealth(Integer.parseInt(get("health")));
        loadTowerData();


    }

    /**
     * Loads the Tower data.
     */
    public void loadTowerData() {
        String towers = get("Towers");
        String[] entries = towers.split(";");

        for (String entry : entries) {
            if (entry.isBlank()) { //no towers
                return;
            }
            String[] data = entry.split(",");
            String towerType = data[0];
            int x = Integer.parseInt(data[1]);
            int y = Integer.parseInt(data[2]);
            int u1 = Integer.parseInt(data[3]);
            int u2 = Integer.parseInt(data[4]);
            int u3 = Integer.parseInt(data[5]);

            Supplier<Tower> towerSupplier = TOWER_LIST.get(towerType);
            if (towerSupplier != null) {
                Tower towerToPlace = towerSupplier.get();
                towerToPlace.setUpgrade1(u1);
                towerToPlace.setUpgrade2(u2);
                towerToPlace.setUpgrade3(u3);

                towerToPlace.setPlacing(false);
                getWorld().addObject(towerToPlace, x, y);

            } else if (!Objects.equals(towerType, "Helicopter")) {
                throw new RuntimeException(towerType + " could not be spawned. Please add to HashMap (if you created a new tower) or contact @Mati (you still do that)");
            }
        }
    }

    /**
     * Checks whether a saveFile exists.
     *
     * @param saveFile the path/file to check.
     * @return whether it exists.
     */
    public boolean saveFileExists(String saveFile) {
        File file = new File("saves/savedgames/" + saveFile);
        return file.exists();
    }

    /**
     * deletes a saveFile
     */
    public void removeSaveFile() {
        File file = new File(savePath);
        if (file.delete()) {
            System.out.println("Successfully deleted " + savePath);
        }
    }
}
