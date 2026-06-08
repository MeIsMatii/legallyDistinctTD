package ui.settings.gambling;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class TextBlock extends Actor {

    private final String text;
    private final int speed;

    public TextBlock(String text, int speed){
        this.text = text;
        this.speed = speed;
        setImage(new GreenfootImage(text, 16, Color.WHITE, Color.BLACK));
    }


}
