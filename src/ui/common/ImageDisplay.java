package ui.common;

import core.MainClass;
import greenfoot.Font;
import greenfoot.GreenfootImage;

/**
 * @author Colin
 */


public class ImageDisplay extends MainClass {
    /**
     * @param image  the image to be used.
     * @param scaleX the scaleX of the image.
     * @param scaleY the scaleY of the image.
     */
    public ImageDisplay(String image, int scaleX, int scaleY) {
        setImage(image);
        getImage().scale(scaleX, scaleY);
    }

    /**
     * @param img the image to be used.
     */
    public ImageDisplay(GreenfootImage img) {
        setImage(img);
    }

    /**
     * @param filename the filename (and location) of the image.
     * @Usecase Display pictures in their original resolution
     */
    public ImageDisplay(String filename) {
        setImage(filename);
    }

    /**
     * @param height          height.
     * @param width           width.
     * @param message         text to be displayed.
     * @param backGroundColor backgroundcolour.
     * @param textColor       textcolour.
     * @param textFont        font.
     * @Usecase Draw new Imagedisplays with your preferred font, color, size and custom text
     */
    public ImageDisplay(int height, int width, String message, greenfoot.Color backGroundColor, greenfoot.Color textColor, Font textFont) {
        GreenfootImage boxImage = new GreenfootImage(width, height);
        boxImage.setColor(backGroundColor);
        boxImage.fill();
        boxImage.setColor(textColor);
        boxImage.setFont(textFont);
        boxImage.drawString(message, 20, height / 2);
        setImage(boxImage);
    }
}
