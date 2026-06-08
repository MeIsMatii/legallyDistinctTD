package ui.settings.gambling;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class Background extends Actor {


    public Background() {
        GreenfootImage img = new GreenfootImage(2000, 2000);
        img.setColor(Color.BLACK);
        img.fill();
        setImage(img);
    }


}
