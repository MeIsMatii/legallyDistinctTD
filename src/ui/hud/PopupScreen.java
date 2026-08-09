package ui.hud;

import greenfoot.Actor;

/**
 * @author Jan
 * @author Mathilo
 */
public abstract class PopupScreen extends Actor {
    public PopupScreen() {
        //GreenfootImage img = new GreenfootImage(1500, 700);
        //img.setColor(Color.GRAY);
        //img.fill();
        setImage("pauseSettingsMenuBlur.png");
        //setImage(img);
    }

    public abstract void onRemove();
}
