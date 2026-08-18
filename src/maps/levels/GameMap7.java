package maps.levels;

/**
 * @author Mathilo
 * @author colin
 */
public class GameMap7 extends GameMap {
    public GameMap7() {
        int[][] pathLocations = {{1396, 2}, {1396, 383}, {168, 383}, {168, 892}, {1576, 892}};
        super.addPath(pathLocations);
    }

    public GameMap7(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{1396, 2}, {1396, 383}, {168, 383}, {168, 892}, {1576, 892}};
        super.addPath(pathLocations);


    }

    @Override
    public int getMapNumber() {
        return 7;
    }
}
