package ui.hud.buttons;

import ui.settings.sound.SoundSettings;
import util.Clickable;
import core.MainClass;
import greenfoot.GreenfootImage;
import util.saves.SaveManager;

public class MuteButton extends MainClass implements Clickable {
    private boolean muted = !SaveManager.getInstance().isSoundOn();
    public MuteButton(){
        updateApperance();
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        if (SoundSettings.isMuted()){
            SoundSettings.getInstance().unMuteAllSounds();
            muted = false;
        }else {
            SoundSettings.getInstance().muteALLSound();
            muted = true;
        }
        updateApperance();
    }
    public void updateApperance(){
        if (muted){
            GreenfootImage img = new GreenfootImage("LautsprecherMuted.png");
            img.scale(30,30);
            setImage(img);
        }else {
            GreenfootImage img = new GreenfootImage("Lautsprecher.png");
            img.scale(30,30);
            setImage(img);
        }
    }
}
