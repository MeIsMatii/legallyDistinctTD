package ui.common;

import core.MainClass;
import greenfoot.GreenfootImage;
import util.Clickable;
/// @Author Colin, Mathilo
public class ImageDisplay extends MainClass{
    public ImageDisplay(String image, int scaleX, int scaleY) {
        setImage(image);
        getImage().scale(scaleX, scaleY);
    }
    public ImageDisplay(GreenfootImage img){
        setImage(img);
    }
    public ImageDisplay(String filename){
        setImage(filename);
    }
}
