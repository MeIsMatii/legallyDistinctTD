package ui.common;

import greenfoot.Greenfoot;
import util.Clickable;

public class ClickawayImageDisplay extends ImageDisplay implements Clickable {

    public ClickawayImageDisplay(String image, int scaleX, int scaleY) {
        super(image, scaleX, scaleY);
    }

    @Override
    public void onClick() {
        getWorld().removeObject(this);
    }

    @Override
    public void act() {
        checkClick();
    }
}
