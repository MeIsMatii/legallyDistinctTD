package util.multiplayer;

import core.MainClass;
import greenfoot.Actor;
import greenfoot.GreenfootImage;
import ui.hud.PopupScreen;
import ui.hud.QuestionPopup;
import ui.hud.buttons.ClosePopupButton;
import util.Clickable;
import util.multiplayer.popups.ClientButton;
import util.multiplayer.popups.HostButton;

/**
 * @author Mathilo
 */
public class MultiplayerPreview extends Actor implements Clickable  {
    public MultiplayerPreview() {
        GreenfootImage im = new GreenfootImage("Maps/MapPreview/Multiplayer.png");
        im.scale(500, 300);
        setImage(im);
    }

    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        if (!getWorld().getObjects(PopupScreen.class).isEmpty()) {
            System.out.println("Multiplayer popup could not be opened bc blocked by: " + getWorld().getObjects(PopupScreen.class));
            return;
        }

        boolean isMultiplayer = NetworkManager.getInstance().isMultiplayer();
        System.out.println("Multiplayer: " + isMultiplayer);
        if (!isMultiplayer) {


            HostButton hostButton = new HostButton(true);
            ClientButton clientButton = new ClientButton();
            //Reference to the currently open popup, null when not open.

            QuestionPopup questionPopup = new QuestionPopup("Would you like to host a game\nor join an already existing game?", hostButton, clientButton);

            hostButton.setOwner(questionPopup);
            clientButton.setOwner(questionPopup);

            getWorld().addObject(questionPopup, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            return;
        }
        //else
        ClosePopupButton closePopupButton = new ClosePopupButton(null);
        closePopupButton.setImage("buttons/NoButton.png");
        HostButton hostButton = new HostButton(false);
        hostButton.setImage("buttons/YesButton.png");

        QuestionPopup questionPopup = new QuestionPopup("Would you like to stop hosting?", closePopupButton, hostButton);

        closePopupButton.setOwner(questionPopup);
        hostButton.setOwner(questionPopup);

        getWorld().addObject(questionPopup, getWorld().getWidth() / 2, getWorld().getHeight() / 2);

    }


}