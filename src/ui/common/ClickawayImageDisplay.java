package ui.common;

import greenfoot.Greenfoot;
import maps.levels.GameMap;
import maps.levels.tutorial.TutorialMap;
import util.Clickable;

/**
 * @Author Colin
 */
public class ClickawayImageDisplay extends ImageDisplay implements Clickable {

    boolean PartOfTutorial = false;

    public ClickawayImageDisplay(String image, int scaleX, int scaleY) {
        super(image, scaleX, scaleY);
    }
    //second constructor only for Tutorial Use case
    public ClickawayImageDisplay(String image, int scaleX, int scaleY, boolean PartOfTut) {
        super(image, scaleX, scaleY);
        PartOfTutorial = PartOfTut;
    }

    @Override
    public void onClick() {
        if(PartOfTutorial){
            onRemove();
        }else {
            getWorld().removeObject(this);
        }
    }

    @Override
    public void act() {
        checkClick();
    }

    /**
     * @Usecase Only for tutorial when the map is already paused
     */
    public void onRemove() {
        if (getWorld() instanceof GameMap) {
            GameMap map = (GameMap) getWorld();
            if (map instanceof TutorialMap) {
                ((TutorialMap) map).setTutorialPopupActive(false);
            }

            map.pauseObjects(false, false);
            map.setPaused(false);

            // Remove the overlay from the world
            getWorld().removeObject(this);
        }
    }
}
