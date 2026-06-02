package core;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class MainClass extends Actor {
    private boolean isPaused;
    private int sleepTicks;

    public MainClass() {
        setPaused(false);
    }

    public void act() {

    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }



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
