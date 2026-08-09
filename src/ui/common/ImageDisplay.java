package ui.common;

import core.MainClass;
import greenfoot.Font;
import greenfoot.GreenfootImage;
import util.Clickable;

/**
 * @Author Colin
 * @Author Mathilo
 */


public class ImageDisplay extends MainClass{
    /**
     * @param image
     * @param scaleX
     * @param scaleY
     */
    public ImageDisplay(String image, int scaleX, int scaleY) {
        setImage(image);
        getImage().scale(scaleX, scaleY);
    }

    /**
     * @param img
     */
    public ImageDisplay(GreenfootImage img){
        setImage(img);
    }

    /**
     * @Usecase Display pictures in their original resolution
     *
     * @param filename
     */
    public ImageDisplay(String filename){
        setImage(filename);
    }

    /**
     * @Usecase Draw new Imagedisplays with your preferred font, color, size and custom text
     * @param height
     * @param width
     * @param message
     * @param backGroundColor
     * @param textColor
     * @param textFont
     */
    public ImageDisplay(int height, int width, String message, greenfoot.Color backGroundColor, greenfoot.Color textColor, Font textFont){
        GreenfootImage boxImage = new GreenfootImage(width, height);
        boxImage.setColor(backGroundColor);boxImage.fill();
        boxImage.setColor(textColor);
        boxImage.setFont(textFont);
        boxImage.drawString(message, 20, height / 2);
        setImage(boxImage);
    }
}
