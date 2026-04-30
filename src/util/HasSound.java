package util;

import greenfoot.GreenfootSound;
import setting.SoundSettings;


public interface HasSound {
    default void playSound(String soundFileName) {
        GreenfootSound soundToPlay = new GreenfootSound(soundFileName);//welcher sound
        soundToPlay.setVolume(SoundSettings.getInstance().getMasterVolume());//volume control
        SoundSettings.getInstance().addRegisteredSound(soundToPlay);//liste hinzufügen
        soundToPlay.play();//play
    }

    default void playSound(GreenfootSound soundToPlay) {
        soundToPlay.setVolume(SoundSettings.getInstance().getMasterVolume());//ein bereitsexistierender sound
        SoundSettings.getInstance().addRegisteredSound(soundToPlay);//liste hinzufügen
        soundToPlay.play();//sync
    }

    default GreenfootSound playSoundAndKeep(String soundFileName) {
        GreenfootSound soundToControl = new GreenfootSound(soundFileName);//welcher sound
        soundToControl.setVolume(SoundSettings.getInstance().getMasterVolume());//set volume
        SoundSettings.getInstance().addRegisteredSound(soundToControl);//liste hinzufügen
        return soundToControl;//gibt den fertig angepassten sound zurück
    }


}
