package map.levels;

import core.Player;
import entities.Hitbox;
import entities.tower.Tower;
import entities.tower.util.RangeDisplay;
import greenfoot.World;
import map.levels.util.Path;
import ui.hud.TowerSelectorSpawner;
import ui.hud.UpgradeMenu;
import util.Cursor;
import util.saves.GameSaveManager;

public abstract class Map extends World {
    private UpgradeMenu UPGRADEMENU;
    private boolean isUpgradeMenuVisible;

    private final Player PLAYER;
    private final Cursor CURSOR;
    private final int PATHWIDTH;

    private final GameSaveManager GAMESAVEMANAGER;

    private int wave = 1;

    public Map() {
        super(1920, 1080, 1);
        this.GAMESAVEMANAGER = new GameSaveManager();
        addObject(GAMESAVEMANAGER, 0,0);

        setPaintOrder(Hitbox.class, Tower.class, RangeDisplay.class); //Tower infront of it's range

        this.PATHWIDTH = 120;
        PLAYER = new Player(100, 100); //jannis ganz alleine gemacht
        CURSOR = new Cursor();

        addHud();
    }

    public void addHud() {
        UPGRADEMENU = null;
        addObject(PLAYER, 0, 0);

        addObject(CURSOR, 0, 0);

        addObject(new TowerSelectorSpawner(), 1770, 540);
    }

    public void setUpgradeMenuVisibility(boolean isVisible, Tower tower) {
        isUpgradeMenuVisible = isVisible;
        //TODO add paths and delete them @Elias
        if(isVisible) {
            int width = (getWidth()-300)/2;
            if(UPGRADEMENU!=null) {
                UPGRADEMENU.delete();
            }
            UPGRADEMENU = new UpgradeMenu(tower);
            addObject(UPGRADEMENU,width, getHeight()-216/2);
        } else if(!getObjects(UpgradeMenu.class).isEmpty()) {
            UPGRADEMENU.delete();
            UPGRADEMENU = null;
        }
    }

    public UpgradeMenu getUpgradeMenu() {
        return this.UPGRADEMENU;
    }
    public boolean isUpgradeMenuVisible() {
        return isUpgradeMenuVisible;
    }

    public Player getPLAYER() {                   //jannis
        return PLAYER;
    }

    public GameSaveManager getGameSaveManager() {
        return GAMESAVEMANAGER;
    }

    public Cursor getCURSOR() {
        return CURSOR;
    }

    public void addPath(int[][] pathList) {
        for (int i = 0; i < pathList.length; i++) {
            int x = pathList[i][0];
            int y = pathList[i][1];


            if (i + 1 < pathList.length) {
                int nextX = pathList[i + 1][0];
                int nextY = pathList[i + 1][1];

                addObject(new Path(nextX, nextY, PATHWIDTH), x, y);
                //System.out.println("meow" +x +y);
            } else {
                addObject(new Path(0, 0, PATHWIDTH), x, y);
            }

        }
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }


}
