package ui.hud;

import entities.tower.Tower;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class UpgradeDescriptionOverlay extends Actor {
    private int path;
    public UpgradeDescriptionOverlay(Tower tower, int path){
        String upgradeDesc = "";
        switch(path) {
            case 1:
                upgradeDesc = tower.getUpgradeDescription1();
                break;
            case 2:
                upgradeDesc = tower.getUpgradeDescription2();
                break;
            case 3:
                upgradeDesc = tower.getUpgradeDescription3();
                break;
        }
        setImage(new GreenfootImage(upgradeDesc, 20, Color.WHITE, Color.BLACK));
        this.path = path;
    }

    public int getPath() {
        return path;
    }

}
