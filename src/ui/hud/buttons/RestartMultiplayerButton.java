package ui.hud.buttons;

import greenfoot.Greenfoot;
import maps.levels.GameMap;

public class RestartMultiplayerButton extends Button{
    public RestartMultiplayerButton() {
        setImage("buttons/RetryButton.png" );
    }
    @Override
    public void onClick() {
        try {
            GameMap currMap = (GameMap) getWorld().getClass().getDeclaredConstructor().newInstance();
            currMap.setMultiplayer(true);
            Greenfoot.setWorld(currMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
