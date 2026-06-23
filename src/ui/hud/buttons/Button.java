package ui.hud.buttons;

import core.MainClass;
import greenfoot.Actor;
import util.Clickable;

public abstract class Button extends Actor implements Clickable {
    public void act() {
        checkClick();
    }
}
