package maps.levels.tutorial;

import greenfoot.GreenfootImage;
import maps.levels.GameMap;

public class TutorialMap extends GameMap {

    public TutorialMap(){
        int[][] pathLocations = {{0,233},{752,233},{752,531},{1414,531},{1414,getHeight()}};
        super.addPath(pathLocations);
        setWave(0);
    }

    @Override
    public int getMapNumber() {
        return 1;
    }

}
