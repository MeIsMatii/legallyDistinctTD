package maps.levels;

import maps.levels.util.MapCoordinatesUtilGuy;

/**
 * @author Mathilo
 * @author colin
 */
public class GameMap5 extends GameMap {
    public GameMap5() {
        int[][] pathLocations = {{10,255},{1464,255},{1464,1012}};
        super.addPath(pathLocations);
        super.addPath(pathLocations);
    }

    public GameMap5(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{10,255},{1464,255},{1464,1012}};
        super.addPath(pathLocations);
    }

    @Override
    public int getMapNumber() {
        return 5;
    }
}
