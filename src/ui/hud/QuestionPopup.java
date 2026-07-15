package ui.hud;

import greenfoot.*;
import ui.hud.buttons.Button;
import ui.hud.buttons.ClosePopupButton;

/**
 * a basic popup with text, a left button and a right button.
 */

public class QuestionPopup extends PopupScreen {
    private final ClosePopupButton closeButton;
    private final Button rightButton;
    private final Button leftButton;

    public QuestionPopup(String text, Button leftButton, Button rightButton) {
        int width = 800;
        int height = 600;
        GreenfootImage boxImage = new GreenfootImage(width, height);
        boxImage.setColor(new Color(139, 69, 19));
        boxImage.fill();
        boxImage.setColor(Color.WHITE);
        boxImage.setFont(new Font("Arial", true, false, 24));
        boxImage.drawString(text, 20, height / 2);
        setImage(boxImage);

        closeButton = new ClosePopupButton(this);
        this.rightButton = rightButton;
        this.leftButton = leftButton;
    }

    @Override
    protected void addedToWorld(World world) {
        // Automatically adds the close button to the top right of this popup when the popup is added
        int buttonX = getX() + (getImage().getWidth() / 2) - 20;
        int buttonY = getY() - (getImage().getHeight() / 2) + 20;
        world.addObject(closeButton, buttonX, buttonY);

        world.addObject(rightButton, 1200, 700);
        world.addObject(leftButton, 800, 700);
    }

    public void act() {
        super.act();
        if ("escape".equals(Greenfoot.getKey())) {
            closeButton.onClick();
        }
    }

    public void onRemove() {
        getWorld().removeObject(rightButton);
        getWorld().removeObject(leftButton);
        getWorld().removeObject(closeButton);
        getWorld().removeObject(this);
    }
}
