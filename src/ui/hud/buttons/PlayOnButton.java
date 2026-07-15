package ui.hud.buttons;

import maps.levels.GameMap;
import maps.menu.PauseMenu;
import ui.hud.PopupScreen;

import java.util.List;

public class PlayOnButton extends Button{

    public PlayOnButton() {
        setImage("PlayOnButton.png");
        getImage().scale(60, 60);
    }

    @Override
    public void onClick() {

        getWorldOfType(GameMap.class).onContinue();
        List<PopupScreen> menus = getWorld().getObjects(PopupScreen.class);
        if (!menus.isEmpty()){
            for (PopupScreen menu : menus) {
                menu.onRemove(); //removes generic popupscreen, no problem, bc there can only be one
            }
        }


    }


}
