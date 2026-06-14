package ui.settings;

import core.Player;
import entities.Entity;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import map.menu.PauseMenu;

import java.util.List;

public class SettingsPopup extends Actor {

    private VolumeSlider volumeSlider;
    private SongDropDown songDropDown;
    private MuteButton muteButton;


    public SettingsPopup() {
        GreenfootImage img = new GreenfootImage(1500, 700);
        img.setColor(Color.YELLOW);
        img.fill();
        setImage(img);
    }

    public void addedToWorld(World w) {
        volumeSlider = new VolumeSlider();
        songDropDown = new SongDropDown();
        muteButton = new MuteButton();
        w.addObject(volumeSlider, getX() - getImage().getWidth() / 3, getY());
        w.addObject(songDropDown, getX()+getImage().getWidth()/3, getY());
        w.addObject(muteButton,getX(),getY());

    }


    public void onRemove() {
        World w = getWorld();

        w.removeObject(volumeSlider);
        w.removeObject(songDropDown);
        List<SongButton> songButtons = w.getObjects(SongButton.class);
        if (!songButtons.isEmpty()) {
            for (SongButton songButton1 : songButtons) {
                w.removeObject(songButton1);
            }
        }
        w.removeObject(this);

        List<PauseMenu> pauseMenus = w.getObjects(PauseMenu.class);
        if (!pauseMenus.isEmpty()) {
            for (PauseMenu pauseMenu : pauseMenus) {
                pauseMenu.onRemove();
            }
        }



    }


}