package maps.levels;
/**
 * @author Mathilo
 * @author colin
 */
public class GameMap3 extends GameMap {
    public GameMap3() {
        int[][] pathLocations = {{4, 880}, {392, 880}, {392, 94}, {1483, 94}, {1483, 1070}};
        super.addPath(pathLocations);

    }

    public GameMap3(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{4, 880}, {392, 880}, {392, 94}, {1483, 94}, {1483, 1070}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 3;
    }
}
