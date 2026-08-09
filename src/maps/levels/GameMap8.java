package maps.levels;

import maps.levels.util.MapCoordinatesUtilGuy;

public class GameMap8 extends GameMap {
    public GameMap8() {
        addObject(new MapCoordinatesUtilGuy(), 0, 0);
        int[][] pathLocations = {{16, 163}, {1462, 163}, {1462, 694}, {205, 694}, {205, 1071}};
        super.addPath(pathLocations);

    }

    public GameMap8(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{16, 163}, {1462, 163}, {1462, 694}, {205, 694}, {205, 1071}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 8;
    }
}
