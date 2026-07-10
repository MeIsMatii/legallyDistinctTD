package ui.hud;

import entities.tower.Tower;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;

public class UpgradeDescriptionOverlay extends Actor {
    private final int path;
    public UpgradeDescriptionOverlay(Tower tower, int path, int maxPath){
        String upgradeDesc = "";
        if(maxPath == 0) {
            upgradeDesc = "locked";
            setImage(new GreenfootImage(upgradeDesc, 20, Color.WHITE, Color.BLACK));
            this.path = path;
            return;
        }

        switch(path) {
            case 1:
                if(tower.getUpgrade1() >= maxPath) {
                    upgradeDesc = "done";
                    break;
                }
                upgradeDesc = tower.getUpgradeDescription1();
                break;
            case 2:
                if(tower.getUpgrade2() >= maxPath) {
                    upgradeDesc = "done";
                    break;
                }
                upgradeDesc = tower.getUpgradeDescription2();
                break;
            case 3:
                if(tower.getUpgrade3() >= maxPath) {
                    upgradeDesc = "done";
                    break;
                }
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
