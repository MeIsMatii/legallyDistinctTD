package ui.settings;

import greenfoot.Font;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import maps.levels.GameMap;
import maps.menu.MapSelector;
import ui.hud.PopupScreen;
import ui.hud.QuestionPopup;
import ui.hud.buttons.*;
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
    private DeveloperNotesButton DeveloperNotesButton;
    private GoToTutButton GoToTutButton;


    public SettingsPopup() {
        GreenfootImage boxImage = getImage();
        boxImage.setFont(new Font("Arial", true, false, 24));
        setImage(boxImage);

        closeButton = null;
    }

    public void addedToWorld(World w) {
        volumeSlider = new VolumeSlider();
        songDropDown = new SongDropDown();
        muteButton = new MuteButton();
        GoToTutButton = new GoToTutButton();
        DeveloperNotesButton = new DeveloperNotesButton();
        w.addObject(volumeSlider, getX() - getImage().getWidth() / 3, getY());
        w.addObject(songDropDown, getX() + getImage().getWidth() / 3, getY());
        w.addObject(muteButton, getImage().getWidth(), getY());

        if (getWorld() instanceof MapSelector) {
            w.addObject(DeveloperNotesButton, getX() - 200, 650);
            w.addObject(GoToTutButton, getX() + 200, 650);
        }
        // Automatically adds the close button to the top right of this popup when the popup is added
        int buttonX = getX() + (getImage().getWidth() / 2) - 20;
        int buttonY = getY() - (getImage().getHeight() / 2) + 20;

        if (!(getWorld() instanceof GameMap)) {
            closeButton = new ClosePopupButton(this); // so maps dont have a close button bc they get return
        }
        if (closeButton != null) {
            w.addObject(closeButton, buttonX, buttonY);
        }

        List<QuestionPopup> questionPopups = w.getObjects(QuestionPopup.class);
        if (!questionPopups.isEmpty()) {
            for (QuestionPopup questionPopup : questionPopups) {
                questionPopup.onRemove();
            }
        }

        List<RetryButton> retryButtons = w.getObjects(RetryButton.class);
        if (!retryButtons.isEmpty()) {
            for (RetryButton retryButton : retryButtons) {
                w.removeObject(retryButton);
            }
        }

        List<WaveResetButton> waveResetButtons = w.getObjects(WaveResetButton.class);
        if (!waveResetButtons.isEmpty()) {
            for (WaveResetButton waveResetButton : waveResetButtons) {
                w.removeObject(waveResetButton);
            }
        }

    }


    public void onRemove() {
        World w = getWorld();

        w.removeObject(volumeSlider);
        w.removeObject(songDropDown);
        w.removeObject(muteButton);
        w.removeObject(GoToTutButton);
        w.removeObject(DeveloperNotesButton);
        if (closeButton != null) {
            w.removeObject(closeButton);
        }
        List<SongButton> songButtons = w.getObjects(SongButton.class);
        if (!songButtons.isEmpty()) {
            for (SongButton songButton1 : songButtons) {
                w.removeObject(songButton1);
            }
        }
        w.removeObject(this);


    }

    public void act() {
        if (Objects.equals(Greenfoot.getKey(), "escape")) {
            onRemove();
        }

        if (songDropDown.isOpen()) {
            List<GoToTutButton> goToTutButtons = getWorld().getObjects(GoToTutButton.class);
            for (GoToTutButton goToTutButton : goToTutButtons) {
                getWorld().removeObject(goToTutButton);
            }

        } else if (!songDropDown.isOpen() && getWorld().getObjects(GoToTutButton.class).isEmpty()) {
            getWorld().addObject(GoToTutButton, getX() + 200, 650);
        }

    }


}