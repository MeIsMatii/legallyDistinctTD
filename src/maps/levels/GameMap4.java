package maps.levels;

import maps.levels.util.MapCoordinatesUtilGuy;

/**
 * @author Mathilo
 * @author colin
 */
public class GameMap4 extends GameMap {
    public GameMap4() {
        int[][] pathLocations = {{1492,8},{1492,427},{84,427},{84,1017}};
        super.addPath(pathLocations);
    }

    public GameMap4(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{1492,8},{1492,427},{84,427},{84,1017}};
        super.addPath(pathLocations);
    }

    @Override
    public int getMapNumber() {
        return 4;
    }
}
