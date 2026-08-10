package maps.levels;
/**
 * @author Mathilo
 * @author colin
 */
public class GameMap7 extends GameMap {
    public GameMap7() {
        int[][] pathLocations = {{1398, 5}, {1398, 372}, {174, 372}, {174, 889}, {1614, 889}};
        super.addPath(pathLocations);
    }

    public GameMap7(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{1398, 5}, {1398, 372}, {174, 372}, {174, 889}, {1614, 889}};
        super.addPath(pathLocations);


    }

    @Override
    public int getMapNumber() {
        return 7;
    }
}
