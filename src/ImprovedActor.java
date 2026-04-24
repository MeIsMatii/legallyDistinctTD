import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class ImprovedActor extends Actor {
    private GreenfootImage currentImage; //stored to ensure paint can be removed

    /**
     * Sets the image saving the old image for later use.
     * @param image to set
     */
    @Override
    public void setImage(GreenfootImage image){
        currentImage = image;
        super.setImage(new ImprovedGreenfootImage(image));

    }


    /**
     * Draws the text over the current image.
     * @param text to be drawn
     */
    public void draw(String text){
        ImprovedGreenfootImage image = new ImprovedGreenfootImage(currentImage);
        image.drawString(text, 0, 10);
        super.setImage(image);
    }

    /**
     * Draws the int value over the current image.
     * @param value to be drawn
     */
    public void draw(int value){
        draw(String.valueOf(value));
    }
    public void drawPrice(int value){
        ImprovedGreenfootImage image = new ImprovedGreenfootImage(getImage());
        image.drawString(String.valueOf(value), 0, 49);
        super.setImage(image);
    }
}
