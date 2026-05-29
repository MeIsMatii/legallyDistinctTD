package util;

import java.util.Properties;

public class SaveManager implements Saveable {

    // path to the save file — stored in a "saves" folder next to the project
    private static final String SAVE_PATH = "saves/game.txt";

    // only instance of this class
    private static SaveManager instance = null;

    // holds all key=value data loaded from the file
    private Properties saveData;

    /** Private constructor — use getInstance() instead. */
    private SaveManager() {
        saveData = loadSave(SAVE_PATH); // load the file as soon as the manager is created
    }

    public static SaveManager getInstance() {
        if (instance == null) {
            instance = new SaveManager(); // create once
        }
        return instance; // return the existing one every time after
    }

   /// rereads the svae file call if something changed
    public void reload() {
        saveData = loadSave(SAVE_PATH); // reload from disk
    }



    /** Returns last map played default to 1. */
    public int getLastMap() {
        return getInt(saveData, "lastMap", 1);
    }

    /** Saves which map was last played. */
    public void setLastMap(int mapNumber) {
        saveData.setProperty("lastMap", String.valueOf(mapNumber));
        saveValue(SAVE_PATH, "lastMap", mapNumber);
    }

    /** Returns whether sound is enabled. Defaults to true. */
    public boolean isSoundOn() {
        return getBoolean(saveData, "soundEnabled", true);
    }

    /** Saves the sound on/off setting. */
    public void setSoundOn(boolean enabled) {
        saveData.setProperty("soundEnabled", String.valueOf(enabled));
        saveValue(SAVE_PATH, "soundEnabled", enabled);
    }


    /** Returns the saved master volume (0–100). Defaults to 80. */
    public int getVolume() {
        return getInt(saveData, "volume", 80);
    }

    /** Saves the master volume. */
    public void setVolume(int volume) {
        saveData.setProperty("volume", String.valueOf(volume));
        saveValue(SAVE_PATH, "volume", volume);
    }

    /** Returns true if the tutorial is completed */
    public boolean getTutorialStatus() {
        return getBoolean(saveData, "TutorialStatus", false); // read raw string //TODO change to false once it has been programmed
    }

    /** Saves if the tutorial was completed. */
    public void setTutorialStatus(boolean copleted) {
        saveData.setProperty("TutorialStatus", String.valueOf(copleted));
        saveValue(SAVE_PATH, "TutorialStatus", copleted);
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
