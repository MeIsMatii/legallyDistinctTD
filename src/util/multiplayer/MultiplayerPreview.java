package util.multiplayer;

import core.MainClass;
import greenfoot.GreenfootImage;
import ui.hud.QuestionPopup;
import util.Clickable;
import util.multiplayer.popups.ClientButton;
import util.multiplayer.popups.HostButton;

/**
 * @author Mathilo
 */
public class MultiplayerPreview extends MainClass implements Clickable {

    public MultiplayerPreview() {
        GreenfootImage im = new GreenfootImage("Maps/MapPreview/Multiplayer.png");
        im.scale(500, 300);
        setImage(im);
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        HostButton hostButton = new HostButton();
        ClientButton clientButton = new ClientButton();
        /**
         * Reference to the currently open popup, null when not open.
         */
        QuestionPopup questionPopup = new QuestionPopup("Would you like to host a game\nor join an already existing game?", hostButton, clientButton);
        hostButton.setOwner(questionPopup);
        clientButton.setOwner(questionPopup);
        getWorld().addObject(questionPopup, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }


}