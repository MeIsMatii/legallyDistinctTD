package ui.hud.buttons;

import greenfoot.GreenfootImage;
import ui.settings.sound.SoundSettings;
import util.saves.SaveManager;

/**
 * @author Colin
 */
public class MuteButton extends Button {
    private boolean muted = !SaveManager.getInstance().isSoundOn();

    public MuteButton() {
        updateApperance();
    }


    @Override
    public void onClick() {
        if (SoundSettings.isMuted()) {
            SoundSettings.getInstance().unMuteAllSounds();
            muted = false;
        } else {
            SoundSettings.getInstance().muteALLSound();
            muted = true;
        }
        updateApperance();
    }

    public void updateApperance() {
        if (muted) {
            GreenfootImage img = new GreenfootImage("buttons/LautsprecherMuted.png");
            img.scale(30, 30);
            setImage(img);
        } else {
            GreenfootImage img = new GreenfootImage("buttons/Lautsprecher.png");
            img.scale(30, 30);
            setImage(img);
        }
    }
}
