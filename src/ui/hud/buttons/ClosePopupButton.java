package ui.hud.buttons;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import map.menu.MultiplayerPreview;
import util.Clickable;

public class ClosePopupButton extends Actor implements Clickable {
    private Actor popupTarget;

    public ClosePopupButton(Actor popupTarget) {
        GreenfootImage image = new GreenfootImage(30, 30);
        image.setColor(new Color(60, 60, 60));
        image.fill();

        image.setColor(Color.WHITE);
        for (int i = -1; i <= 1; i++) {
            image.drawLine(8 + i, 8, 22 + i, 22);
            image.drawLine(22 + i, 8, 8 + i, 22);
            image.drawLine(8, 8 + i, 22, 22 + i);
            image.drawLine(22, 8 + i, 8, 22 + i);
        }

        setImage(image);
        this.popupTarget = popupTarget;
    }

    @Override
    public void act() {
        checkClick();
    }

    @Override
    public void onClick() {
        if (popupTarget != null && getWorld() != null) {
            if (popupTarget instanceof MultiplayerPreview) {
                ((MultiplayerPreview) popupTarget).closePopup();
            } else {
                getWorld().removeObject(popupTarget);
                getWorld().removeObject(this);
            }
        }
    }
}