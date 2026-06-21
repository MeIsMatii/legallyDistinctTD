package map.menu;

import core.MainClass;
import greenfoot.Color;
import greenfoot.Font;
import greenfoot.GreenfootImage;
import ui.common.CustomImageDisplay;
import ui.hud.buttons.ClosePopupButton;
import util.Clickable;

public class MultiplayerPreview extends MainClass implements Clickable {
    CustomImageDisplay img = new CustomImageDisplay(700, 500, "coming soon", new Color(139, 69, 19), Color.WHITE, new Font("Arial", true, false, 24));
    ClosePopupButton closeButton;
    boolean clicked = false;

    public MultiplayerPreview() {
        GreenfootImage im = new GreenfootImage("Multiplayer.png");
        im.scale(500, 300);
        setImage(im);
        closeButton = new ClosePopupButton(this);
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        if (!clicked) {
            getWorld().addObject(img, 1000, 540);
            getWorld().addObject(closeButton, 1220,208);
            clicked = true;
        } else {
            closePopup();
        }
    }

    public void closePopup() {
        if (getWorld() != null) {
            getWorld().removeObject(img);
            getWorld().removeObject(closeButton);
        }
        clicked = false;
    }
}