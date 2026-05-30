package map.util;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;


public class MapCoordinatesUtilGuy extends Actor {

    public MapCoordinatesUtilGuy() {}
    public void act() {

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(mouseInfo == null) {
            return;
        }
        if(mouseInfo.getButton() == 2) //middle button
        {
            System.out.printf("x: %d, y: %d\n", mouseInfo.getX(), mouseInfo.getY());
        }
    }
}
