package map.map;

import greenfoot.World;
import hud.Player;
import hud.TowerSelector;
import map.util.Path;
import util.Cursor;

public abstract class Map extends World {
    private final Player PLAYER;
    private final Cursor CURSOR;
    private final int PATHWIDTH;

    public Map() {
        super(1920, 1080, 1);
        this.PATHWIDTH = 120;

        PLAYER = new Player(100,100); //jannis ganz alleine gemacht
        CURSOR = new Cursor();

        addHud();
    }

    public void addHud() {

        addObject(PLAYER,0,0);

        addObject(CURSOR,0,0);

        addObject(new TowerSelector(),1760,540);
    }


    public Player getPLAYER() {                   //jannis
        return PLAYER;
    }

    public Cursor getCURSOR() {
        return CURSOR;
    }

    public void addPath(int[][] pathList){
        for (int i = 0; i < pathList.length; i++) {
            int x = pathList[i][0];
            int y = pathList[i][1];


            if(i+1< pathList.length) {
                int nextX = pathList[i+1][0];
                int nextY = pathList[i+1][1];

                addObject(new Path(nextX,nextY, PATHWIDTH),x,y);
                //System.out.println("meow" +x +y);
            } else {
                addObject(new Path(0,0, PATHWIDTH),x,y);
            }

        }
    }

    //TODO add paths like in Map1
}
