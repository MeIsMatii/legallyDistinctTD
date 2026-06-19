package util.saves;

import java.io.*;
import java.util.Properties;

public interface Saveable {


    /**
     * Loads the save file into a Properties object so values can be read.
     * Call this once before using any getValue() method.
     *
     * @param filePath path to the save file, e.g. "saves/player.txt"
     * @return a Properties object holding all key=value pairs, or null on error
     */
    default Properties loadSave(String filePath) {
        Properties props = new Properties(); // Properties is Java's built-in key=value store
        try {
            // open the file for reading
            FileInputStream fis = new FileInputStream(filePath);
            props.load(fis); // reads all key=value lines into props
            fis.close();     // always close the file after reading
            System.out.println("Save loaded from: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("No save file found at: " + filePath + " (will be created on first save)");
        } catch (IOException e) {
            // something went wrong reading the file
            System.out.println("Error loading save: " + e.getMessage());
        }
        return props; // return the loaded data (may be empty if file didn't exist)
    }

    /**
     * Gets a value as a raw String.
     *
     * @param props the Properties loaded by loadSave()
     * @param key   the key to look up, e.g. "coins"
     * @return the value as a String, or null if the key doesn't exist
     */
    default String getValue(Properties props, String key) {
        return props.getProperty(key); // returns the value, or null if missing
    }

    /**
     * Gets a value as an int.
     *
     * @param props        the Properties loaded by loadSave()
     * @param key          the key to look up, e.g. "coins"
     * @param defaultValue returned if the key doesn't exist or can't be parsed
     * @return the value as an int
     */
    default int getInt(Properties props, String key, int defaultValue) {
        String raw = props.getProperty(key); // get the raw string
        if (raw == null) return defaultValue; // key not found → use default
        try {
            return Integer.parseInt(raw.trim()); // convert string to int
        } catch (NumberFormatException e) {
            System.out.println("Could not parse int for key: " + key);
            return defaultValue; // bad format → use default
        }
    }

    /**
     * Gets a value as a double.
     *
     * @param props        the Properties loaded by loadSave()
     * @param key          the key to look up, e.g. "speed"
     * @param defaultValue returned if the key doesn't exist or can't be parsed
     * @return the value as a double
     */
    default double getDouble(Properties props, String key, double defaultValue) {
        String raw = props.getProperty(key); // get the raw string
        if (raw == null) return defaultValue; // key not found → use default
        try {
            return Double.parseDouble(raw.trim()); // convert string to double
        } catch (NumberFormatException e) {
            System.out.println("Could not parse double for key: " + key);
            return defaultValue;
        }
    }

    /**
     * Gets a value as a boolean.
     * "true" (any case) → true, anything else → false.
     *
     * @param props        the Properties loaded by loadSave()
     * @param key          the key to look up, e.g. "soundEnabled"
     * @param defaultValue returned if the key doesn't exist
     * @return the value as a boolean
     */
    default boolean getBoolean(Properties props, String key, boolean defaultValue) {
        String raw = props.getProperty(key); // get the raw string
        if (raw == null) return defaultValue; // key not found → use default
        return Boolean.parseBoolean(raw.trim()); // "true" → true, everything else → false
    }

    /**
     * Saves (or overwrites) a single key=value pair in the save file.
     * Works with any data type — just pass an int, double, boolean, String, etc.
     * Java will automatically convert it to a String via toString().
     *
     * @param filePath path to the save file, e.g. "saves/player.txt"
     * @param key      the key to set, e.g. "coins"
     * @param value    the value to store — any type (int, double, boolean, String ...)
     */
    default void saveValue(String filePath, String key, Object value) {
        Properties props = new Properties(); // start with an empty store

        // first load the existing file so we don't erase other values
        try {
            FileInputStream fis = new FileInputStream(filePath);
            props.load(fis); // load existing data
            fis.close();
        } catch (FileNotFoundException e) {
            // file doesn't exist yet — that's fine, we'll create it below
        } catch (IOException e) {
            System.out.println("Error reading save before write: " + e.getMessage());
        }

        // set (or overwrite) the given key with the new value
        props.setProperty(key, String.valueOf(value)); // String.valueOf works for all types

        // write everything back to the file
        try {
            // make sure the folder exists (e.g. "saves/" folder)
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // creates parent folders if needed

            FileOutputStream fos = new FileOutputStream(file); // open for writing
            props.store(fos, "Tower Defense Save File for general data(no saves for games)");        // write all key=value pairs
            fos.close(); // always close after writing
            System.out.println("Saved: " + key + " = " + value + " at " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving value: " + e.getMessage());
        }
    }

    /**
     * Writes a whole Properties object to a file at once.
     * Used by newGameSave() to write all defaults in one go.
     *
     * @param filePath path to the save file
     * @param props    the Properties object to write
     */
    default void saveAll(String filePath, Properties props) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();               // create folder if needed
            java.io.FileOutputStream fos = new java.io.FileOutputStream(file);
            props.store(fos, "Tower Defense Save File for general data(no saves for games)"); // write all key=value pairs
            fos.close();
        } catch (java.io.IOException e) {
            System.out.println("Error writing save: " + e.getMessage());
        }
    }
}