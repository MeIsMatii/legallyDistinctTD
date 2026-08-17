package ui.hud.towerSelector;

import core.MainClass;
import entities.base.Tower;
import greenfoot.*;
import maps.levels.GameMap;
import util.Clickable;

import java.util.List;
import java.util.function.Supplier;
/**
 * @author Febo
 * @author Jannis
 */
public class TowerSelector extends MainClass implements Clickable {
    private final Supplier<Tower> towerToSpawn;
    private final Tower tower;

    private final GreenfootImage imageNoPrice;

    private int oldCoins = 0;

    public TowerSelector(Supplier<Tower> towerToSpawn) {
        tower = towerToSpawn.get();
        GreenfootImage imgage = new GreenfootImage("towerSelectorFrame.png");
        imgage.scale(130,130);


        GreenfootImage img = tower.getImage();
        img.scale(100, 100);
        imgage.drawImage(img,15,15);

        GreenfootImage priceDisplay = new GreenfootImage("towerSelectorPrice.png");
        GreenfootImage fullImage = new GreenfootImage(130,170);

        fullImage.drawImage(imgage,0,0);
        fullImage.drawImage(priceDisplay, 23, 130);

        fullImage.setFont(new Font("Arial", true, false, 20));

        imageNoPrice = fullImage;
        setImage(imageNoPrice);

        this.towerToSpawn = towerToSpawn;
    }

    public void addedToWorld(World w) {
        updatePrice();
    }


    public void updatePrice() {
        int coins = getWorldOfType(GameMap.class).getPlayer().getCoins();
        if(coins == oldCoins) { //coins didnt change so state didnt either
            return;
        }
        oldCoins =  coins;

        GreenfootImage img = new GreenfootImage(imageNoPrice);
        if(coins >= tower.getPrice()) {
            img.setColor(Color.GREEN);
        } else {
            img.setColor(Color.RED);
        }

        img.drawString(String.valueOf(tower.getPrice() + "$"), 45, 157);
        setImage(img);
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
        updatePrice();
    }
}


    