package entity;

import greenfoot.Greenfoot;
import greenfoot.MouseInfo;

public class Player extends Entity {

    public Player() {

    }

    public void act() {
        performMovement();
        CoordinatesDebug();
    }

    private void performMovement() {
        if (Greenfoot.isKeyDown("W")) {
            move(1);
        }
    }
    public void CoordinatesDebug(){
        if (Greenfoot.isKeyDown("F3")){
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            System.out.println("X:"+ mouseInfo.getX() +"   " + "Y:" + mouseInfo.getY());
        }
    }
    public void onHit(Entity hitter) {
        System.out.printf("Player hit by %s\n", hitter);
    }
}
