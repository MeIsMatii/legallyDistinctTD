package ui.hud.buttons;

import core.MainClass;
import greenfoot.Actor;
import ui.hud.PopupScreen;
import util.Clickable;

public abstract class Button extends Actor implements Clickable {
    protected PopupScreen owner;
    public void act() {
        checkClick();
    }

    public void setOwner(PopupScreen owner) {
        this.owner = owner;
    }
}
