package core;

import greenfoot.GreenfootSound;
import greenfoot.sound.Mp3AudioInputStream;
import greenfoot.util.GreenfootUtil;
import ui.settings.SoundSettings;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


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
        try {

            if (javax.sound.sampled.AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, new Mp3AudioInputStream(GreenfootUtil.getURL(soundFileName, "sounds")).getFormat())) == null) {
                System.out.println("meow");
                return null;
            }


            GreenfootSound soundToControl = new GreenfootSound(soundFileName);//welcher sound
            soundToControl.setVolume(SoundSettings.getInstance().getMasterVolume());//set volume
            SoundSettings.getInstance().addRegisteredSound(soundToControl);//liste hinzufügen
            return soundToControl;//gibt den fertig angepassten sound zurück
        } catch (Exception e) {
            System.out.println("No audio device found. PLEASE FFS DO NOT DO ANYTHING WITH THE VOLUME AND MUSIC STUFF --Mathilo");
            return null;
        }
    }


}
