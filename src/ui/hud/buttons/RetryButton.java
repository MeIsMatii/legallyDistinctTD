package ui.hud.buttons;

import map.levels.Map;
import util.Clickable;
import core.MainClass;
import greenfoot.Greenfoot;

public class RetryButton extends Button{


    public RetryButton() {
        setImage("RetryButton.png");
        getImage().scale(60, 60);
    }

    public void onClick(){
        try {
            Map map = (Map) getWorld().getClass().getDeclaredConstructor().newInstance();
            Greenfoot.setWorld(map);
            map.getGameSaveManager().createSaveFile();
        } catch (Exception e) {
            System.out.println("error lmao");
        }
    }


}
