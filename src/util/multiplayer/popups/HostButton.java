package util.multiplayer.popups;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import ui.hud.buttons.Button;
import util.multiplayer.NetworkManager;

/**
 * @author Mathilo
 */
public class HostButton extends Button {
    public HostButton() {
        GreenfootImage img = new GreenfootImage(100, 50);
        img.setColor(Color.BLACK);
        img.fillRect(0, 0, 100, 50);
        img.setColor(Color.WHITE);
        img.drawString("Host", img.getWidth() / 2, img.getHeight() / 2);
        setImage(img);

    }

    @Override
    public void onClick() {
        NetworkManager.getInstance().setMultiplayer(true);
        System.out.println(NetworkManager.getInstance().isMultiplayer());
        owner.onRemove();
    }
}
