package ui.settings;

import greenfoot.*;
import maps.levels.GameMap;
import maps.menu.PauseMenu;
import ui.hud.PopupScreen;
import ui.hud.buttons.ClosePopupButton;
import ui.hud.buttons.MuteButton;
import ui.settings.sound.SongButton;
import ui.settings.sound.SongDropDown;
import ui.settings.sound.VolumeSlider;

import java.util.List;
import java.util.Objects;


    /**
     * @author julian
     * @author colin
     */
public class SettingsPopup extends PopupScreen {

    private VolumeSlider volumeSlider;
    private SongDropDown songDropDown;
    private MuteButton muteButton;
    private ClosePopupButton closeButton;


    public SettingsPopup() {
        GreenfootImage boxImage = getImage();
        boxImage.setFont(new Font("Arial", true, false, 24));
        boxImage.drawString("Settings:", 20, boxImage.getHeight() / 2);
        setImage(boxImage);

        closeButton = null;
    }

    public void addedToWorld(World w) {
        volumeSlider = new VolumeSlider();
        songDropDown = new SongDropDown();
        muteButton = new MuteButton();
        w.addObject(volumeSlider, getX() - getImage().getWidth() / 3, getY());
        w.addObject(songDropDown, getX()+getImage().getWidth()/3, getY());
        w.addObject(muteButton,getX(),getY());

        // Automatically adds the close button to the top right of this popup when the popup is added
        int buttonX = getX() + (getImage().getWidth() / 2) - 20;
        int buttonY = getY() - (getImage().getHeight() / 2) + 20;

        if(!(getWorld() instanceof GameMap)) {
            closeButton = new ClosePopupButton(this); // so maps dont have a close button bc they get return
        }
        if(closeButton != null) {
            w.addObject(closeButton, buttonX, buttonY);
        }

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
        if(closeButton != null) {
            w.removeObject(closeButton);
        }
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

    public void act() {
        if(Objects.equals(Greenfoot.getKey(), "escape")) {
            onRemove();
        }
    }


}