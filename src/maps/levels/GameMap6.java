package maps.levels;

public class GameMap6 extends GameMap {
    public GameMap6() {
        int[][] pathLocations = {{192, 6}, {192, 809}, {1613, 809}};
        super.addPath(pathLocations);

    }

    public GameMap6(boolean isMultiplayer, boolean isHost) {
        super(isMultiplayer, isHost);
        int[][] pathLocations = {{192, 6}, {192, 809}, {1613, 809}};
        super.addPath(pathLocations);

    }

    @Override
    public int getMapNumber() {
        return 6;
    }
}
