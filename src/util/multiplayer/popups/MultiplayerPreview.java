package util.multiplayer.popups;

import core.MainClass;
import greenfoot.GreenfootImage;
import ui.hud.QuestionPopup;
import util.Clickable;

public class MultiplayerPreview extends MainClass implements Clickable {

    /**
     * The IP address the user last confirmed. Empty string if none yet.
     */
    private String lastIP = "";

    /**
     * Reference to the currently open popup, null when not open.
     */
    private QuestionPopup questionPopup = null;

    public MultiplayerPreview() {
        GreenfootImage im = new GreenfootImage("Multiplayer.png");
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
        questionPopup = new QuestionPopup("Would you like to host a game\nor join an already existing game?", hostButton, clientButton);
        hostButton.setOwner(questionPopup);
        clientButton.setOwner(questionPopup);
        getWorld().addObject(questionPopup, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
    }


}