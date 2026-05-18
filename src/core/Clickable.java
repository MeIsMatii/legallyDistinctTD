package core;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;
import greenfoot.World;

public interface Clickable {

    default void checkClick() {

        boolean clickHappenedSomewhere = Greenfoot.mouseClicked(null);//schaut überall
        if (!clickHappenedSomewhere) {
            return;
        }
        MouseInfo mouseDetails = Greenfoot.getMouseInfo(); //wo ist die maus
        if (mouseDetails == null) {
            return;
        }
        Actor thisActor = (Actor) this;
        World currentWorld = thisActor.getWorld();
        if (currentWorld == null) {
            return;
        }
        int pixelsPerCell = currentWorld.getCellSize();
        int actorCenterInPixelsX = thisActor.getX() * pixelsPerCell + pixelsPerCell / 2;
        int actorCenterInPixelsY = thisActor.getY() * pixelsPerCell + pixelsPerCell / 2;//berechnet mitte der zelle
        int mousePositionInPixelsX = mouseDetails.getX() * pixelsPerCell + pixelsPerCell / 2; //Maus koordinaten /2 um einheitlich mit dem ersten zusein
        int mousePositionInPixelsY = mouseDetails.getY() * pixelsPerCell + pixelsPerCell / 2;

        int halfImageWidthInPixels = thisActor.getImage().getWidth() / 2;//max hälfte entfernt vom zenter
        int halfImageHeightInPixels = thisActor.getImage().getHeight() / 2;

        int distanceFromCenterX = Math.abs(mousePositionInPixelsX - actorCenterInPixelsX);
        int distanceFromCenterY = Math.abs(mousePositionInPixelsY - actorCenterInPixelsY);//wie weit weg vom zenter

        boolean clickedInsideImage = distanceFromCenterX <= halfImageWidthInPixels && distanceFromCenterY <= halfImageHeightInPixels; //in border von Bild

        if (clickedInsideImage) {
            onClick();
        }
    }

    default void onClick() {
        System.out.println("onClick is not defined for this object");
    }
}