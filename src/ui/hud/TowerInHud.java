package ui.hud;

import core.Clickable;
import core.MainClass;
import entities.tower.TestTower;
import entities.tower.Tower;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import map.levels.Map;
import java.awt.*;



public class TowerInHud extends MainClass implements Clickable {

    public TowerInHud(){
        setImage("TestTower2_2.png");
        GreenfootImage img = getImage();
        img.scale(200,200);
        setImage(img);
    }
    @Override
    public void onClick() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        getWorld().addObject(new TestTower(true,300),mouseInfo.getX(), mouseInfo.getY());
    }

    public void act() {
        checkClick();
    }
}
