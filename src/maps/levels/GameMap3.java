package maps.levels;

/**
 * @author Mathilo
 * @author colin
 */
public class GameMap3 extends GameMap {
    public GameMap3() {
        int[][] pathLocations = {{58, 875}, {401, 875}, {401, 95}, {1484, 95}, {1484, 998}};
        super.addPath(pathLocations);

    }

    public GameMap3(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{58, 875}, {401, 875}, {401, 95}, {1484, 95}, {1484, 998}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 3;
    }
}
