package ui.settings.gambling;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.w3c.dom.Text;

public class TextBlock extends Actor {

    private final String text;
    private final double speed;
    private double posY;

    public double getSpeed() {
        return speed;
    }

    public TextBlock(String text, double speed){
        this.text = text;
        this.speed = speed;
        setImage(new GreenfootImage(text, 16, Color.WHITE, Color.BLACK));

    }

    public void addedToWorld(World world){
        posY = getY();
    }


    public void act(){
        goUp();
    }

    public void goUp(){
        int x = getX();
        posY += speed;

        setLocation(x, (int) Math.round(posY));

        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }





}
