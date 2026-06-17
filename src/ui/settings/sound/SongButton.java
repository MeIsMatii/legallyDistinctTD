package ui.settings.sound;

import util.Clickable;
import util.HasSound;
import core.MainClass;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class SongButton extends MainClass implements Clickable, HasSound {

    private final String songName;
    private static GreenfootSound currentlyPlaying = null;

    public SongButton(String songName) {
        this.songName = songName;

        GreenfootImage image = new GreenfootImage(200, 50);
        // weißer hintergrund
        image.setColor(Color.WHITE);
        image.fillRect(0, 0, image.getWidth(), image.getHeight());
        image.setColor(Color.BLACK);//schwarzer rand
        image.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
        String label = songName.replace(".mp3", "");//Songname als label
        image.drawString(label, 10, 30);
        setImage(image);
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        // Stoppt den aktuellen song
        if (currentlyPlaying != null) {
            currentlyPlaying.stop();
        }
        // sound volume zu volume anpassen
        currentlyPlaying = playSoundAndKeep(songName);
        currentlyPlaying.playLoop();
    }

}