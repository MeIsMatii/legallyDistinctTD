package core;

import greenfoot.Actor;

/**
 * @author Mathilo
 * @author Colin
 * @author Julian
 */
public abstract class MainClass extends Actor {
    private boolean isPaused;

    public MainClass() {
        setPaused(false);
    }


    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }


}
