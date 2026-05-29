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
    private int sleepTicks = 0;

    /**
     * Sets the time for an Actor to sleep.
     * @param ticks the time in ticks (we are using 120t/s).
     */
    public void sleepFor(int ticks) {
        this.sleepTicks = ticks;
    }

    /**
     * This function checks whether the Actor is still sleeping.
     * @return whether the Actor is still sleeping.
     */
    protected boolean isDelayed() {
        if(sleepTicks > 0) {
            sleepTicks--;
            return true;
        }
        return false;
    }

}
