package map.levels.util;

import entities.Entity;
import entities.Hitbox;
import greenfoot.World;

public class Path extends Entity {
    private final int nextPathX;
    private final int nextPathY;
    private final int PATHWIDTH;

    public Path(int nextPathX, int nextPathY, int PATHWIDTH) {
        this.nextPathX = nextPathX;
        this.nextPathY = nextPathY;
        this.PATHWIDTH = PATHWIDTH;
        setImage("invisible.png");
        //Todo Real Path Size

    }

    public void addedToWorld(World world) {
        checkLocations();

        int hitboxWidth = 0;
        int hitboxHeight = 0;

        int xOffset = 0;
        int yOffset = 0;

        if (getNextPathX() == 0 && getNextPathY() == 0) {
            hitboxWidth = 80;
            hitboxHeight = 80;


        } else if (getX() == getNextPathX()) {
            if (getY() < getNextPathY()) { //DOWN
                hitboxHeight = getNextPathY() - getY() + (PATHWIDTH / 2);
                yOffset = -hitboxHeight; //this needs to be negative D: //idk why tho

            } else if (getY() > getNextPathY()) { //UP
                hitboxHeight = getY() - getNextPathY() + (PATHWIDTH / 2);
                yOffset = hitboxHeight; //either this or 0. idfk anymore --mathilo
            }
            hitboxWidth = PATHWIDTH;


        } else if (getY() == getNextPathY()) {
            if (getX() < getNextPathX()) { // RIGHT
                hitboxWidth = getNextPathX() - getX() + (PATHWIDTH / 2);
                xOffset = -hitboxWidth; // huh

            } else if (getX() > getNextPathX()) { //LEFT
                hitboxWidth = getX() - getNextPathX() + (PATHWIDTH / 2);
                xOffset = hitboxWidth;
            }
            hitboxHeight = PATHWIDTH;
        }
        xOffset = xOffset / 2;
        yOffset = yOffset / 2;


        Hitbox hitbox = new Hitbox(hitboxWidth, hitboxHeight, false, this);
        getWorld().addObject(hitbox, getX() - xOffset, getY() - yOffset);
    }

    public int getNextPathX() {
        return nextPathX;
    }

    public int getNextPathY() {
        return nextPathY;
    }

    public void onHit(Entity hitter) {
        ///nothing needs to be handled in here i think
    }

    public void onHover() {
        //nothing
    }

    /**
     * Validates the given path
     *
     * @throws RuntimeException when there is an issue with the paths
     */
    private void checkLocations() {

        //Path not connected
        if ((getX() != getNextPathX() && getY() != getNextPathY()) && (getNextPathX() != 0 || getNextPathY() != 0)) {
            System.out.println();
            System.out.println("The Path locations are: (" + getX() + " | " + getY() + ") and (" + getNextPathX() + " | " + getNextPathY() + ")");
            throw new RuntimeException("Path is not connected. Atleast 1 coordinate needs to be the same for each path and next path.\nThe Path locations are: (" + getX() + " | " + getY() + ") and (" + getNextPathX() + " | " + getNextPathY() + ")");

        }
        //Path out of bounds
        else if (getX() < 0 || getX() > getWorld().getWidth() || getY() < 0 || getY() > getWorld().getHeight()) {
            throw new RuntimeException("Path is out of bounds at (" + getX() + " | " + getY() + "). Please fix.");

        }
        //Next path out of bounds
        else if (getNextPathX() != 0 && getNextPathY() != 0 &&
            (getNextPathX() < 0 || getNextPathX() > getWorld().getWidth() || getNextPathY() < 0 || getNextPathY() > getWorld().getHeight())) {
            throw new RuntimeException("Path is out of bounds at (" + getX() + " | " + getY() + "). Please fix.");

        }
    }

}
