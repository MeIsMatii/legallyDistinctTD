package map.levels;

import core.MainClass;
import core.Player;
import greenfoot.World;
import map.levels.util.Path;
import ui.hud.TowerSelector;
import ui.hud.UpgradeMenu;
import util.Cursor;

import java.util.List;

public abstract class Map extends World {
    private UpgradeMenu UPGRADEMENU;
    private boolean isUpgradeMenuVisible;

    private int resolution;
    private final Player PLAYER;
    private final Cursor CURSOR;
    private final int PATHWIDTH;
    private int[][] PATHLIST;

    public Map() {
        super(1920, 1080, 1);

        this.PATHWIDTH = 120;

        PLAYER = new Player(100, 100); //jannis ganz alleine gemacht
        CURSOR = new Cursor();

        addHud();
    }

    public void addHud() {
        this.UPGRADEMENU = new UpgradeMenu();

        addObject(PLAYER, 0, 0);

        addObject(CURSOR, 0, 0);

        addObject(new TowerSelector(), 1770, 540);
    }

    public void setUpgradeMenuVisibility(boolean isVisible) {
        isUpgradeMenuVisible = isVisible;
        if(isVisible) {
            int width = (getWidth()-300)/2;
            addObject(UPGRADEMENU,width, getHeight()-216/2);
        } else if(!getObjects(UpgradeMenu.class).isEmpty()) {
            removeObject(UPGRADEMENU);
        }
    }

    public boolean isUpgradeMenuVisible() {
        return isUpgradeMenuVisible;
    }

    public Player getPLAYER() {                   //jannis
        return PLAYER;
    }

    public Cursor getCURSOR() {
        return CURSOR;
    }

    public void addPath(int[][] pathList) {
        this.PATHLIST = pathList;
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


    //TODO add paths like in Map1
}
