package ui.hud;

import core.MainClass;
import greenfoot.*;
import ui.hud.buttons.ClosePopupButton;

public class SaveLoadPopup extends MainClass {
    private ClosePopupButton closeButton;

    public SaveLoadPopup(String text) {
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
    }

    @Override
    protected void addedToWorld(World world) {
        // Automatically adds the close button to the top right of this popup when the popup is added
        int buttonX = getX() + (getImage().getWidth() / 2) - 20;
        int buttonY = getY() - (getImage().getHeight() / 2) + 20;
        world.addObject(closeButton, buttonX, buttonY);
    }

    public void act() {
        super.act()
        if ("escape".equals(Greenfoot.getKey())) {
            closeButton.onClick()
        }
    
}
