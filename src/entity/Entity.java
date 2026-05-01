package entity;

import greenfoot.Actor;
import greenfoot.World;


public abstract class Entity extends Actor {
    public abstract void onHit(Entity hitter);

    public void checkHover(boolean isHovering) {
        if (isHovering) {
            onHover();
        }
        //so i can implement an else tree :D
    }

    public abstract void onHover();

    public void addedToWorld(World world) {
        int CELLSIZE = getWorld().getCellSize();

        int hitboxWidth = getImage().getWidth() / CELLSIZE;
        int hitboxHeight = getImage().getHeight() / CELLSIZE;


        Hitbox hitbox = new Hitbox(hitboxWidth, hitboxHeight, this);
        getWorld().addObject(hitbox, getX(), getY());
    }
}
