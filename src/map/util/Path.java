package map.util;

import entity.Entity;

public class Path extends Entity {
    private final int nextPathX;
    private final int nextPathY;


    public Path(int nextPathX, int nextPathY) {
        this.nextPathX = nextPathX;
        this.nextPathY = nextPathY;
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
}
