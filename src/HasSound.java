import greenfoot.*;
import greenfoot.sound.Sound;


public interface HasSound {
    default void playSound(String soundFileName) {
        GreenfootSound soundToPlay = new GreenfootSound(soundFileName);
        soundToPlay.setVolume(SoundSettings.getInstance().getMasterVolume());//setzt lautstärke
        soundToPlay.play();//spielt den sound

        SoundSettings.getInstance().addRegisteredSound(soundToPlay);
    }
    default void playSound(GreenfootSound soundToPlay) {
        soundToPlay.setVolume(SoundSettings.getInstance().getMasterVolume());//setzt lautstärke
        soundToPlay.play();//spielt den sound

        SoundSettings.getInstance().addRegisteredSound(soundToPlay);
    }

    default GreenfootSound playSoundAndKeep(String soundFileName) {
        //spielt einen sound der looped ()
        GreenfootSound soundToControl = new GreenfootSound(soundFileName);
        soundToControl.setVolume(SoundSettings.getInstance().getMasterVolume());//setzt lautstärke
        return soundToControl;
    }

   //updated die lautstärke von einem existierendem sound
    default void syncVolumeOf(GreenfootSound soundToSync) {
        if (soundToSync != null) {
            soundToSync.setVolume(SoundSettings.getInstance().getMasterVolume());
        }
    }


}
