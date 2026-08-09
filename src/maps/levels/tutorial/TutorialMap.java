package maps.levels.tutorial;

import maps.levels.GameMap;
import ui.common.ClickawayImageDisplay;

/**
 * @Author Sophia
 * @Author Colin
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