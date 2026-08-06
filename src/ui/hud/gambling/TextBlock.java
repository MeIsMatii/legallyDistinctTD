package ui.hud.gambling;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class TextBlock extends Actor {

    private final String text;
    private final double speed;
    private double posY;

    private int size = 16;

    public double getSpeed() {
        return speed;
    }

    public TextBlock(String text, double speed){
        this.text = text;
        this.speed = speed;
        setImage(new GreenfootImage(text, size, Color.YELLOW, Color.BLACK));

    }

    public void addedToWorld(World world){
        posY = getY();
    }


    public void act(){
        goUp();
    }

    public void goUp(){
        size++;
        setImage(new GreenfootImage(text, size, Color.YELLOW, Color.BLACK));
        int x = getX();
        posY += speed;

        setLocation(x, (int) Math.round(posY));

        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }





}
