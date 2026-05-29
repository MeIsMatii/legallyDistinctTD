package util;

import java.io.*;
import java.util.Properties;

public interface Saveable {



    default Properties loadSave(String filePath) {
        Properties props = new Properties(); // Properties ist Javas built-in key=value store
        try {
            // file öffnen
            FileInputStream fis = new FileInputStream(filePath);
            props.load(fis); // macht alle key=value lines zu props
            fis.close();     // file schließen
            System.out.println("Save loaded from: " + filePath);
        } catch (FileNotFoundException e) {
            //file existiert nicht
            System.out.println("No save file found at: " + filePath + " (will be created on first save)");
        } catch (IOException e) {
            // fehler beim lesen
            System.out.println("Error loading save: " + e.getMessage());
        }
        return props; // returns data
    }



    default String getValue(Properties props, String key) {
        return props.getProperty(key); // returns the value, or null if not there
        //ja kommentiere ab jetzt in english
    }


    default int getInt(Properties props, String key, int defaultValue) {
        String raw = props.getProperty(key); // get the raw string
        try {
            return Integer.parseInt(raw.trim()); // convert string to int
        } catch (NumberFormatException e) {
            System.out.println("Error with: " + key);
            return defaultValue; //
        }
    }

    default double getDouble(Properties props, String key, double defaultValue) {
        String raw = props.getProperty(key); // get the raw string
        try {
            return Double.parseDouble(raw.trim()); // convert string to double
        } catch (NumberFormatException e) {
            System.out.println("Error with: " + key);
            return defaultValue;
        }
    }


    default boolean getBoolean(Properties props, String key, boolean defaultValue) {
        String raw = props.getProperty(key); // get the raw string
        return Boolean.parseBoolean(raw.trim()); // "true" = true, everything else = false
    }

    default void saveValue(String filePath, String key, Object value) {
        Properties props = new Properties(); // start with an empty save

        /// first load the existing file so we don't erase values !!!Don't Remove!!!
        try {
            FileInputStream fis = new FileInputStream(filePath);
            props.load(fis); // load existing data
            fis.close();
        } catch (FileNotFoundException e) {
            // file doesn't exist
            //TODO create a file @Colin
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
            props.store(fos, "Tower Defense Save File");        // write all key=value
            fos.close(); // close after writing
        } catch (IOException e) {
            System.out.println("Error saving value: " + e.getMessage());
        }
    }
    /// I don't know how this works 100%, it just works this is 70% code from the docs and 30% guessing

}