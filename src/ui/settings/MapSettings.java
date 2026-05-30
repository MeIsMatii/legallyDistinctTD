package ui.settings;

import core.HasSound;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;
import ui.common.HomeButton;
import ui.common.ImageDisplay;


public class MapSettings extends World implements HasSound {
    private final GreenfootSound backgroundMusic;

    public MapSettings() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        addObject(new HomeButton(), 1, 1);
        addObject(new VolumeSlider(), 9, 7);
        backgroundMusic = playSoundAndKeep("TEMPTropischSound.mp3");
        addObject(new SongDropDown(),21,6);
        addObject(new MuteButton(),2,7);
    }

    @Override
    public void act() {
        debug();
    }
    private void debug(){
        if (Greenfoot.isKeyDown("F3")){
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            System.out.println("X: "+ mouseInfo.getX()+"    Y: "+ mouseInfo.getY() );
            return;
        }
        if (Greenfoot.isKeyDown("F2")) {
            backgroundMusic.playLoop();
        }
        if (Greenfoot.isKeyDown("F1")) {
            backgroundMusic.stop();
        }
    }
}
