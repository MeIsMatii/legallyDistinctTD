package ui.hud.buttons;

import maps.levels.GameMap;
import ui.hud.PopupScreen;

import java.util.List;

/**
 * @author julian
 */
public class PlayOnButton extends Button {

    public PlayOnButton() {
        setImage("buttons/PlayOnButton.png");
        getImage().scale(60, 60);
    }

    @Override
    public void onClick() {

        getWorldOfType(GameMap.class).onContinue();
        List<PopupScreen> pauseMenus = getWorld().getObjects(PopupScreen.class);
        if (!pauseMenus.isEmpty()) {
            for (PopupScreen pauseMenu : pauseMenus) {
                pauseMenu.onRemove(); //removes generic popupscreen, no problem, bc there can only be one
            }
        }


    }


}
