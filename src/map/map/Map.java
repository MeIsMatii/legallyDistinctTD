package map.map;

import greenfoot.World;
import hud.Player;
import map.util.Path;

abstract class Map extends World {
    Player player;
    public Map() {
        super(1920, 1080, 1);
        player = new Player(100,100); //jannis ganz alleine gemacht
        addObject(player,0,0);
    }

    public void addPath(int[][] pathList){
        for (int i = 0; i < pathList.length; i++) {
            int x = pathList[i][0];
            int y = pathList[i][1];


            if(i+1< pathList.length) {
                int nextX = pathList[i+1][0];
                int nextY = pathList[i+1][1];

                addObject(new Path(nextX,nextY),x,y);
                System.out.println("meow" +x +y);
            } else {
                addObject(new Path(0,0),x,y);
            }

        }
    }

    //TODO add paths like in Map1
}
