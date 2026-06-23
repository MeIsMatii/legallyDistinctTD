package ui.hud;

import greenfoot.*;
import ui.hud.buttons.Button;
import ui.hud.buttons.ClosePopupButton;
import ui.hud.buttons.LoadSaveButton;
import ui.hud.buttons.NewSaveButton;

public class NewGamePopup extends PopupScreen {
    private ClosePopupButton closeButton;
    private Button rightButton;
    private Button leftButton;

    private int worldNumber;

    public NewGamePopup(String text, int worldNumber, Button leftButton, Button rightButton) {
        int width = 800;
        int height = 600;
        this.worldNumber = worldNumber;
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

        world.addObject(rightButton,1200,700);
        world.addObject(leftButton,800,700);
    }

    public void act() {
        super.act();
        if ("escape".equals(Greenfoot.getKey())) {
            closeButton.onClick();
        }
    }

    public void onRemove() {
        System.out.println("onremove");
        getWorld().removeObject(rightButton);
        getWorld().removeObject(leftButton);
        getWorld().removeObject(closeButton);
        getWorld().removeObject(this);
    }
}
