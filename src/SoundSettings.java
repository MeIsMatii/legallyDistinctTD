import greenfoot.GreenfootSound;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class SoundSettings extends MainClass {

    private static final int MinimumVolume = 0;
    private static final int Maximum_Volume = 100;//(max greenfoot lautstärke)
    private static final int DefaultStartingVolume = 80;
    private static SoundSettings singleInstance = null; //static so dass es bei weltänderungen bestehen bleibt
    private List<GreenfootSound> registeredSounds = new ArrayList<GreenfootSound>();
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

    /// setter und getter

    public void setMasterVolume(int newVolume) {
        // Math.max und Math.min stellen sicher das die zahl zwischen 0 und 100 ist(um fehler zu vermeiden)
        this.masterVolume = Math.max(MinimumVolume, Math.min(Maximum_Volume, newVolume));
/*
        List<HasSound> objectsWithSounds = get
        for (HasSound obj : objectsWithSounds) {
            obj.setVolume(masterVolume);
        }
*/
    }

    public void addRegisteredSound(GreenfootSound soundToRegister) {

        if (soundToRegister == null || (!registeredSounds.isEmpty() && registeredSounds.contains(soundToRegister))) {
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
        ///TODO fix colin
        if(registeredSounds.isEmpty()) {
            return;
        }
        try {
            for(GreenfootSound sound: registeredSounds) {

                if (!sound.isPlaying()) {
                    registeredSounds.remove(sound);
                    continue;
                }
                sound.setVolume(masterVolume);
            }
            } catch (Exception ConcurrentModificationException) {
                System.out.println("uhmmmmmm DM @colin \"i timing'd the sound\" ");
            
        }

    }


}
