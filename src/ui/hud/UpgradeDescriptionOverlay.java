package ui.hud;

import entities.tower.Tower;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class UpgradeDescriptionOverlay extends Actor {
    public UpgradeDescriptionOverlay(Tower tower){
        setImage(new GreenfootImage(tower.getUpgradeDescription1(), 20, Color.WHITE, Color.BLACK));
    }

}
