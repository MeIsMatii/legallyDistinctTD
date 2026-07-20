package ui.hud;

import core.MainClass;
import entities.tower.Tower;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import maps.levels.GameMap;
import util.Clickable;

import java.util.List;
import java.util.function.Supplier;

public class TowerSelector extends MainClass implements Clickable {
    private final Supplier<Tower> towerToSpawn;
    private final Tower tower;

    public TowerSelector(Supplier<Tower> towerToSpawn) {
        tower = towerToSpawn.get();
        setImage(tower.getImage());
        GreenfootImage img = getImage();
        img.scale(200, 200);
        img.drawString(String.valueOf(tower.getPrice()), 30, 20);
        setImage(img);

        this.towerToSpawn = towerToSpawn;
    }

    @Override
    public void onClick() {
        if (isPaused()) {
            return;
        }
        GameMap gameMap = (GameMap) getWorld();
        if (gameMap.getPlayer().getCoins() >= tower.getPrice()) {
            if ((isTouching(Tower.class) && getIntersectingObjects(Tower.class).get(0).isPlacing())) {
                List<Tower> towerList = getIntersectingObjects(Tower.class);

                if (getIntersectingObjects(Tower.class).get(0).getClass() == tower.getClass()) { //so you cannot exchange a tower with another one of the same class. instead its just sold
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

                gameMap.getPlayer().setCoins(gameMap.getPlayer().getCoins() - tower.getPrice());
                MouseInfo mouseInfo = Greenfoot.getMouseInfo();
                getWorld().addObject(towerToSpawn.get(), mouseInfo.getX(), mouseInfo.getY());
            } catch (Exception e) {
                System.out.println("Error with onClick() at TowerInHud");
            }

        }
    }

    public void act() {
        checkClick();
    }
}


    