package core;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class MainClass extends Actor {

    private String lastPressedKey;

    private boolean isPaused;

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public MainClass() {
        setPaused(false);
    }

    public void act() {
        // TODO @Julian
        //getKey holen
        String lastPressedKey = Greenfoot.getKey();

        // falls lastPressedKey != getkey und getKey == escape dann
        if (Greenfoot.getKey() != lastPressedKey && lastPressedKey == "ESCAPE") {
            setPaused(!isPaused());
            System.out.printf("isPaused: %s\n", isPaused);
        } else {
            lastPressedKey = Greenfoot.getKey();
        }
        //  die if abfrage da

        //sonst lastPressedKey auf getKey setztens

    }
}
