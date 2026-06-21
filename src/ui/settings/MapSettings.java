package ui.settings;

import ui.hud.buttons.MuteButton;
import ui.settings.sound.SongDropDown;
import ui.settings.sound.VolumeSlider;
import util.HasSound;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;
import map.menu.MapSelector;
import ui.common.BackButton;


public class MapSettings extends World implements HasSound {
    private final GreenfootSound backgroundMusic;

    public MapSettings() {
        super(1920, 1080, 1);
        setBackground("dirtsquare.png");
        addObject(new BackButton(new MapSelector()), 50, 50);
        addObject(new VolumeSlider(), 700, 500);
        backgroundMusic = playSoundAndKeep("TEMPTropischSound.mp3");
        addObject(new SongDropDown(),1200,500);
        addObject(new MuteButton(),400,500);
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
