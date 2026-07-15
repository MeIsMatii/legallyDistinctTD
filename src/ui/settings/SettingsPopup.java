package ui.settings;

import greenfoot.*;
import maps.menu.PauseMenu;
import ui.hud.buttons.MuteButton;
import ui.settings.sound.SongButton;
import ui.settings.sound.SongDropDown;
import ui.settings.sound.VolumeSlider;

import java.util.List;

public class SettingsPopup extends Actor {

    private VolumeSlider volumeSlider;
    private SongDropDown songDropDown;
    private MuteButton muteButton;


    public SettingsPopup() {
        int width = 1500;
        int height = 800;
        GreenfootImage boxImage = new GreenfootImage(width, height);
        boxImage.setColor(new Color(139, 69, 19));boxImage.fill();
        boxImage.setColor(Color.WHITE);
        boxImage.setFont(new Font("Arial", true, false, 24));
        boxImage.drawString("Settings:", 20, height / 2);
        setImage(boxImage);
    }

    public void addedToWorld(World w) {
        volumeSlider = new VolumeSlider();
        songDropDown = new SongDropDown();
        muteButton = new MuteButton();
        w.addObject(volumeSlider, getX() - getImage().getWidth() / 3, getY());
        w.addObject(songDropDown, getX()+getImage().getWidth()/3, getY());
        w.addObject(muteButton,getX(),getY());

        List<PauseMenu>pauseMenus=w.getObjects(PauseMenu.class);
        if (!pauseMenus.isEmpty()){
            for (PauseMenu pauseMenu: pauseMenus){
                pauseMenu.onRemove();
            }
        }

    }


    public void onRemove() {
        World w = getWorld();

        w.removeObject(volumeSlider);
        w.removeObject(songDropDown);
        w.removeObject(muteButton);
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