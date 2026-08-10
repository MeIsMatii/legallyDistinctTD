package maps.levels;
/**
 * @author Mathilo
 * @author colin
 */
public class GameMap5 extends GameMap {
    public GameMap5() {

        int[][] pathLocations = {{6, 253}, {1465, 253}, {1465, 1066}};
        super.addPath(pathLocations);
    }

    public GameMap5(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);

        int[][] pathLocations = {{6, 253}, {1465, 253}, {1465, 1066}};
        super.addPath(pathLocations);
    }

    @Override
    public int getMapNumber() {
        return 5;
    }
}
