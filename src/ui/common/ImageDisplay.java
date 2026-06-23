package ui.common;

import core.MainClass;
import greenfoot.GreenfootImage;
import util.Clickable;

public class ImageDisplay extends MainClass{
    public ImageDisplay(String image, int scaleX, int scaleY) {
        setImage(image);
        getImage().scale(scaleX, scaleY);
    }
    public ImageDisplay(GreenfootImage img){
        setImage(img);
    }
}
