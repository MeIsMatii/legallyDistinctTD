package ui.settings.sound;

import greenfoot.GreenfootSound;
import util.saves.SaveManager;

import java.util.ArrayList;
import java.util.List;

public class SoundSettings {

    private static final int MINIMUM_VOLUME = 0;
    private static final int MAXIMUM_VOLUME = 100;
    private static final int DEFAULT_STARTING_VOLUME = SaveManager.getInstance().getVolume();
    private static boolean muted = false;

    private static SoundSettings singleInstance = null;

    private final List<GreenfootSound> registeredSounds = new ArrayList<>();
    private int masterVolume;

    private SoundSettings() {
        if (SaveManager.getInstance().isSoundOn()) {
            this.masterVolume = DEFAULT_STARTING_VOLUME;
        } else {
            muteALLSound();
        }
    }

    public static boolean isMuted() {
        return muted;
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
        if (muted) {
            unMuteAllSounds();
            SaveManager.getInstance().setSoundOn(true);
        }
        this.masterVolume = Math.max(MINIMUM_VOLUME, Math.min(MAXIMUM_VOLUME, newVolume));
        SaveManager.getInstance().setVolume(this.masterVolume);
    }


    public void muteALLSound() {
        this.masterVolume = MINIMUM_VOLUME;
        syncGlobalVolume();
        muted = true;
        SaveManager.getInstance().setSoundOn(false);
    }

    public void unMuteAllSounds() {
        SaveManager.getInstance().reload();
        this.masterVolume = SaveManager.getInstance().getVolume();
        syncGlobalVolume();
        muted = false;
        SaveManager.getInstance().setSoundOn(true);
    }

    public void addRegisteredSound(GreenfootSound soundToRegister) {
        SaveManager saveManager = SaveManager.getInstance();
        if (soundToRegister == null) {
            return;
        }
        if (!saveManager.isSoundOn()) {
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
                soundsToRemove.add(sound);
            }
        }
        //alle sounds die nicht mehr spielen löschen
        registeredSounds.removeAll(soundsToRemove);
    }
}