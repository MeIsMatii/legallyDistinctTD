package util;

import greenfoot.Actor;
import ui.hud.PopupScreen;

public abstract class PopupOpener extends Actor implements Clickable {
    protected boolean canOpen = true;
    public void act() {
        checkClick();

        while(!canOpen) {
            if(getWorld().getObjects(PopupScreen.class).isEmpty()) {
                canOpen = true;
            }
        }
    }


    @Override
    public void onClick() {
        if(getWorld() == null) {
            return;
        }
        if(!getWorld().getObjects(PopupScreen.class).isEmpty()) {
            canOpen = false;

            System.out.println("Popup could not be opened because another one is already open.");

            return;
        }
    }
}
