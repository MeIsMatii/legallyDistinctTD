package ui.hud.gambling;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * @author jannis
 * @author  mathilo; did the starwars stuff
 */
public class TextBlock extends Actor {

    private final String text;
    private final double speed;
    private double posY;

    private int size = 48;
    private int counter = 0;
    private final int MIN_SIZE = 12;

    public TextBlock(String text, double speed) {
        this.text = text;
        this.speed = speed;
        updateImage();
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void addedToWorld(World world) {
        posY = getY();
    }

    @Override
    public void act() {
        goUp();
    }

    public void goUp() {
        counter++;
        if (counter > 6) {
            counter = 0;
            if (size > MIN_SIZE) {
                size--;
                updateImage();
            }
        }

        posY += speed;
        setLocation(getX(), (int) Math.round(posY));

        if (getY() <= 10 || isAtEdge()) {
            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
        }
    }

    private void updateImage() {
        setImage(new GreenfootImage(text, size, Color.YELLOW, new Color(0, 0, 0, 0)));
    }
}