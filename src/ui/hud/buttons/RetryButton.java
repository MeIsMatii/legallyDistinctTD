package ui.hud.buttons;

import maps.levels.GameMap;
import greenfoot.Greenfoot;
/// @Author Julian
public class RetryButton extends Button{


    public RetryButton() {
        setImage("buttons/RetryButton.png");
        getImage().scale(60, 60);
    }

    public void onClick(){
        try {
            GameMap gameMap = (GameMap) getWorld().getClass().getDeclaredConstructor().newInstance();
            Greenfoot.setWorld(gameMap);
            gameMap.getGameSaveManager().createSaveFile();
        } catch (Exception e) {
            System.out.println("error lmao");
        }
    }


}
