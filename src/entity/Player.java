package entity;

import greenfoot.Greenfoot;

public class Player extends Entity {

    public Player() {

    }

    public void act() {
        performMovement();
    }

    private void performMovement() {
        if (Greenfoot.isKeyDown("W")) {
            move(1);
        }
    }

    public void onHit(Entity hitter) {
        System.out.printf("Player hit by %s\n", hitter);
    }
}
