package util;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;

public class Cursor extends Actor {

    public Cursor() {
        setImage("cursor_invisible.png");
    }

    public void act() {
        followMouse();
    }


    public void followMouse() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo == null) {
            return;
        }
        if (getX() != mouseInfo.getX() || getY() != mouseInfo.getY()) {
            setLocation(mouseInfo.getX(), mouseInfo.getY());
        }
    }
    /*
    public void checkHover() {
        if(isTouching(Hitbox.class)) {
            List<Hitbox> hitboxes = getWorld().getObjectsAt(getX(),getY(),Hitbox.class);
            for(Hitbox hitbox: hitboxes) {
                hitbox.onHover();
            }
        }
    }*/
}
