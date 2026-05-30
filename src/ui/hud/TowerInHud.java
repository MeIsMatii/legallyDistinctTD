package ui.hud;

import core.Clickable;
import core.MainClass;
import entities.tower.TestTower;
import entities.tower.Tower;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import map.levels.Map;

import java.util.List;

public class TowerInHud extends MainClass implements Clickable {

    public TowerInHud() {
        setImage("TestTower2_2.png");
        GreenfootImage img = getImage();
        img.scale(200, 200);
        setImage(img);
    }

    @Override
    public void onClick() {
        Map map = (Map) getWorld();
        map.getPLAYER().getCoins();
        if (map.getPLAYER().getCoins() > 0) {
            if (isTouching(Tower.class)) {
                List<Tower> towerList = getIntersectingObjects(Tower.class);
                //TODO fix @Mathilo, gotta make delete method 4 tower to del Hitbox & Range
                for (Tower tower : towerList) map.removeObject(tower);
            }
            map.getPLAYER().setCoins(map.getPLAYER().getCoins() - 100);
            MouseInfo mouseInfo = Greenfoot.getMouseInfo();
            getWorld().addObject(new TestTower(true, 300), mouseInfo.getX(), mouseInfo.getY());

        }


    }


    public void act() {
        checkClick();
    }
}
