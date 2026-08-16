package maps.levels;

import maps.levels.util.MapCoordinatesUtilGuy;

/**
 * @author Mathilo
 * @author colin
 */
public class GameMap1 extends GameMap {
    public GameMap1() {
        int[][] pathLocations = {{0, 233}, {752, 233}, {752, 531}, {1414, 531}, {1414, getHeight()}};
        super.addPath(pathLocations);
    }

    public GameMap1(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);

        int[][] pathLocations = {{0, 233}, {752, 233}, {752, 531}, {1414, 531}, {1414, getHeight()}};
        super.addPath(pathLocations);
    }

    public int getMapNumber() {
        return 1;
    }


}
