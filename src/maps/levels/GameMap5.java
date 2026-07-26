package maps.levels;

import maps.levels.util.MapCoordinatesUtilGuy;

public class GameMap5 extends GameMap {
    public GameMap5() {

        addObject(new MapCoordinatesUtilGuy(), 0, 0);
        int[][] pathLocations = {{0, 233}, {752, 233}, {752, 531}, {1414, 531}, {1414, 984}};
        super.addPath(pathLocations);
    }

    public GameMap5(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);

        addObject(new MapCoordinatesUtilGuy(), 0, 0);
        int[][] pathLocations = {{0, 233}, {752, 233}, {752, 531}, {1414, 531}, {1414, 984}};
        super.addPath(pathLocations);
    }

    @Override
    public int getMapNumber() {
        return 5;
    }
}
