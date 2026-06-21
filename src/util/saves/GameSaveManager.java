package util.saves;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.io.File;

public class GameSaveManager implements Saveable {

    // path to the save file — stored in a "saves" folder next to the project
    private static String Map;
    private static String SAVE_PATH = "saves/game saves/" + Map;
    public static String getMapNr() {
        return Map;
    }
    public static void setMapNr(String map) {
        Map = map;
    }
    // only instance of this class
    private static GameSaveManager instance = null;

    // holds all key=value data loaded from the file
    private Properties saveData;

    /** Private constructor — use getInstance() instead. */
    private GameSaveManager() {
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

    public static GameSaveManager getInstance() {
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
}
