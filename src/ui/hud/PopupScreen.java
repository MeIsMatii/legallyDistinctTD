package ui.hud;

import core.MainClass;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

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
