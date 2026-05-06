package entity;

import greenfoot.Actor;
import greenfoot.World;


public abstract class Entity extends Actor {
    public abstract void onHit(Entity hitter);

    public void checkHover(boolean isHovering) {
        if (isHovering) {
            System.out.println("hover");
            onHover();
        } else {
            System.out.println("unhover");
            onUnhover();
        }
        //so i can implement an else tree :D
    }

    public void onHover() {
        return;
    }

    public void onUnhover() {
        return;
    }

    public void addedToWorld(World world) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = getImage().getWidth() / CELLSIZE;
        int hitboxHeight = getImage().getHeight() / CELLSIZE;


        Hitbox hitbox = new Hitbox(hitboxWidth, hitboxHeight, this);
        getWorld().addObject(hitbox, getX(), getY());
    }
}
