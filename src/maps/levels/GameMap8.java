package maps.levels;

/**
 * @author Mathilo
 * @author colin
 */
public class GameMap8 extends GameMap {
    public GameMap8() {
        int[][] pathLocations = {{4, 170}, {1461, 170}, {1461, 689}, {207, 689}, {207, 1017}};
        super.addPath(pathLocations);
    }

    public GameMap8(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{4, 170}, {1461, 170}, {1461, 689}, {207, 689}, {207, 1017}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 8;
    }
}
