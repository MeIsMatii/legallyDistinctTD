package maps.levels;

import maps.levels.util.MapCoordinatesUtilGuy;

public class GameMap6 extends GameMap {
    public GameMap6() {
        addObject(new MapCoordinatesUtilGuy(), 0, 0);
        int[][] pathLocations = {{0, 233}, {752, 233}, {752, 531}, {1414, 531}, {1414, 984}};
        super.addPath(pathLocations);

    }

    public GameMap6(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        addObject(new MapCoordinatesUtilGuy(), 0, 0);
        int[][] pathLocations = {{0, 233}, {752, 233}, {752, 531}, {1414, 531}, {1414, 984}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 6;
    }
}
