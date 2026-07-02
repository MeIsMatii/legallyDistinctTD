package ui.hud;

import util.Clickable;
import core.MainClass;
import entities.tower.Tower;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import map.levels.Map;

import java.util.List;

public class TowerSelector extends MainClass implements Clickable {
    private final Tower towerToSpawn;
    public TowerSelector(Tower towerToSpawn) {
        setImage(towerToSpawn.getImage());
        GreenfootImage img = getImage();
        img.scale(200, 200);
        img.drawString(String.valueOf(towerToSpawn.getPRICE()), 30, 20);
        setImage(img);

        this.towerToSpawn = towerToSpawn;
    }

    @Override
    public void onClick() {
if(isPaused()) {
return;
}
        Map map = (Map) getWorld();
        if (map.getPLAYER().getCoins() >= towerToSpawn.getPRICE()) {
            if (isTouching(Tower.class) && getIntersectingObjects(Tower.class).get(0).isPlacing()) {
                List<Tower> towerList = getIntersectingObjects(Tower.class);
                for (Tower tower : towerList) {
                    map.removeObject(tower);
                    map.getPLAYER().setCoins(map.getPLAYER().getCoins()+tower.getPRICE());
                }

            }
            try {

                map.getPLAYER().setCoins(map.getPLAYER().getCoins() - towerToSpawn.getPRICE());
                MouseInfo mouseInfo = Greenfoot.getMouseInfo();
                getWorld().addObject((Tower) towerToSpawn.getClass().getDeclaredConstructor().newInstance(), mouseInfo.getX(), mouseInfo.getY());
            } catch (Exception e) {
                System.out.println("Error with onClick() at TowerInHud");
            }

        }


    }


    