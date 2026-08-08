package ui.common;

import core.MainClass;
import greenfoot.Font;
import greenfoot.GreenfootImage;
import util.Clickable;
/// @author Colin, Mathilo
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

    public ImageDisplay(int height, int width, String message, greenfoot.Color backGroundColor, greenfoot.Color textColor, Font textFont){
        GreenfootImage boxImage = new GreenfootImage(width, height);
        boxImage.setColor(backGroundColor);boxImage.fill();
        boxImage.setColor(textColor);
        boxImage.setFont(textFont);
        boxImage.drawString(message, 20, height / 2);
        setImage(boxImage);
    }
}
