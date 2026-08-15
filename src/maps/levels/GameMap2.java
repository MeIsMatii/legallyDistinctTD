package maps.levels;

import maps.levels.util.MapCoordinatesUtilGuy;
/**
 * @author Mathilo
 * @author colin
 */
public class GameMap2 extends GameMap {
    public GameMap2() {
        int[][] pathLocations = {{1412,24},{1403,24},{1403,385},{217,385},{217,866},{1576,866}};
        super.addPath(pathLocations);

    }

    public GameMap2(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);

        int[][] pathLocations = {{1412,24},{1403,24},{1403,385},{217,385},{217,866},{1576,866}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 2;
    }
}
