package map.util;

import entity.Entity;
import entity.Hitbox;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class Path extends Entity {
    private final int nextPathX;
    private final int nextPathY;


    public Path(int nextPathX, int nextPathY) {
        this.nextPathX = nextPathX;
        this.nextPathY = nextPathY;
        setImage("cursor_invisible.png");
        //Todo Real Path Size

    }

    public void addedToWorld(World world) {

        int hitboxWidth = 0;
        int hitboxHeight = 0;

        int xOffset = 0;
        int yOffset = 0;



        if (getNextPathX() == 0 && getNextPathY() == 0) {
            hitboxWidth = 80;
            hitboxHeight = 80;


        } else if (getX() == getNextPathX()) {
            if (getY() < getNextPathY()) { //DOWN
                hitboxHeight = getNextPathY() - getY();
                yOffset=hitboxHeight;
                //offset hH distance down

            } else if (getY() > getNextPathY()) { //UP
                hitboxHeight = getY() - getNextPathY();
                yOffset=0;
            }
            hitboxWidth = 80;


        } else if (getY() == getNextPathY()) {
            if (getX() < getNextPathX()) { // RIGHT
                hitboxWidth = getNextPathX() - getX();
                xOffset=0;

            } else if (getX() > getNextPathX()) { //LEFT
                hitboxWidth = getX() - getNextPathX();
                xOffset=hitboxWidth;
            }
            hitboxHeight = 80;
        }
        //System.out.printf("mewo: w: %d, h: %d\n", hitboxWidth, hitboxHeight);
        System.out.println(xOffset);
        Hitbox hitbox = new Hitbox(hitboxWidth, hitboxHeight, this);
        getWorld().addObject(hitbox, getX()-xOffset, getY()-yOffset);
    }

    public int getNextPathX() {
        return nextPathX;
    }

    public int getNextPathY() {
        return nextPathY;
    }

    public void onHit(Entity hitter) {
        return; ///nothing needs to be handled in here i think
    }

    public void onHover() {
        //nothing
    }

}
