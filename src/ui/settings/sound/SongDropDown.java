package ui.settings.sound;

import core.MainClass;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import util.Clickable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Colin (function)
 * @author julian (positioning)
 */
public class SongDropDown extends MainClass implements Clickable {


    //Für mehr songs einfach Filename als string hier hinschreiben
    private static final String[] SONGS = {
        "backgourndSound1.mp3",
        "backgourndSound2.mp3",
        "backgourndSound3.mp3",
        "ChillSong(Gemini).mp3",
        "TropicalSong(Gemini).mp3",
        "bloons1.mp3"
    };
    private final List<SongButton> songButtons = new ArrayList<>(); // speichert alle buttons
    private boolean isOpen = false;

    public SongDropDown() {
        draw();
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    private void draw() {
        GreenfootImage image = new GreenfootImage(200, 50);
        image.setColor(Color.WHITE);
        image.fillRect(0, 0, image.getWidth(), image.getHeight());
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);

        // Pfeil oben oder unter
        String label = isOpen ? "^ Songs (click to close)" : "v Songs (click to open)";
        image.drawString(label, 10, 30);

        setImage(image);
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        if (isOpen) {
            closeDropdown();
        } else {
            openDropdown();
        }
        isOpen = !isOpen;
        draw();          // updated den pfeil
    }

    // macht songbuttons
    private void openDropdown() {
        setLocation(getX(), getY() - 175);
        for (int i = 0; i < SONGS.length; i++) {
            SongButton button = new SongButton(SONGS[i]);

            //3zellen drunter nächtes ding
            getWorld().addObject(button, getX(), getY() + 60 + (i * 60));

            songButtons.add(button); // speichern um später zu löschen
        }
    }

    // entfernt alle sachen beim schließen
    private void closeDropdown() {
        setLocation(getX(), getY() + 175);
        for (SongButton btn : songButtons) {
            getWorld().removeObject(btn);
        }
        songButtons.clear();
    }
}