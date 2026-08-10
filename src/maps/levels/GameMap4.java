package maps.levels;
/**
 * @author Mathilo
 * @author colin
 */
public class GameMap4 extends GameMap {
    public GameMap4() {

        int[][] pathLocations = {{86, 1067}, {86, 434}, {1503, 434}, {1503, 5}};
        super.addPath(pathLocations);
    }

    public GameMap4(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{86, 1067}, {86, 434}, {1503, 434}, {1503, 5}};
        super.addPath(pathLocations);
    }

    @Override
    public int getMapNumber() {
        return 4;
    }
}
