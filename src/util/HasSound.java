package util;

import greenfoot.GreenfootSound;
import ui.settings.sound.SoundSettings;
import util.saves.SaveManager;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


public interface HasSound {

    default boolean isAudioHardwareSupported() { //bc me on linux and it dont work --Mathilo
        try {

            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0f, 16, 2, 4, 44100.0f, false);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);


            return AudioSystem.isLineSupported(info);
        } catch (Exception e) {
            return false;
        }
    }


    default void playSound(String soundFileName) {
        if(!SaveManager.getInstance().isSoundOn()) {
            return;
        }

        if(!isAudioHardwareSupported()) {
            return;
        }
        GreenfootSound soundToPlay;
        try {
            soundToPlay = new GreenfootSound(soundFileName);//welcher sound


            soundToPlay.setVolume(SoundSettings.getInstance().getMasterVolume());//volume control
            SoundSettings.getInstance().addRegisteredSound(soundToPlay);//liste hinzufügen
            soundToPlay.play();//play
        } catch (Exception e) {
            return;
        }
    }

    default void playSound(GreenfootSound soundToPlay) {
        if(!SaveManager.getInstance().isSoundOn()) {
            return;
        }
        if(!isAudioHardwareSupported()) {
            return;
        }
        try {
            soundToPlay.setVolume(SoundSettings.getInstance().getMasterVolume());//ein bereitsexistierender sound
            SoundSettings.getInstance().addRegisteredSound(soundToPlay);//liste hinzufügen
            soundToPlay.play();//sync
        } catch (Exception e) {
            return;
        }
    }

    default GreenfootSound playSoundAndKeep(String soundFileName) {
        if(!SaveManager.getInstance().isSoundOn()) {
            return null;
        }
        if(!isAudioHardwareSupported()) {
            return null;
        }

        try {
            GreenfootSound soundToControl = new GreenfootSound(soundFileName);//welcher sound
            soundToControl.setVolume(SoundSettings.getInstance().getMasterVolume());//set volume
            SoundSettings.getInstance().addRegisteredSound(soundToControl);//liste hinzufügen
            return soundToControl;//gibt den fertig angepassten sound zurück
        } catch (Exception e) {
            return null;
        }
    }

    default void hardWareMute() { //incase hardware doesnt work
        SaveManager.getInstance().setSoundOn(false);
    }
}


