package entity;

import greenfoot.Greenfoot;
import greenfoot.World;

public class Player extends Entity {

    public Player() {

    }

    public void addedToWorld(World world) {
        Hitbox hitbox = new Hitbox(1, 3, this);
        getWorld().addObject(hitbox, getX(), getY());
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
