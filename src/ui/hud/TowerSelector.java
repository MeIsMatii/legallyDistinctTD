package ui.hud;

import core.MainClass;
import entities.tower.Tower;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import map.levels.GameMap;
import util.Clickable;

import java.util.List;

public class TowerSelector extends MainClass implements Clickable {
    private final Tower towerToSpawn;

    public TowerSelector(Tower towerToSpawn) {
        setImage(towerToSpawn.getImage());
        GreenfootImage img = getImage();
        img.scale(200, 200);
        img.drawString(String.valueOf(towerToSpawn.getPrice()), 30, 20);
        setImage(img);

        this.towerToSpawn = towerToSpawn;
    }

    @Override
    public void onClick() {
        if (isPaused()) {
            return;
        }
        GameMap gameMap = (GameMap) getWorld();
        if (gameMap.getPlayer().getCoins() >= towerToSpawn.getPrice()) {
            if ((isTouching(Tower.class) && getIntersectingObjects(Tower.class).get(0).isPlacing())) {
                List<Tower> towerList = getIntersectingObjects(Tower.class);

                if (getIntersectingObjects(Tower.class).get(0).getClass() == towerToSpawn.getClass()) { //so you cannot exchange a tower with another one of the same class. instead its just sold
                    for (Tower tower : towerList) {
                        gameMap.removeObject(tower);
                        gameMap.getPlayer().setCoins(gameMap.getPlayer().getCoins() + tower.getPrice());
                    }
                    return;
                }

                for (Tower tower : towerList) {
                    gameMap.removeObject(tower);
                    gameMap.getPlayer().setCoins(gameMap.getPlayer().getCoins() + tower.getPrice());
                }

            }
            try {

                gameMap.getPlayer().setCoins(gameMap.getPlayer().getCoins() - towerToSpawn.getPrice());
                MouseInfo mouseInfo = Greenfoot.getMouseInfo();
                getWorld().addObject(towerToSpawn.getClass().getDeclaredConstructor().newInstance(), mouseInfo.getX(), mouseInfo.getY());
            } catch (Exception e) {
                System.out.println("Error with onClick() at TowerInHud");
            }

        }
    }

    public void act() {
        checkClick();
    }
}


    