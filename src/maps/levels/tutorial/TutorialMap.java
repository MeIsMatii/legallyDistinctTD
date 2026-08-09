package maps.levels.tutorial;

import greenfoot.Greenfoot;
import maps.levels.GameMap;
import ui.common.ClickawayImageDisplay;
import ui.hud.towerSelector.TowerSelector;
import util.Cursor;

/**
 * @author Sophia
 * @author Colin
 */
public class TutorialMap extends GameMap {
    private int counter = 0;
    private int lastDisplayedWave = -1;
    private boolean isTutorialPopupActive = false;

    public TutorialMap(){
        super();
        int[][] pathLocations = {{0,233},{752,233},{752,531},{1414,531},{1414,getHeight()}};
        super.addPath(pathLocations);
    }

    @Override
    public void act() {
        super.act();
        textBoardPlacer();
        Cursor cursor = null;
        for(Cursor c : getObjects(Cursor.class)) {
            cursor = c;
            break;
        }
        if(cursor == null) {
            return;
        }

        if(isTutorialPopupActive && cursor.isTouching(TowerSelector.class) && Greenfoot.getMouseInfo().getButton() == 1) {
            onContinue();
            isTutorialPopupActive = false;
            for(ClickawayImageDisplay cImg : getObjects(ClickawayImageDisplay.class)) {
                removeObject(cImg);
            }

        }
    }

    public void textBoardPlacer(){
        int currentRound = getWave();

        // Skip if popup is already showing or if we already showed it for this wave
        if (isTutorialPopupActive || currentRound == lastDisplayedWave) {
            return;
        }

        switch (currentRound){
            case 1:
                showTutorialBoard();
                lastDisplayedWave = currentRound;
                break;
            case 2:
                showTutorialBoard();
                lastDisplayedWave = currentRound;
                break;
            case 3:
                showTutorialBoard();
                lastDisplayedWave = currentRound;
                break;
            case 39:
                showTutorialBoard();
                lastDisplayedWave = currentRound;
                break;
        }
    }

    private void showTutorialBoard() {
        counter = counter + 1;
        switch (counter){
            case 1:
                isTutorialPopupActive = true;
                addObject(new ClickawayImageDisplay("TutorialDisplayOne.png", 1620, 216, true), 810, 972);
                pauseObjects(true, true);
                return;
            case 2:
                isTutorialPopupActive = true;
                addObject(new ClickawayImageDisplay("TutorialDisplayTwo.png", 1620, 216, true), 810, 972);
                pauseObjects(true, true);
                return;
            case 3:
                isTutorialPopupActive = true;
                addObject(new ClickawayImageDisplay("TutorialDisplayThree.png", 1620, 216, true), 810, 972);
                pauseObjects(true, true);
                return;
            case 4:
                isTutorialPopupActive = true;
                addObject(new ClickawayImageDisplay("TutorialDisplayLast.png", 1620, 216, true), 810, 972);
                pauseObjects(true, true);
                return;
        }
    }

    public void setTutorialPopupActive(boolean active) {
        this.isTutorialPopupActive = active;
    }

    @Override
    public int getMapNumber() {
        return 1;
    }
}