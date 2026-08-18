package maps.levels;

/**
 * @author Mathilo
 * @author colin
 */
public class GameMap6 extends GameMap {
    public GameMap6() {
        int[][] pathLocations = {{191, 9}, {191, 807}, {1579, 807}};
        super.addPath(pathLocations);

    }

    public GameMap6(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{191, 9}, {191, 807}, {1579, 807}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 6;
    }
}
