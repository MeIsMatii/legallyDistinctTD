package core;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public abstract class MainClass extends Actor {
    public void scale(int ratio) {
        GreenfootImage img = getImage();
        img.scale(getImage().getWidth() * ratio, getImage().getHeight() * ratio);
        setImage(img);
    }
}
