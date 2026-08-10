package ui.hud.buttons;

import greenfoot.Greenfoot;
import maps.levels.GameMap;
import util.multiplayer.NetworkManager;

/**
 * @author Mathilo
 */
public class RestartMultiplayerButton extends Button {
    public RestartMultiplayerButton() {
        setImage("buttons/RetryButton.png");
    }

    @Override
    public void onClick() {
        try {
            NetworkManager.getInstance().setConnected(false);
            NetworkManager.getInstance().setDisconnected(false);
            GameMap currMap = (GameMap) getWorld().getClass().getDeclaredConstructor(boolean.class, boolean.class).newInstance(true, true);
            Greenfoot.setWorld(currMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
