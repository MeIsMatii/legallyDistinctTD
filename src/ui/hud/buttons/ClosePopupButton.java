package ui.hud.buttons;

import greenfoot.Color;
import greenfoot.GreenfootImage;
import ui.hud.PopupScreen;
/**
 * @Author Colin
 * @Author Mathilo
 */
public class ClosePopupButton extends Button {

    public ClosePopupButton(PopupScreen popupTarget) {
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
        this.owner = popupTarget;
    }


    @Override
    public void onClick() {
        if (owner != null && getWorld() != null) {

            owner.onRemove();

        }
    }
}