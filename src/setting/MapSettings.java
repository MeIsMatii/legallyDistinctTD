package setting;

import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.World;
import util.HasSound;

public class MapSettings extends World implements HasSound {
    private final GreenfootSound backgroundMusic;

    public MapSettings() {
        super(29, 29, 20);
        setBackground("dirtsquare.png");
        addObject(new HomeButton(), 4, 4);
        addObject(new VolumeSlider(), 6, 10);
        backgroundMusic = playSoundAndKeep("TEMPTropischSound.mp3");
    }

    @Override
    public void act() {
        if (Greenfoot.isKeyDown("F2")) {
            backgroundMusic.playLoop();
        }
        if (Greenfoot.isKeyDown("F1")) {
            backgroundMusic.stop();
        }
    }
}
