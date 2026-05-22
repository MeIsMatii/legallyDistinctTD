package core;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public abstract class MainClass extends Actor {

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
