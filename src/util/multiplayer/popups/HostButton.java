package util.multiplayer.popups;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import ui.hud.buttons.Button;
import util.multiplayer.NetworkManager;

/**
 * @author Mathilo
 */
public class HostButton extends Button {
    private final boolean isHosting;
    int counter = 0;
    boolean wasClicked = false;

    public HostButton(boolean isHosting) {
        this.isHosting = isHosting;
        GreenfootImage img = new GreenfootImage(100, 50);
        img.setColor(Color.BLACK);
        img.fillRect(0, 0, 100, 50);
        img.setColor(Color.WHITE);
        img.drawString("Host", img.getWidth() / 2, img.getHeight() / 2);
        setImage(img);

    }

    public void act() {
        super.act();
        if(!wasClicked) {
            return;
        }
        counter++;

        if (counter > 90) {
            getWorld().showText("", getWorld().getWidth() / 2, getWorld().getHeight() / 3);
            owner.onRemove();
        }
    }

    @Override
    public void onClick() {
        wasClicked = true;
        NetworkManager.getInstance().setMultiplayer(isHosting);
        if (isHosting) {
            getWorld().showText("To start hosting, start a new game on a map.", getWorld().getWidth() / 2, getWorld().getHeight() / 3);

        } else {
            owner.onRemove();
        }
    }
}
