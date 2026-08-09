package util.multiplayer.popups;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import ui.hud.buttons.Button;

/**
 * @author Mathilo
 */
public class ClientButton extends Button {

    public ClientButton() {
        GreenfootImage img = new GreenfootImage(100, 50);
        img.setColor(Color.WHITE);
        img.fillRect(0, 0, 100, 50);
        img.setColor(Color.BLACK);
        img.drawString("Join", img.getWidth() / 2, img.getHeight() / 2);
        setImage(img);
    }

    @Override
    public void onClick() {

        getWorld().addObject(new IPMenuOverlay(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        owner.onRemove();
    }
}
