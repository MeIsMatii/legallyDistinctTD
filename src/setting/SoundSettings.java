package setting;

import greenfoot.GreenfootSound;

import java.util.ArrayList;
import java.util.List;

public class SoundSettings {

    private static final int MinimumVolume = 0;
    private static final int MaximumVolume = 100;
    private static final int DefaultStartingVolume = 80;

    private static SoundSettings singleInstance = null;

    private final List<GreenfootSound> registeredSounds = new ArrayList<GreenfootSound>();
    private int masterVolume;

    private SoundSettings() {
        this.masterVolume = DefaultStartingVolume;
    }

    public static SoundSettings getInstance() {
        if (singleInstance == null) {
            singleInstance = new SoundSettings();
        }
        return singleInstance;
    }

    public int getMasterVolume() {
        return masterVolume;
    }

    public void setMasterVolume(int newVolume) {
        this.masterVolume = Math.max(MinimumVolume, Math.min(MaximumVolume, newVolume));
    }

    public void addRegisteredSound(GreenfootSound soundToRegister) {
        if (soundToRegister == null) {
            return;
        }
        // schaut das nicht zwei mal der gleiche sound in der liste ist
        if (registeredSounds.contains(soundToRegister)) {
            return;
        }
        registeredSounds.add(soundToRegister);
    }

    public void increaseVolume(int stepAmount) {
        setMasterVolume(this.masterVolume + stepAmount);
        syncGlobalVolume();
    }

    public void decreaseVolume(int stepAmount) {
        setMasterVolume(this.masterVolume - stepAmount);
        syncGlobalVolume();
    }

    public void syncGlobalVolume() {
        if (registeredSounds.isEmpty()) {
            return;
        }

        //sounds die gelöscht werden sollen
        List<GreenfootSound> soundsToRemove = new ArrayList<GreenfootSound>();

        for (GreenfootSound sound : registeredSounds) {
            if (sound.isPlaying()) {
                //sound ist aktiv
                sound.setVolume(masterVolume);
            } else {
                // sound nicht aktiv aber trotzdem lautstärke anpassen, so dass loops in der lautstärke verändertt werden können
                sound.setVolume(masterVolume);
            }
        }
        //alle sounds die nicht mehr spielen löschen
        registeredSounds.removeAll(soundsToRemove);
    }
}