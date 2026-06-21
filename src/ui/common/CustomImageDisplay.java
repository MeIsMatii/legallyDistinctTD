package ui.common;

import core.MainClass;
import greenfoot.Font;
import greenfoot.GreenfootImage;

import java.awt.*;

public class CustomImageDisplay extends MainClass {
    public CustomImageDisplay(int height, int width, String message, greenfoot.Color backGroundColor, greenfoot.Color textColor, Font textFont){
        GreenfootImage boxImage = new GreenfootImage(width, height);
        boxImage.setColor(backGroundColor);boxImage.fill();
        boxImage.setColor(textColor);
        boxImage.setFont(textFont);
        boxImage.drawString(message, 20, height / 2);
        setImage(boxImage);
    }
}
